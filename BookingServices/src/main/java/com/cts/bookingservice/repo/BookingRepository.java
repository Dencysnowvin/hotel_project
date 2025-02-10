package com.cts.bookingservice.repo;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cts.bookingservice.entity.Booking;

import feign.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByRoomId(Long roomId);

	@Query("SELECT b FROM Booking b WHERE b.checkInDate = :checkInDate")
    List<Booking> findByCheckInDate(@Param("checkInDate") LocalDate checkInDate);
	
	 @Query("SELECT b FROM Booking b WHERE b.roomId = :roomId AND " +
	           "((b.checkInDate <= :checkOutDate AND b.checkOutDate >= :checkInDate))")
	    List<Booking> findOverlappingBookings(@Param("roomId") Long roomId, 
	                                          @Param("checkInDate") LocalDate checkInDate, 
	                                          @Param("checkOutDate") LocalDate checkOutDate);
	}


