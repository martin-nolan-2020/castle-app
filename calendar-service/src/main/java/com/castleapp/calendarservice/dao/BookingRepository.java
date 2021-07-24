package com.castleapp.calendarservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castleapp.calendarservice.dto.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	

}
