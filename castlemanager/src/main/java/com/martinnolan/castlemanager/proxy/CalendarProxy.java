package com.martinnolan.castlemanager.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import com.castleapp.calendarservice.dto.Booking;

//@FeignClient(name="calendar-service", url = "localhost:8000")
//remove url = "localhost:8000" to allow Eureka to perform load balancing
@FeignClient(name="calendar-service")
public interface CalendarProxy {

	//below URL is in the CalendarServiceController 
	//different to CalendarServiceController however as it returns a String and not a Booking
	//this is because I don't have a bean that is equivalent to Booking in the castlemanager microservice
	//*** MAYBE THIS NEEDS TO BE CHANGED IN THE FUTURE TO BE MORE USEFUL?? ***
	@GetMapping("bookings-by-castle-id/{id}/date/{date}")
	public String getBookingByCastleId(@PathVariable int id, @PathVariable String date) 
			//throws RuntimeException
			;

	//public String getBookingByCastleId(Integer id, String date);
}
