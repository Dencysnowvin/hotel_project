 package com.cts.paymentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.paymentservice.dto.PaymentBookingResponseDTO;
import com.cts.paymentservice.dto.PaymentDTO;
import com.cts.paymentservice.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return ResponseEntity.ok(paymentDTO);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
//
//    @GetMapping("/booking/{bookingId}")
//    public ResponseEntity<List<PaymentDTO>> getPaymentsByBookingId(@PathVariable Long bookingId) {
//        List<PaymentDTO> payments = paymentService.getPaymentsByBookingId(bookingId);
//        return ResponseEntity.ok(payments);
//    }
    
    @GetMapping("/{paymentId}/booking-details")
    public ResponseEntity<PaymentBookingResponseDTO> getPaymentBookingDetails(@PathVariable Long paymentId) {
        PaymentBookingResponseDTO responseDTO = paymentService.getPaymentBookingDetails(paymentId);
        return ResponseEntity.ok(responseDTO);
    }
    
    @PostMapping("/{paymentId}/complete")
    public ResponseEntity<PaymentDTO> completePayment(@PathVariable Long paymentId, @RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO completedPayment = paymentService.completePayment(paymentId, paymentDTO);
        return ResponseEntity.ok(completedPayment);
    }
}


    