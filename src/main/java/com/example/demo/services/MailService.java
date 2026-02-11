package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mail;


    public void sendMail(String to,String subject,String body){
        SimpleMailMessage res = new SimpleMailMessage();
        res.setTo(to);
        res.setSubject(subject);
        res.setText(body);
        mail.send(res);
    }
}
