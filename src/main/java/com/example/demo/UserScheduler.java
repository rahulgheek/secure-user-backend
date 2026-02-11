package com.example.demo;

import com.example.demo.entity.UserBean;
import com.example.demo.services.MailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduler {
    @Autowired
    private MailService service;

    @Autowired
    private UserService s;

    @Scheduled(cron = "0 */3 * * * *")
    public void sendScheduledMail(){
        service.sendMail("rahulgheek2@gmail.com","Java Scheduled mail check","Hi scheduling service check successfull");
    }

    @Scheduled(cron = "0 */3 * * * *")
    public void sendMailToUsers(){
        List<UserBean> user = s.displayAll();

        for (UserBean u: user){
            if(u.getRole().equals("ROLE_USER")){
                service.sendMail(u.getEmail(),"Java scheduled mail check","Hi");
            }
        }
    }
}
