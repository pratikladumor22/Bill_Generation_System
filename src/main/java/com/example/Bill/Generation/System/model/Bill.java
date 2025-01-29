package com.example.Bill.Generation.System.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_no")
    private long billNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_id")
    private Product product;

    private int quantity;
    private double totalcost;
    private double gstamount;
    private double finalamount;
    private LocalDateTime purchasedate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus payment;

    public PaymentStatus getPayment() {
        return payment;
    }

    public void setPayment(PaymentStatus payment) {
        this.payment = payment;
    }

    public long getBillNo() {
        return billNo;
    }

    public void setBillNo(long billNo) {
        this.billNo = billNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public double getGstamount() {
        return gstamount;
    }

    public void setGstamount(double gstamount) {
        this.gstamount = gstamount;
    }

    public double getFinalamount() {
        return finalamount;
    }

    public void setFinalamount(double finalamount) {
        this.finalamount = finalamount;
    }

    public LocalDateTime getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(LocalDateTime purchasedate) {
        this.purchasedate = purchasedate;
    }
}
