package com.EmailSender.controller;

import com.EmailSender.request.emailRequest.EmailRequest;
import com.EmailSender.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<EmailRequest> createAppointment(@RequestBody EmailRequest emailRequest) {
        EmailRequest response = appointmentService.sendRequestAppointment(emailRequest);
        return ResponseEntity.ok(response);
    }
}
