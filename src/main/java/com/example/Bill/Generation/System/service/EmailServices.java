package com.example.Bill.Generation.System.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailServices {
    String sendMail(MultipartFile[] file, String to, String subject, String body );
}
