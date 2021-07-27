package com.castleapp.calendarservice.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castleapp.calendarservice.dto.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByCastleId(Integer id);
	
	//Booking findByCastleIdAndBookingId(Integer castleId, Integer id);
	
	Booking findByDateBookedAndCastleId(LocalDate date, Integer castleId);

}
