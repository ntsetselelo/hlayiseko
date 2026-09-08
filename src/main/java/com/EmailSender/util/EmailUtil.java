package com.EmailSender.util;


import com.EmailSender.Email;
import com.EmailSender.EmailSender;
import com.EmailSender.request.emailRequest.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import java.util.Collections;

@Slf4j
public class EmailUtil {
    @Value("${application.email.from-name}")
    private String fromName;
    @Value("${application.email.from-address}")
    private  String fromAddress;
    @Autowired
    private EmailSender sender;

    public Email emailBuilder(String recipientEmail,String message,String subject) {
        Email email = new Email();
        email.setBody(new MessageUtil().buildMessage(message).toString());
        email.setToAddress(recipientEmail);
        email.setSubject(subject);
        email.setFromAddress(fromAddress);
        email.setFromName(fromName);
        email.setType("TEXT");
        return email;
    }
}
