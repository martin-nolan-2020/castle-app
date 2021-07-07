package com.martinnolan.castlemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.martinnolan.castlemanager.dao.CastleRepository;
import com.martinnolan.castlemanager.dto.Castle;

@RestController
public class CastleController {
	
	@Autowired
	CastleRepository castleRepository;

	@GetMapping("first-test")
	public String getText() {
		return "this is the first test.";
	}
	
	@GetMapping("all")
	public List<Castle> getAllCastles(){
		return castleRepository.findAll();
	}
	
	@PostMapping("add")
	public void addACastle(@RequestBody Castle newCastle){
		castleRepository.save(newCastle);
	}
	
}
