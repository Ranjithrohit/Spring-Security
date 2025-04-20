package com.ranjith.service;

import com.ranjith.request.LoginRequest;
import com.ranjith.request.SignupRequest;
import com.ranjith.response.LoginResponse;

public interface AuthService {
	String register(SignupRequest request);
	LoginResponse login(LoginRequest request);
}
