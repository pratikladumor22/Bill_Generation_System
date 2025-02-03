package com.example.Bill.Generation.System.service;

import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class smsServices {
    public static final String ACCOUNT_SID = "your sid";
    public static final String AUTH_TOKEN = "Your token";

    public void sendSms(String smsNumber, String smsMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(smsNumber), new PhoneNumber("your twilio number"),smsMessage)
                .create();
        System.out.println(message.getSid());
    }

    public void sendwh(String whnumber , String whMessage){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + whnumber),new PhoneNumber("whatsapp:your twilio number"),whMessage
        ).create();
        System.out.println(message.getSid());
    }
}
