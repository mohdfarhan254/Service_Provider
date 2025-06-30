package com.farhan.serviceapp.shared.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.farhan.serviceapp.shared.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
@Override
public void sendOtpEmail(String to, String otp) {
    try {
        System.out.println("📧 Sending OTP to: " + to + " | OTP: " + otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mohdfarhan29102002@gmail.com"); // ✅ Explicitly set sender
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        System.out.println("✅ Mail sent successfully!");
    } catch (Exception e) {
        System.out.println("❌ Failed to send email: " + e.getMessage());
        e.printStackTrace(); // log full error
    }
}



}
