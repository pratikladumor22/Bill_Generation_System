package com.example.Bill.Generation.System.service;

import com.example.Bill.Generation.System.DTO.BillRequest;
import com.example.Bill.Generation.System.Repository.BillRepository;
import com.example.Bill.Generation.System.Repository.CustomerRepository;
import com.example.Bill.Generation.System.Repository.ProductRepository;
import com.example.Bill.Generation.System.model.Bill;
import com.example.Bill.Generation.System.model.Customer;
import com.example.Bill.Generation.System.model.PaymentStatus;
import com.example.Bill.Generation.System.model.Product;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class BillServices {
    @Autowired
    BillRepository billRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    smsServices smsServices;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecret;

    public String generateBill(long mobileNO , int pID , int quantity){
        Customer customer = customerRepository.findBymobileNo(mobileNO);
        Product product = productRepository.findById(pID).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getQuantity() < quantity) {
            return "Insufficient stock for the requested product.";
        }
        double totalcost = product.getPrice() * quantity;
        double gstamount = totalcost * 0.18;
        double finalamount = totalcost  + gstamount;

        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayApiKey, razorpayApiSecret);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (double) (finalamount * 100)); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());
            


            PaymentStatus payment = generateRandomPaymentStatus() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
            Bill bill = new Bill();
            bill.setCustomer(customer);
            bill.setProduct(product);
            bill.setQuantity(quantity);
            bill.setTotalcost(totalcost);
            bill.setGstamount(gstamount);
            bill.setFinalamount(finalamount);
            bill.setPurchasedate(LocalDateTime.now());
            bill.setPayment(payment);

            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            billRepository.save(bill);

            String mobile = "+91" + String.valueOf(mobileNO);
            String message;
            message = "\nBill Number : " + String.valueOf(bill.getBillNo()) +
                    "\nCustomer Name : " + bill.getCustomer().getName() +
                    "\nAmount : " + String.valueOf(bill.getTotalcost()) +
                    "\nGST Amount : " + String.valueOf(bill.getGstamount()) +
                    "\nTotal Amount : " + String.valueOf(bill.getFinalamount()) +
                    "\nPayment Status: " + payment;
            smsServices.sendSms(mobile, message);
            smsServices.sendwh(mobile, message);

            if (product.getQuantity() < 10) {
                String adminMessage = "⚠️ Alert: Low Stock Notification ⚠️\n" +
                        "Product Name : " + product.getPname() + "\n" +
                        "Current Quantity : " + product.getQuantity() + "\n" +
                        "Please restock as soon as possible.";
                smsServices.sendSms(mobile, adminMessage);
                smsServices.sendwh(mobile, adminMessage);
            }
            return payment == PaymentStatus.SUCCESS ? "Order SuccessFully." : "Order Failed.";
        }catch (RazorpayException e) {
            return "Payment processing failed: " + e.getMessage();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean generateRandomPaymentStatus(){
        return Math.random() < 0.9;
    }
}
