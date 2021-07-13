package com.martinnolan.castlemanager.controllers;

import java.net.URI;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.martinnolan.castlemanager.dao.CastleRepository;
import com.martinnolan.castlemanager.dto.Castle;

import com.martinnolan.castlemanager.exceptions.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@Validated
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
	
	//return all castles
	@GetMapping("castles")
	public List<Castle> getAllCastles(){
		return castleRepository.findAll();
	}
	
	//return a specific castle by id
	@GetMapping("castles/{id}")
	public EntityModel<Optional<Castle>> getCastleById(@PathVariable Integer id) throws RuntimeException {
		Optional<Castle> castle = castleRepository.findById(id);
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("id: " + id);
		}
		
		
		//implement HATEOAS
		//"all-castles", SERVER_PATH + "/castles"
		//getAllCastles
		
		//a resource rather than a castle is needed here so that the below link can be appended providing simple HATEOAS
		EntityModel<Optional<Castle>> resource = EntityModel.of(castle);
		
		//provides a link to all castles in what is returned to localhost:8080/castles/1
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getAllCastles());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;

	}
	
	//delete a castle by id
	@DeleteMapping("castles/{id}")
	public String deleteACastle(@PathVariable Integer id) throws RuntimeException{
		Optional<Castle> castle = castleRepository.findById(id);
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("id is: " + id);
		} else {
			castleRepository.deleteById(id);
			return "Castle with id " + id + " has been deleted";
		}
		
	}
	
	//create a castle - auto generates the id
	@PostMapping("castles")
	public ResponseEntity<Object> addACastle(@Valid @RequestBody Castle newCastle){
		Castle savedCastle = castleRepository.save(newCastle);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedCastle.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();	
	}
	
	//derived query to get all castles under certain length
	@GetMapping("max-length/{length}")
	public List<Castle> getCastlesMinLength(@PathVariable Double length){
		return castleRepository.findByLengthLessThan(length);
	}
	
}
