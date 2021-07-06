package com.martinnolan.castlemanager.dto;

import javax.persistence.*;

public class Damage {

	private int id;
	private String info;
		
	public Damage() {
		super();
	}

	public Damage(int id, String info) {
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
		return "Damage [id=" + id + ", info=" + info + "]";
	}
	
	
	
	
}
