package com.EmailSender;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender {
    @Value("${application.mail.from-email}")
    private String emailFrom;
    @Autowired
    JavaMailSender javaMailSender;

    @Async
    public void sendEmail(Email email) throws  Exception{
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(email.getToAddress());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(),true);
            helper.setFrom(emailFrom);
            javaMailSender.send(message);

        }catch (MessagingException e){
            log.error("failed to send email: {}",e.getMessage(),e);
        }
    }
}
