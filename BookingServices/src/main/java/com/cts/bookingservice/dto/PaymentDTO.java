package com.cts.bookingservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private Long bookingId;
    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus status;

}