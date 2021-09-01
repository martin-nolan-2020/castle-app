package com.martinnolan.castlemanager.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import org.apache.commons.lang.StringEscapeUtils.*;
//import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.text.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.martinnolan.castlemanager.dao.CastleRepository;
import com.martinnolan.castlemanager.dto.Booking;
import com.martinnolan.castlemanager.dto.Castle;
import com.martinnolan.castlemanager.dto.Note;
import com.martinnolan.castlemanager.exceptions.*;
import com.martinnolan.castlemanager.proxy.CalendarProxy;

import feign.FeignException;
import net.minidev.json.parser.JSONParser;

//import net.minidev.json.JSONObject;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@Validated
//@CrossOrigin(origins = "http://localhost:4200")	//this deals with the CORS issue when trying to access API from frontend React app
//@CrossOrigin(origins = "http://localhost:4200")	//this deals with the CORS issue when trying to access API from frontend React app
public class CastleController {
	
	@Autowired
	CastleRepository castleRepository;

	@Autowired
	CalendarProxy calendarProxy;
	
	//below works
	@GetMapping("first-test")
	public String getText() {
		return "this is the first test. hello world";
	}
	
	@GetMapping("second-test-castle-bean")
	public Castle getCastle() {
		return new Castle("BS-2", "Big slide", "Blue slide", null ,30.0, 25.0, 160.0, "https://bouncycastlenetwork-res.cloudinary.com/image/upload/f_auto,q_auto,c_limit,w_500/d573bff20c31fe487e116b5760f41036");
	}
	
	//below not working now 
	//**** NOT FINISHED ****
//	List<Note> twoNotesForExample = new ArrayList<>(Arrays.asList(new Note()));
//	@GetMapping("castle-bean")
//	public Castle getCastleBean() {
//		return new Castle("BS-2", "Big slide", "Blue slide", null ,30.0, 25.0, 160.0);	
//	}
	
	//return all castles
	@GetMapping("castles")
	public List<Castle> getAllCastles(){
		System.out.println("***inside get all - before ***");
		return castleRepository.findAll();
	}
	
	//return a specific castle by id
	@GetMapping("castles/{id}")
	public Optional<Castle> getCastleById(@PathVariable Integer id) throws RuntimeException {
		Optional<Castle> castle = castleRepository.findById(id);
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("Oh no. Something has gone wrong! ID: " + id + " not found");
		}
		
		//throw new CastleNotFoundException("Oh no. Something has gone wrong!");
		//throw new RuntimeException("went wrong!!");
		//throw new CastleNotFoundException("id: " + id);
		
		//****THE BELOW IS NOT WORKING FOR THE MOMENT --> WON'T RETURN NOTES LIST ABOUT A CASTLE??	
//		//a resource rather than a castle is needed here so that the below link can be appended providing simple HATEOAS
//		EntityModel<Optional<Castle>> resource = EntityModel.of(castle);
//		
//		//provides a link to all castles in what is returned to localhost:8080/castles/1
//		WebMvcLinkBuilder linkTo = 
//				linkTo(methodOn(this.getClass()).getAllCastles());
//		
//		resource.add(linkTo.withRel("all-users"));
//		
//		return resource;
		
