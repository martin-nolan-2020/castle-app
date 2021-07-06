package com.martinnolan.castlemanager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastleController {

	@GetMapping("first-test")
	public String getText() {
		return "this is the first test.";
	}
	
}
