package com.martinnolan.castlemanager.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.martinnolan.castlemanager.dao.CastleRepository;
import com.martinnolan.castlemanager.dto.Castle;

import exceptions.CastleNotFoundException;

@RestController
public class CastleController {
	
	@Autowired
	CastleRepository castleRepository;

	//below works
	@GetMapping("first-test")
	public String getText() {
		return "this is the first test.";
	}
	
	//below works
	@GetMapping("castle-bean")
	public Castle getCastleBean() {
		return new Castle("BS-2", "Big slide", "Blue slide", 30.0, 25.0, 160.0);	
	}
	
	@GetMapping("castles")
	public List<Castle> getAllCastles(){
		return castleRepository.findAll();
	}
	
	@GetMapping("castles/{id}")
	public Optional<Castle> getCastleById(@PathVariable Integer id) throws RuntimeException {
		Optional<Castle> castle = castleRepository.findById(id);
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("id: " + id);
		}
		return castle;
		
		
		//return castleRepository.findById(id);
	}
	
	@GetMapping("max-length/{length}")
	public List<Castle> getCastlesMinLength(@PathVariable Double length){
		return castleRepository.findByLengthLessThan(length);
	}
	
	@PostMapping("castles")
	public ResponseEntity<Object> addACastle(@RequestBody Castle newCastle){
		Castle savedCastle = castleRepository.save(newCastle);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCastle.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
		
		
	}
	
}
