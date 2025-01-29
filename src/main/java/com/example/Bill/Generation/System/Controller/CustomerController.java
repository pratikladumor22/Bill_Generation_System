package com.example.Bill.Generation.System.Controller;

import com.example.Bill.Generation.System.model.Customer;
import com.example.Bill.Generation.System.service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerServices services;

    @PostMapping("/add")
    public String add(@RequestBody Customer customer){
        services.addCustomer(customer);
        String s = String.valueOf(customer.getcID());
        return "Name : "  + customer.getName() + "\nMobileNo : " + customer.getMobileNo()  + "\nId : " + s;
    }
}
