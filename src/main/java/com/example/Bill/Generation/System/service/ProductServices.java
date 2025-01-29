package com.example.Bill.Generation.System.service;

import com.example.Bill.Generation.System.DTO.Response;
import com.example.Bill.Generation.System.Repository.ProductRepository;
import com.example.Bill.Generation.System.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;


    public Response addProduct(Product product){
        Response response = new Response();
        try {
            productRepository.save(product);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Product add SuccessFully. ");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response updateStock(int pID , int quantity){
        Response response = new Response();
        try {
            Optional<Product> product =productRepository.findById(pID);
            product.get().setQuantity(product.get().getQuantity() + quantity);
            productRepository.save(product.get());
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("Stock Updated.");
        }catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public Response generateProductReportCsv(String filePath) throws IOException{
        Response response = new Response();


        List<Product> products = productRepository.findAll();
        try(Writer writer=new FileWriter(filePath);
            ICsvBeanWriter csvBeanWriter=new CsvBeanWriter(writer, CsvPreference.EXCEL_PREFERENCE)){

            String[] headers={"pID", "pname", "price" ,"quantity"};
            csvBeanWriter.writeHeader(headers);

            for (Product product:products){
                csvBeanWriter.write(product,headers);
            }
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("CSV fill Genrated. ");
        }
        catch (Exception e){
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

