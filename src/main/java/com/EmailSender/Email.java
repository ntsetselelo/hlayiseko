package com.EmailSender;

import lombok.Data;

import javax.sql.DataSource;
import java.util.List;

@Data
public class Email {

    private String toAddress;
    private  String subject;

    private String fromAddress;
    private String fromName;
    private  String body;
    private String type;

    private DataSource attachment;
    private List<String> to;
}