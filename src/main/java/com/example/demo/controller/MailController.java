package com.example.demo.controller;

import com.example.demo.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public ResponseEntity<String> sendMail(){
        mailService.sendMail("YOUR_EMAIL","Java mail check","Hii service check successfull");
        return new ResponseEntity<>("mail sent successful", HttpStatus.OK);
    }
}
