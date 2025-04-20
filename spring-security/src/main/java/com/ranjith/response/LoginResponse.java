package com.ranjith.response;

import lombok.Builder;

@Builder
public record LoginResponse(
	
		String token,
		long expiresIn
	) {

}
