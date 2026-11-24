package com.example.EmployeeMangaementSystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendOtp(String toEmail, String otp) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("OTP VERIFICATION");
        simpleMailMessage.setText("Your OTP is " + otp);

        javaMailSender.send(simpleMailMessage);
		return otp;
    }
}


