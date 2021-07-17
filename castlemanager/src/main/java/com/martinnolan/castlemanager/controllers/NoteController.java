package com.martinnolan.castlemanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.martinnolan.castlemanager.dao.CastleRepository;
import com.martinnolan.castlemanager.dto.Castle;
import com.martinnolan.castlemanager.dto.Note;
import com.martinnolan.castlemanager.exceptions.CastleNotFoundException;

@RestController
public class NoteController {

	@Autowired
	CastleRepository castleRepository;
	
	@GetMapping("/castles/{id}/notes")
	public List<Note> retrieveNotesByCastle(@PathVariable int id){
		Optional<Castle> castle = castleRepository.findById(id);
		
		if(!castle.isPresent()) {
			throw new CastleNotFoundException("Not found: id --> " + id);
		}
		
		return castle.get().getNotes();
	}
}
