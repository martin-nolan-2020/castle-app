package com.martinnolan.castlemanager.dto;

import java.util.List;

import javax.persistence.*;

@Entity
public class Castle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String identifier;
	private String name;
	private String description;
//	@OneToMany(targetEntity=Note.class, mappedBy="castle", fetch=FetchType.EAGER)
//	private List<Note> notes;
	private double length;
	private double width;
	private double price;
//	@OneToMany(targetEntity=Damage.class, mappedBy="castle", fetch=FetchType.EAGER)	
//	private List<String> damage;
	
	public Castle() {
		super();
	}

//	public Castle(int id, String identifier, String name, String description, List<String> notes, double length,
//			double width, double price, List<String> damage) {
//		super();
//		this.id = id;
//		this.identifier = identifier;
//		this.name = name;
//		this.description = description;
//		this.notes = notes;
//		this.length = length;
//		this.width = width;
//		this.price = price;
//		this.damage = damage;
//	}
	
	

	public int getId() {
		return id;
	}

	public Castle(int id, String identifier, String name, String description, double length, double width, double price) {
	super();
	this.id = id;
	this.identifier = identifier;
	this.name = name;
	this.description = description;
	this.length = length;
	this.width = width;
	this.price = price;
}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<String> getNotes() {
//		return notes;
//	}

//	public void setNotes(List<String> notes) {
//		this.notes = notes;
//	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Castle [id=" + id + ", identifier=" + identifier + ", name=" + name + ", description=" + description
				+ ", length=" + length + ", width=" + width + ", price=" + price + "]";
	}

//	public List<String> getDamage() {
//		return damage;
//	}
//
//	public void setDamage(List<String> damage) {
//		this.damage = damage;
//	}

//	@Override
//	public String toString() {
//		return "Castle [id=" + id + ", identifier=" + identifier + ", name=" + name + ", description=" + description
//				+ ", notes=" + notes + ", length=" + length + ", width=" + width + ", price=" + price + ", damage="
//				+ damage + "]";
//	}
	
	

	
	
	
	
	
}
