package com.martinnolan.castlemanager.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "note")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String info;
	
	//each Note needs to belong to a Castle. A Castle can have many Notes. Therefore, it is a ManyToOne relationship.
	//FetchType.LAZY - this means that Note won't automatically try to fetch Castle and so avoid recursion type problems.
	//@JsonIgnore helps with recusrion
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Castle castle;
		
	public Note() {
		super();
	}

//	public Note(int id, String info, Castle castle) {
//		super();
//		this.id = id;
//		this.info = info;
//		this.castle = castle;
//	}
	
	public Note(String info, Castle castle) {
		super();
		//this.id = id;
		this.info = info;
		this.castle = castle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", info=" + info + "]";
	}

	
	
	
	
}
