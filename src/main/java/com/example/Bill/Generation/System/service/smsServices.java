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
    public static final String ACCOUNT_SID = "ACf78e85476d8cd8f916e32c874f03728b";
    public static final String AUTH_TOKEN = "78e20184d95046db4e558d38e2347646";

    public void sendSms(String smsNumber, String smsMessage) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber(smsNumber), new PhoneNumber("+15076195033"),smsMessage)
                .create();
        System.out.println(message.getSid());
    }

    public void sendwh(String whnumber , String whMessage){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:" + whnumber),new PhoneNumber("whatsapp:+14155238886"),whMessage
        ).create();
        System.out.println(message.getSid());
    }
}
