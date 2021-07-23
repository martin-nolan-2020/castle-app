package com.castleapp.calendarservice.controllers;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.castleapp.calendarservice.dto.Booking;

@RestController
public class CalendarServiceController {

//	@GetMapping("/test")
//	public String aTest() {
//		return new String("Test is good"); 
//	}	
	
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
		return new Booking(1, convertToDate, "R95N6K6" ,castleId);
//		return null;
		
	}
	
}
