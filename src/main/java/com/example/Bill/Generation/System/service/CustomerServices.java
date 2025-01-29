package com.example.Bill.Generation.System.service;

import com.example.Bill.Generation.System.DTO.Response;
import com.example.Bill.Generation.System.Repository.CustomerRepository;
import com.example.Bill.Generation.System.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    @Autowired
    CustomerRepository customerRepository;

    public Response addCustomer(Customer customer){
        Response response = new Response();
        try {
            customerRepository.save(customer);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Customer Details Save Successfully. ");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
