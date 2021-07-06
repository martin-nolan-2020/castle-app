package com.martinnolan.castlemanager.dto;

import javax.persistence.*;


@Entity
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String info;
		
	public Note() {
		super();
	}

	public Note(int id, String info) {
		super();
		this.id = id;
		this.info = info;
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

	@Override
	public String toString() {
		return "Note [id=" + id + ", info=" + info + "]";
	}
	
	
}
