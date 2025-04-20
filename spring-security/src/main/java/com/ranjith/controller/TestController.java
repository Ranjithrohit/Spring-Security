package com.ranjith.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class TestController {

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		System.out.println("Test endpoint hit");
		return ResponseEntity.ok("Success");
	}
}
