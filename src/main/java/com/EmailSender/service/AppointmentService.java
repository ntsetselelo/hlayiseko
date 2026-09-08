package com.EmailSender.service;

import com.EmailSender.EmailSender;
import com.EmailSender.request.emailRequest.EmailRequest;
import com.EmailSender.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    EmailSender emailSender;
    @Value("${application.receptionist.email}")
    private String receptionistEmail;
    public EmailRequest sendRequestAppointment(EmailRequest emailRequest) {
        try {
            String receptionistSubject="New Appointment";
            String receptionistBody="<p><strong>Full Name:</strong> " + emailRequest.getFullName() + "</p>" +
                    "<p><strong>Email:</strong> " + emailRequest.getEmail() + "</p>" +
                    "<p><strong>Phone:</strong> " + emailRequest.getPhoneNumber() + "</p>" +
                    "<p><strong>Date:</strong> " + emailRequest.getDate() + "</p>" +
                    "<p><strong>Service Type:</strong> " + emailRequest.getServiceType() + "</p>" +
                    "<p><strong>Additional Message:</strong> " + emailRequest.getAdditionalMessage() + "</p>";
            emailSender.sendEmail(new EmailUtil().emailBuilder(receptionistEmail,receptionistBody,receptionistSubject));
            String patientSubject = "Appointment Request Received - Greener-Spot Opticals";
            String patientBody =
                    "<p>Dear " + emailRequest.getFullName() + ",</p>" +
                            "<p>Thank you for reaching out to Greener-Spot Opticals! We have received your request for a <strong>"
                            + emailRequest.getServiceType() + "</strong> on <strong>" + emailRequest.getDate() + "</strong>.</p>" +
                            "<p>Our team will review your appointment details and confirm your booking shortly.</p>" +
                            "<br>" +
                            "<p>Best regards,<br><strong>Greener-Spot Opticals Team</strong><br>" +
                            "Phone: +27 66 232 7912</p>";
            emailSender.sendEmail(new EmailUtil().emailBuilder(emailRequest.getEmail(),patientBody,patientSubject));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return emailRequest;
    }
}
