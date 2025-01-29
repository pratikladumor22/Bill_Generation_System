package com.example.Bill.Generation.System.Controller;

import com.example.Bill.Generation.System.service.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    private EmailServices services;

    public EmailController(EmailServices services) {
        this.services = services;
    }

    @PostMapping("/send")
    public String sendmail(@RequestParam(value = "file" ,required = false)MultipartFile[] file , String to , String subject , String body){
       return services.sendMail(file, to, subject, body);
    }



}
