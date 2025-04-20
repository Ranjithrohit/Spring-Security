package com.ranjith.request;

public record SignupRequest(
		String email,
		String password,
		String username
	) {

}
