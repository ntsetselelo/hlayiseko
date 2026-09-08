package com.EmailSender.request.emailRequest;

import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private String fullName;
    private String phoneNumber;
    private String serviceType;
    private String date;
    private String additionalMessage;
}
