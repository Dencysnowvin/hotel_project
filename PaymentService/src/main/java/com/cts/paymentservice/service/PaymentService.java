package com.cts.paymentservice.service;

import java.util.List;

import com.cts.paymentservice.dto.PaymentBookingResponseDTO;
import com.cts.paymentservice.dto.PaymentDTO;

public interface PaymentService {

	PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getAllPayments();
   List<PaymentDTO> getPaymentsByBookingId(Long bookingId);
   PaymentBookingResponseDTO getPaymentBookingDetails(Long paymentId);
   PaymentDTO completePayment(Long paymentId, PaymentDTO paymentDTO);

}
