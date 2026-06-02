package com.emailSender.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emailSender.service.EmailService;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;


    @Scheduled(cron = "0 10 16 * * *")
    public void sendMailEveryFiveMinutes() {

        emailService.sendEmail(
                "bhawanaahirwar52@gmail.com",
                "Scheduled Email",
                "This email is automatically sent every 5 minutes.",
                null
        );

        System.out.println("Email Sent Successfully By Scheduler");
    }
}