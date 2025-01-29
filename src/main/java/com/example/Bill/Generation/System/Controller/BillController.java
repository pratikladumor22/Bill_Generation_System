package com.example.Bill.Generation.System.Controller;

import com.example.Bill.Generation.System.DTO.BillRequest;
import com.example.Bill.Generation.System.model.Bill;
import com.example.Bill.Generation.System.service.BillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillServices services;

    @PostMapping("/bill-generate")
    public String generateBill(@RequestBody BillRequest request){
        String  bill = services.generateBill(request.getMobileNo(),
                request.getpID(),
                request.getQuantity());
        return bill;
    }
}