		return castle;

	}
	
	@GetMapping("castles-with-bookings/{id}/date/{date}")
	public Castle getCastleWithBookingInfo(@PathVariable Integer id, @PathVariable String date) {
		System.out.println("***");
		System.out.println(id);
		System.out.println(date);
		Optional<Castle> castle = castleRepository.findById(id);
		System.out.println("*!*" + castle);
		
		
		
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/3", String.class);
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/55/date/2017-02-25", String.class);
		
		
		//try-catch block
		//https://stackoverflow.com/questions/16194014/spring-mvc-resttemplate-launch-exception-when-http-404-happens
		//need try catch block in case 404 Not Found is returned
		//checks if a certain Castle is booked on a certain date - so needs to call Calendar/Booking service to check if there is a match
		//if there is no match then the default Castle field "bookedOrAvailable "remains at "available"
		//if there is a match in Calendar/Booking service then Castle field "bookedOrAvailable" is changed to "booked" 
//		try {
//			ResponseEntity<String> forEntity = 
//					new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/"+id+"/date/"+date, String.class);
//			
//			Castle newCastle = castle.get();
//			//newCastle.setBookings(forEntity.getBody());
//			newCastle.setBookedOrAvailable("booked");
//			
//			System.out.println("*@* " + forEntity);
//			System.out.println("***!*** " + forEntity.getBody());
//			
//		} catch (final HttpClientErrorException e) {
//			// TODO: handle exception
//			System.out.println(e.getStatusCode());
//		    System.out.println(e.getResponseBodyAsString());
//		}
		
		try {
			String bookingString = calendarProxy.getBookingByCastleid(id, date);		// %$%
			System.out.println("inside try/catch --> " + bookingString);
			Castle newCastle = castle.get();
			//newCastle.setBookings(forEntity.getBody());
			
			//no 404 (i.e. a booking of that Castle exists on that date so can set field to "booked")
			newCastle.setBookedOrAvailable("booked (checked with calendar microservice - found a booking in its DB)");
			} catch(FeignException exception) {
				//catches the 404 Not Found and so setBookedOrAvailable remains at the default value of "available"
				System.out.println("inside catch part");
				System.out.println(exception.getMessage());
				
				
			}
		
		//Booking aBooking = new Booking();
		
		Castle newCastle = castle.get();
		//newCastle.setBookings(forEntity.getBody());
		//newCastle.setBookedOrAvailable("ok");

		
		//return castle.get();
		return newCastle;
	}
	
	
	
//	@PostMapping("castles")
//	public ResponseEntity<Object> addACastle(@Valid @RequestBody Castle newCastle){
//		Castle savedCastle = castleRepository.save(newCastle);
//		
//		URI location = ServletUriComponentsBuilder
//			.fromCurrentRequest()
//			.path("/{id}")
//			.buildAndExpand(savedCastle.getId())
//			.toUri();
//		
//		return ResponseEntity.created(location).build();	
//	}
	
