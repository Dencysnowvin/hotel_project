package com.cts.paymentservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByBookingId(Long bookingId);
}