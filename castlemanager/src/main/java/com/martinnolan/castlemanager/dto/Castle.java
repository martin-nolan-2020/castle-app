package com.martinnolan.castlemanager.dto;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Castle means each unit for hire")
@Entity
public class Castle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //https://stackoverflow.com/questions/49813666/table-dbname-hibernate-sequence-doesnt-exist
	private int id;
	
	@NotNull(message = "Identifier cannot be null")
	@ApiModelProperty(notes = "Identifier cannot be null")
	private String identifier;
	
	@Size(min = 2)
	@ApiModelProperty(notes = "Name must have at least 2 characters")
	private String name;
	
	@Size(min = 2)
	@ApiModelProperty(notes = "Description must have at least 2 characters")
	private String description;
	
	@OneToMany(targetEntity=Note.class, mappedBy="castle", fetch=FetchType.EAGER)
	private List<Note> notes;
	
	@DecimalMin(value = "0.0", inclusive = false)	//https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@DecimalMax(value = "100.0", inclusive = false)  //https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@ApiModelProperty(notes = "Length can be between 0.1 and 99.9")
	
	private double length;
	@DecimalMin(value = "0.0", inclusive = false)	//https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@DecimalMax(value = "100.0", inclusive = false)	//https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@ApiModelProperty(notes = "Width can be between 0.1 and 99.9")
	
	private double width;
	@DecimalMin(value = "0.0", inclusive = false)	//https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@DecimalMax(value = "1000.0", inclusive = false)	//https://www.tabnine.com/code/java/classes/javax.validation.constraints.DecimalMax
	@ApiModelProperty(notes = "Price can be between 0.1 and 999.9")
	private double price;
//	@OneToMany(targetEntity=Damage.class, mappedBy="castle", fetch=FetchType.EAGER)	
//	private List<String> damage;
	
	public Castle() {
		super();
	}

	public Castle(String identifier, String name, String description, List<Note> notes, double length,
			double width, double price) {
		super();
		//this.id = id;
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.notes = notes;
		this.length = length;
		this.width = width;
		this.price = price;
		//this.damage = damage;
	}
	
	

	public int getId() {
		return id;
	}
	
	

//	public Castle(String identifier, String name, String description, double length, double width, double price) {
//	super();
//	this.identifier = identifier;
//	this.name = name;
//	this.description = description;
//	this.length = length;
//	this.width = width;
//	this.price = price;
//}

//	public Castle(int id, String identifier, String name, String description, double length, double width, double price) {
//	super();
//	this.id = id;
//	this.identifier = identifier;
//	this.name = name;
//	this.description = description;
//	this.length = length;
//	this.width = width;
//	this.price = price;
//}

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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

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

//	@Override
//	public String toString() {
//		return "Castle [id=" + id + ", identifier=" + identifier + ", name=" + name + ", description=" + description
//				+ ", length=" + length + ", width=" + width + ", price=" + price + "]";
//	}

//	public List<String> getDamage() {
//		return damage;
//	}
//
//	public void setDamage(List<String> damage) {
//		this.damage = damage;
//	}

	@Override
	public String toString() {
		return "Castle [id=" + id + ", identifier=" + identifier + ", name=" + name + ", description=" + description
				+ ", notes=" + notes + ", length=" + length + ", width=" + width + ", price=" + price + "]";
	}
	
	

	
	
	
	
	
}
