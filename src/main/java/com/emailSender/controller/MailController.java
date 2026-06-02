package com.emailSender.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.emailSender.service.EmailService;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendMail(
            @RequestParam String toEmail,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam(required = false)
            @Schema(type = "string", format = "binary")
            MultipartFile file
            
    ) throws Exception {

        File convFile = null;

        // optional file
        if (file != null && !file.isEmpty()) {
            convFile = File.createTempFile("upload-", file.getOriginalFilename());
            file.transferTo(convFile);
        }

        emailService.sendEmail(toEmail, subject, message, convFile);

        return "Email Sent Successfully ";
    }
}