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
import com.martinnolan.castlemanager.dao.NoteRepository;
import com.martinnolan.castlemanager.dto.Castle;
import com.martinnolan.castlemanager.dto.Note;
import com.martinnolan.castlemanager.exceptions.CastleNotFoundException;

@RestController
public class NoteController {

	@Autowired
	CastleRepository castleRepository;
	
	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping("/castles/{id}/notes")
	public List<Note> retrieveNotesByCastle(@PathVariable int id){
		Optional<Castle> castle = castleRepository.findById(id);

		if(!castle.isPresent()) {
			throw new CastleNotFoundException("Not found: id --> " + id);
		}
//		System.out.println("----------");
//		System.out.println(castle);
//		System.out.println("----------");
//		System.out.println(castle.get());
//		System.out.println("----------");
//		System.out.println(castle.get().getNotes());
//		System.out.println("----------");
		
		return castle.get().getNotes();
	}
	
	@PostMapping("/castles/{id}/notes")
	public ResponseEntity<Object> addANote(@PathVariable int id, @RequestBody Note newNote){
		Optional<Castle> castle = castleRepository.findById(id);
		
		
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("Not found: id --> " + id);
		} 
		
		Castle aCastle = castle.get();
		System.out.println("******** " + aCastle );
		newNote.setCastle(aCastle);
		System.out.println("******** " + newNote );
		System.out.println("******** " + newNote.getCastle() );
		//Note savedNote = noteRepository.save(newNote);
		noteRepository.save(newNote);
			
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newNote.getId())
				.toUri();
			
		return ResponseEntity.created(location).build();	
		
		//newNote.setCastle(castle);
		//Note savedNote = noteRepository.save(newNote);
		
		
	}
	
}
