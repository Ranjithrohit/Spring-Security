package com.ranjith.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranjith.request.LoginRequest;
import com.ranjith.request.SignupRequest;
import com.ranjith.response.LoginResponse;
import com.ranjith.response.LoginResponse.LoginResponseBuilder;
import com.ranjith.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<String> register(@RequestBody SignupRequest request){
		String userRequest = authService.register(request);
		return ResponseEntity.ok(userRequest);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
		LoginResponse response = authService.login(LoginRequest.builder()
				.email(request.email())
				.password(request.password())
				.build());
		LoginResponse apiResponse = LoginResponse.builder()
				.token(response.token())
				.expiresIn(response.expiresIn())
				.build();
		return ResponseEntity.ok(apiResponse);
		
		
	}
}
