package com.martinnolan.castlemanager.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import com.castleapp.calendarservice.dto.Booking;

@FeignClient(name="calendar-service", url = "localhost:8000")
public interface CalendarProxy {

	//below URL is in the CalendarServiceController 
	@GetMapping("bookings-by-castle-id/{id}/date/{date}")
	public String getBookingByCastleId(@PathVariable int id, @PathVariable String date) 
			//throws RuntimeException
			;

	//public String getBookingByCastleId(Integer id, String date);
}
