package com.martinnolan.castlemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinnolan.castlemanager.dto.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
