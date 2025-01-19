package com.example.practice3.services.senders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {
    @Value("${spring.mail.username}") private String from;

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String toEmail, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setText(text);
        emailSender.send(message);
    }
}