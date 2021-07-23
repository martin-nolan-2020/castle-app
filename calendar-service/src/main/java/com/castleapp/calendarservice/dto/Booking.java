package com.castleapp.calendarservice.dto;

import java.time.LocalDate;
import java.util.Date;

public class Booking {

	private int id;
	private LocalDate dateBooked;
	private String eircode;
	private int castle_id;
	
	public Booking() {
		super();
	}

	public Booking(int id, LocalDate dateBooked, String eircode, int castle_id) {
		super();
		this.id = id;
		this.dateBooked = dateBooked;
		this.eircode = eircode;
		this.castle_id = castle_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateBooked() {
		return dateBooked;
	}

	public void setDateBooked(LocalDate dateBooked) {
		this.dateBooked = dateBooked;
	}
	
	public String getEircode() {
		return eircode;
	}

	public void setEircode(String eircode) {
		this.eircode = eircode;
	}

	public int getCastle_id() {
		return castle_id;
	}

	public void setCastle_id(int castle_id) {
		this.castle_id = castle_id;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", dateBooked=" + dateBooked + ", eircode=" + eircode + ", castle_id=" + castle_id
				+ "]";
	}
	
}
