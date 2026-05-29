package com.emailSender.dto;

public record EmailRequest(
        String toEmail,
        String subject,
        String body
) {}