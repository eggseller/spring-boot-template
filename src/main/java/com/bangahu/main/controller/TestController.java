package com.bangahu.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bangahu.main.model.domain.User;
import com.bangahu.main.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	private final UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<?> getUser(String username) {
		User user = userService.selectUser(username);
		return ResponseEntity.ok(user);
	}
}
