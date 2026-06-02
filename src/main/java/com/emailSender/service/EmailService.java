package com.emailSender.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String message, File file) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);

            String htmlBody =
                    "<html>" +
                            "<body style='font-family:Arial'>" +
                            "<h1 style='color:blue;'>Welcome to Email Service 🚀</h1>" +
                            "<p style='font-size:16px;'>" + message + "</p>" +
                            "<hr>" +
                            "<p style='color:green;'>This email is generated automatically.</p>" +
                            "<br>" +
                            "<b>Thanks & Regards</b><br>" +
                            "<i>Spring Boot Team</i>" +
                            "</body>" +
                    "</html>";

            helper.setText(htmlBody, true);

            // Attachment
            if (file != null) {
                FileSystemResource resource = new FileSystemResource(file);
                helper.addAttachment(resource.getFilename(), resource);
            }

            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace(); // console me actual error dikhega
            throw new RuntimeException("Email sending failed", e);
        }
    }
}