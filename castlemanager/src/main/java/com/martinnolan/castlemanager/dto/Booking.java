package com.martinnolan.castlemanager.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Booking {

	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) //https://stackoverflow.com/questions/49813666/table-dbname-hibernate-sequence-doesnt-exist
	private int id;
	
	//@Column(name = "date_booked")
	private LocalDate dateBooked;
	private String eircode;
	//@Column(name = "castle_id")
	//private int castleId;		%$%
	private int castleid;
	//environment provides port information
	private String environment;
	
	public Booking() {
		super();
	}

	public Booking(int id, LocalDate date_booked, String eircode, int castleid) {
		super();
		this.id = id;
		this.dateBooked = date_booked;
		this.eircode = eircode;
		this.castleid = castleid;
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

	public void setDateBooked(LocalDate date_booked) {
		this.dateBooked = date_booked;
	}
	
	public String getEircode() {
		return eircode;
	}

	public void setEircode(String eircode) {
		this.eircode = eircode;
	}

	public int getCastleid() {
		return castleid;
	}

	public void setCastleid(int castleid) {
		this.castleid = castleid;
	}
	
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", dateBooked=" + dateBooked + ", eircode=" + eircode + ", castleId=" + castleid
				+ ", environment=" + environment + "]";
	}
	
	

//	@Override
//	public String toString() {
//		return "Booking [id=" + id + ", dateBooked=" + dateBooked + ", eircode=" + eircode + ", castle_id=" + castle_id
//				+ "]";
//	}
	
}
