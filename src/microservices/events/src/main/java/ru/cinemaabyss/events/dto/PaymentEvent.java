package ru.cinemaabyss.events.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class PaymentEvent {
    private int paymentId;
    private int userId;
    private BigDecimal amount;
    private String status;
    private ZonedDateTime timestamp;
    private String methodType;
}