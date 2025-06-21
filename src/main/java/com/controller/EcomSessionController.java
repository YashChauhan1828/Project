package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomSessionController 
{
	@Autowired
	EcomUserRepository userdao;
	
	
	@GetMapping("/ehome")
	public ResponseEntity<?> homePage(HttpSession session) 
	{
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
//		String name = user.getFirst_name();
		response.put("firstName", user.getFirst_name());
	    response.put("profilePicturePath", user.getProfile_picture_path());
		return ResponseEntity.ok(response);
	}
}
