package com.example.Bill.Generation.System.Controller;

import com.example.Bill.Generation.System.DTO.Response;
import com.example.Bill.Generation.System.model.Product;
import com.example.Bill.Generation.System.service.EmailServices;
import com.example.Bill.Generation.System.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductServices services;

    @Autowired
    EmailServices emailServices;

    @PostMapping("add-product")
    public Response add(@RequestBody Product product){
        return services.addProduct(product);
    }

    @PutMapping("stock-add")
    public Response add(@RequestParam int pID , @RequestParam int quantity){
        return services.updateStock(pID,quantity);
    }

    @GetMapping("/generate-report")
    public String generateProductReport() throws IOException{
        String filePath = "E:\\product_report.csv";
        Response response = services.generateProductReportCsv(filePath);

        if (response.getHttpStatus() == HttpStatus.OK){
            File csvfile = new File(filePath);
            MultipartFile[] attachments = new MultipartFile[1];
            attachments[0] = new MockMultipartFile("product_report.csv", new  FileInputStream(csvfile));
            String subject = "Stock Report";
            String body = "Please find the attached stock report.";
            String to = "("your emial")";
            return emailServices.sendMail(attachments , to , subject , body);
        }else {
            return String.valueOf(response);
        }

//        File file = new File(filePath);
//        if (!file.exists()) {
//            throw new IOException("File not found: " + filePath);
//        }
//        return file;
    }


}
