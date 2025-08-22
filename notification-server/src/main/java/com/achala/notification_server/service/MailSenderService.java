package com.achala.notification_server.service;

import com.achala.notification_server.dto.OrderConfirmationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromMail;

    protected void sendEmail(String toMail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(toMail);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    protected String buildEmailBody(OrderConfirmationDto order) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = order.createdAt().format(formatter);

        return "Hello " + order.customer().firstName() + ",\n\n" +
                "Thank you for your order!\n\n" +
                "Order Reference: " + order.reference() + "\n" +
                "Order Date: " + formattedDate + "\n\n" +
                "Items Ordered:\n" +
                order.orderItems().stream()
                        .map(product -> "- " + product.name() + " x " + product.quantity())
                        .collect(Collectors.joining("\n")) +
                "\n\nTotal Amount: $" + order.totalAmount() +
                "\n\nWe are processing your order and will notify you once it is shipped.\n\n" +
                "Thank you for choosing Us!";
    }
}
