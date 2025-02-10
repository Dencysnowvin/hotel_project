package com.cts.bookingservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.bookingservice.dto.PaymentDTO;
import com.cts.bookingservice.dto.PaymentStatus;

@FeignClient(name = "PAYMENTSERVICE")
public interface PaymentClient {
	@PostMapping("/payments")
    PaymentDTO createPayment(@RequestBody PaymentDTO paymentDTO);

    @GetMapping("/payments/booking/{bookingId}")
    List<PaymentDTO> getPaymentsByBookingId(@PathVariable("bookingId") Long bookingId);

    @PutMapping("/payments/{paymentId}/status")
    PaymentDTO updatePaymentStatus(@PathVariable("paymentId") Long paymentId, @RequestParam("status") PaymentStatus status);
}