//	//make a booking for a Castle
//	@PostMapping("castles-with-bookings-feign/{id}/date/{date}")
//	public ResponseEntity<Object> addABooking(@PathVariable Integer id, @PathVariable String date){
//		
//		return null;
//		
//	}
	
	
	//feign is included in the URL as it is using the feign client rather than the Rest template - less code involved 
	@GetMapping("castles-with-bookings-feign/{id}/date/{date}")
	public Castle getCastleWithBookingInfoFeign(@PathVariable Integer id, @PathVariable String date) throws RuntimeException {
		System.out.println("***feign***");
		System.out.println(id);
		System.out.println(date);
		Optional<Castle> castle = castleRepository.findById(id);
		System.out.println("*!*feign***" + castle);
		
		
		//instead of using the RestTemplate as in the previous method (getCastleWithBookingInfo) here using the CalendarProxy as part 
		//of the FeignClient 
		//same info as getCastleWithBookingInfo method:
				//try-catch block
				//https://stackoverflow.com/questions/16194014/spring-mvc-resttemplate-launch-exception-when-http-404-happens
				//need try catch block in case 404 Not Found is returned
				//checks if a certain Castle is booked on a certain date - so needs to call Calendar/Booking service to check if there is a match
				//if there is no match then the default Castle field "bookedOrAvailable "remains at "available"
				//if there is a match in Calendar/Booking service then Castle field "bookedOrAvailable" is changed to "booked" 

		Castle newCastle = castle.get();
		try {
		String bookingString = calendarProxy.getBookingByCastleid(id, date);		// %$%
		System.out.println("inside try/catch --> " + bookingString);
//		Castle newCastle = castle.get();
		//newCastle.setBookings(forEntity.getBody());
		
		//no 404 (i.e. a booking of that Castle exists on that date so can set field to "booked")
		newCastle.setBookedOrAvailable("booked (checked with calendar microservice - found a booking in its DB)");
		} catch(FeignException exception) {
			//catches the 404 Not Found and so setBookedOrAvailable remains at the default value of "available"
			System.out.println("inside catch part");
			System.out.println(exception.getMessage());
			//throw new CastleNotFoundException("Oh no!! Something has gone wrong! ID: " + id + " not found");
			
		}
		//System.out.println("*!*!" + bookingString);
		System.out.println("*!*!!!!!!!*!*!" );
		
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/3", String.class);
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/55/date/2017-02-25", String.class);
		
		
		//try-catch block
		//https://stackoverflow.com/questions/16194014/spring-mvc-resttemplate-launch-exception-when-http-404-happens
		//need try catch block in case 404 Not Found is returned
		//checks if a certain Castle is booked on a certain date - so needs to call Calendar/Booking service to check if there is a match
		//if there is no match then the default Castle field "bookedOrAvailable "remains at "available"
		//if there is a match in Calendar/Booking service then Castle field "bookedOrAvailable" is changed to "booked" 
//		try {
//			ResponseEntity<String> forEntity = 
//					new RestTemplate().getForEntity("http://localhost:8000/bookings-by-castle-id/"+id+"/date/"+date, String.class);
//			
//			Castle newCastle = castle.get();
//			//newCastle.setBookings(forEntity.getBody());
//			newCastle.setBookedOrAvailable("booked");
//			
//			System.out.println("*@* " + forEntity);
//			System.out.println("***!*** " + forEntity.getBody());
//			
//		} catch (final HttpClientErrorException e) {
//			// TODO: handle exception
//			System.out.println(e.getStatusCode());
//		    System.out.println(e.getResponseBodyAsString());
//		}
		
		//Booking aBooking = new Booking();
		
		//Castle newCastle = castle.get();
		//newCastle.setBookings(forEntity.getBody());
		//newCastle.setBookedOrAvailable("ok");

		
		//return castle.get();
		return newCastle;
	}
		
	private String removeQuotesAndUnescape(String uncleanJson) {
		System.out.println(" before noQuotes === " + uncleanJson);
	    //String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");
		//String noQuotes = uncleanJson.replaceAll("(?:[^\"]*\"){4}", "");
		
		//currently have: {"id":3,"dateBooked":"2018-02-25","eircode":"S95N7K7","environment":"9000","castle_id":2}
		//remove 							   " 	and   " from above (5th and 6th)
		
		System.out.println(uncleanJson.length());
		
		int quotationMarkCount = 0;
		for(int i = 0; i < uncleanJson.length(); i++) {
			if(uncleanJson.charAt(i)==34) {
				quotationMarkCount++;
			}
			if((quotationMarkCount==5&&uncleanJson.charAt(i)==34)|(quotationMarkCount==6&&uncleanJson.charAt(i)==34)) {
				uncleanJson = uncleanJson.substring(0,i) 
						+ 
						uncleanJson.substring(i+1)
						;
				
			}		
		}
		System.out.println(uncleanJson);
		
	    
		
		//System.out.println("noQuotes === " + noQuotes);
	    //return StringEscapeUtils.unescapeJava(noQuotes);
		return uncleanJson;
	}
	
	//delete a castle by id
	//@CrossOrigin(origins = "http://localhost:4200")	//this deals with the CORS issue when trying to access API from frontend React app
	@DeleteMapping("castles/{id}")
	public String deleteACastle(@PathVariable Integer id) throws RuntimeException{
		Optional<Castle> castle = castleRepository.findById(id);
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("id is: " + id);
		} else {
			castleRepository.deleteById(id);
			return "Castle with id " + id + " has been deleted";
		}
		
	}
	
	//create a castle - auto generates the id
	@PostMapping("castles")
	public ResponseEntity<Object> addACastle(@Valid @RequestBody Castle newCastle){
		Castle savedCastle = castleRepository.save(newCastle);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCastle.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();	
	}
	
	@PutMapping("castles/{id}")
	public ResponseEntity<Castle> updateACastle(@PathVariable Integer id, @RequestBody Castle updatedCastle) 
				//throws RuntimeException 
				{
		Optional<Castle> aFoundCastle = castleRepository.findById(id);
		if(!aFoundCastle.isPresent()){
			//throw new CastleNotFoundException("not found. ID is: " + id);
			Castle savedCastle = castleRepository.save(updatedCastle);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedCastle.getId())
					.toUri();
				
			return ResponseEntity.created(location).build();
			
			
		} else {
			Castle foundCastle = aFoundCastle.get();
			foundCastle.setDescription(updatedCastle.getDescription());
			foundCastle.setPrice(updatedCastle.getPrice());
			castleRepository.save(foundCastle);
			return new ResponseEntity<Castle>(foundCastle, HttpStatus.OK);
		}
		
	}
	
	
	//derived query to get all castles under certain length
	@GetMapping("max-length/{length}")
	public List<Castle> getCastlesMinLength(@PathVariable Double length){
		return castleRepository.findByLengthLessThan(length);
	}
	
}
