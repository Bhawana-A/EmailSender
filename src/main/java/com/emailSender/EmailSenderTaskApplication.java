package com.emailSender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailSenderTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderTaskApplication.class, args);
	}

}
