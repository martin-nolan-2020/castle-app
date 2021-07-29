package com.castleapp.calendarservice.controllers;

import java.util.Date;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.castleapp.calendarservice.dao.BookingRepository;
import com.castleapp.calendarservice.dto.Booking;

import exceptions.BookingNotFoundException;

@RestController
public class CalendarServiceController {

	//this is used for getting the port value
	@Autowired
	Environment environment;
	
	@Autowired
	BookingRepository bookingRepository;
	
//	@GetMapping("/test")
//	public String aTest() {
//		return new String("Test is good"); 
//	}	
	
	
	//---below endpoint is not interacting with DB, it is just creating a new Booking regardless of castleId and date inputs---
	//this endpoint is used when the user wants to check if a Castle is booked for a certain date.
	//So, the user needs to input the date and Castle id to check if the Castle is available for the date
	//If it is already booked then the details of the Booking are returned including:
	//1. id (Booking ID) 
	//2. dateBooked 
	//3. the Eircode of the customer who booked
	//4. the Castle ID
	//If it has not been booked then return null as there is no matching Booking object found in the database
	@GetMapping("/calendar/date/{date}/castles/{castleId}")
	public Booking getDates(@PathVariable String date, @PathVariable int castleId){
		
		//--------------------------------------------//
		//need to pad the date String with '0' if day or month is single digit.
						   //012345678 index count for next line
		//For example month: 2017-5-23 needs to become 2017-05-23 (Twenty-third of May)
						   //012345678
		//For example day:   2015-12-2 needs to become 2015-12-02 (Second of December)
		
		//deal with month first
		System.out.println(date.substring(6,7));
		if(date.substring(6,7).equals("-")) {
			System.out.println("less than 10 month (October)");
			//if true then month is 1 - 9 so need to pad with 0 at front
			date = date.substring(0,5) + "0" + date.substring(5);
			System.out.println("padded month: " + date);
		}
		
		//now deal with day in the same way
		if(date.length()<10) {
			//String is 9 characters long and should be 10 so need to add a 0 again
			date = date.substring(0,8) + "0" + date.substring(8);
			System.out.println("padded day (and month): " + date);
		}
		
		//date is now in the desired format
		
		//--------------------------------------------//
		
//		System.out.println(date);
		int year = Integer.parseInt(date.substring(0,4));
//		System.out.println(year);
		int month = Integer.parseInt(date.substring(5,7));
//		System.out.println(month);
		int day = Integer.parseInt(date.substring(8,10));
//		System.out.println(day);
		LocalDate convertToDate = LocalDate.of(year, month, day);
//		
		Booking booking = new Booking(1, convertToDate, "R95N6K6" ,castleId);
		
		//this extracts the port
		String port = environment.getProperty("local.server.port");
		booking.setEnvironment(port);
		return booking;
//		return null;
		
	}
	
	//return all bookings
	@GetMapping("bookings")
	public List<Booking> getAllFromDB(){
		return bookingRepository.findAll();
	}
	
	//return 1 booking by ID
	@GetMapping("bookings/{id}")
	public Optional<Booking> getBookingById(@PathVariable int id) throws RuntimeException {
		Optional<Booking> bookingFound = bookingRepository.findById(id);
		if(!bookingFound.isPresent()) {
			throw new BookingNotFoundException("unable to find booking with id: " + id);		
		} 
		
		return bookingFound;
		
	}
	
	//2017-02-25 3
	//return list of bookings with certain CASTLE id
	@GetMapping("bookings-by-castle-id/{id}/date/{date}")
	public Booking getBookingByCastleId(@PathVariable int id, @PathVariable String date) throws RuntimeException{
		LocalDate ld = LocalDate.parse(date);
		//return bookingRepository.findByCastleId(id);
		List<Booking> listOfBookings = bookingRepository.findByCastleId(id);
		Booking aBooking = bookingRepository.findByDateBookedAndCastleId(ld, id);
		if(aBooking==null) {
			throw new BookingNotFoundException("Sorry - unable to find booking with id: " + id);		
		}
		return aBooking;
		//return bookingRepository.findByDateBookedAndCastleId(ld, id);
		//return null;
	}
	
	@PostMapping("bookings")
	public ResponseEntity<Object> createABooking(@RequestBody Booking booking){
		Booking savedBooking = bookingRepository.save(booking);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedBooking.getId())
		.toUri();
		
		return ResponseEntity.created(location).build();	
	}
	
	
	
	
	
	
	
	
	
	
}
