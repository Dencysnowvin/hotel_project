package com.cts.paymentservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.paymentservice.client.BookingClient;
import com.cts.paymentservice.client.RoomClient;
import com.cts.paymentservice.dto.BookingDTO;
import com.cts.paymentservice.dto.PaymentBookingResponseDTO;
import com.cts.paymentservice.dto.PaymentDTO;
import com.cts.paymentservice.dto.RoomDTO;
import com.cts.paymentservice.entity.Payment;
import com.cts.paymentservice.entity.PaymentStatus;
import com.cts.paymentservice.exception.InvalidPaymentAmountException;
import com.cts.paymentservice.exception.ResourceNotFoundException;
import com.cts.paymentservice.repo.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	    @Autowired
	    private PaymentRepository paymentRepository;

	    
	    @Autowired
	    private RoomClient roomClient;
	    
	    @Autowired
	    private BookingClient bookingClient;


	    @Override
	    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
	        // Validate booking ID using Feign client
	        BookingDTO booking = bookingClient.getBookingById(paymentDTO.getBookingId());
	        if (booking == null) {
	            throw new ResourceNotFoundException("Booking not found with id: " + paymentDTO.getBookingId());
	        }

	        // Retrieve room price from Hotel service
	        RoomDTO room = roomClient.getRoomById(booking.getRoomId());
	        if (room == null) {
	            throw new ResourceNotFoundException("Room not found with id: " + booking.getRoomId());
	        }

	        // Update amount based on room price
	        paymentDTO.setAmount(room.getPrice());
	        paymentDTO.setPaymentDate(LocalDate.now());
	        paymentDTO.setStatus(PaymentStatus.PENDING); // Initial status

	        Payment payment = convertToEntity(paymentDTO);
	        Payment savedPayment = paymentRepository.save(payment);
	        return convertToDTO(savedPayment);
	    }

	    @Override
	    public PaymentDTO getPaymentById(Long id) {
	        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + id));
	        return convertToDTO(payment);
	    }

	    @Override
	    public List<PaymentDTO> getAllPayments() {
	        List<Payment> payments = paymentRepository.findAll();
	        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }
	    
	    @Override
	    public List<PaymentDTO> getPaymentsByBookingId(Long bookingId) {
	        List<Payment> payments = paymentRepository.findByBookingId(bookingId);
	        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
	    }

	    @Override
	    public PaymentDTO completePayment(Long paymentId, PaymentDTO paymentDTO) {
	        Payment payment = paymentRepository.findById(paymentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

	        // Validate the amount
	        if (payment.getAmount() != paymentDTO.getAmount()) {
	            throw new InvalidPaymentAmountException("The amount entered does not match the required amount.");
	        }

	        // Update payment details
	        payment.setPaymentDate(LocalDate.now());
	        payment.setStatus(PaymentStatus.COMPLETED);

	        Payment updatedPayment = paymentRepository.save(payment);
	        return convertToDTO(updatedPayment);
	    }

	   

	    @Override
	    public PaymentBookingResponseDTO getPaymentBookingDetails(Long paymentId) {
	        Payment payment = paymentRepository.findById(paymentId)
	                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));
	        BookingDTO bookingDetails = bookingClient.getBookingById(payment.getBookingId());

	        PaymentBookingResponseDTO responseDTO = new PaymentBookingResponseDTO();
	        responseDTO.setPaymentId(payment.getPaymentId());
	        responseDTO.setBookingDetails(bookingDetails);

	        return responseDTO;
	    }
	    // Conversion methods
	    private PaymentDTO convertToDTO(Payment payment) {
	        PaymentDTO paymentDTO = new PaymentDTO();
	        paymentDTO.setPaymentId(payment.getPaymentId());
	        paymentDTO.setBookingId(payment.getBookingId());
	        paymentDTO.setAmount(payment.getAmount());
	        paymentDTO.setPaymentDate(payment.getPaymentDate());
	        paymentDTO.setStatus(payment.getStatus());
	        return paymentDTO;
	    }

	    private Payment convertToEntity(PaymentDTO paymentDTO) {
	        Payment payment = new Payment();
	        payment.setPaymentId(paymentDTO.getPaymentId());
	        payment.setBookingId(paymentDTO.getBookingId());
	        payment.setAmount(paymentDTO.getAmount());
	        payment.setPaymentDate(paymentDTO.getPaymentDate());
	        payment.setStatus(paymentDTO.getStatus());
	        return payment;
	    }
	  
	}
