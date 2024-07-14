package com.microservicesproject.order_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOrderCompletionEmail(String recipientEmail, String orderDetails) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(recipientEmail);
            helper.setSubject("Order Confirmation");
            helper.setText("Your order details: " + orderDetails);
            javaMailSender.send(message);
        } catch (Exception e) {
            // Handle exception
        }
    }
}
