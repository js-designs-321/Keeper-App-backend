package com.jatin.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatin.springboot.exception.ResouceNotFoundException;
import com.jatin.springboot.model.Note;
import com.jatin.springboot.repository.NoteRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class NoteController {
	
	@Autowired
	private NoteRepository noteRepository; 
	
	@GetMapping("/notes")
	public List<Note> getAllNotes(){
		return noteRepository.findAll(); 
	}
	
	@PostMapping("/notes")
	public Note createNote(@RequestBody Note note) {
		return noteRepository.save(note); 
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteNote(@PathVariable Long id){
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Note does not exist with id :" + id)); 
		noteRepository.delete(note);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
