package com.blog.payloads.jwt;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	private String password;
	
}
