package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.EmailService;

@RestController
public class EcomMailVerficationController 
{
	@Autowired
	EmailService emailservice;
	
//	@GetMapping("/inputmail")
//	public String getMethodName() 
//	{
//		return "InputMail";
//	}
	@PostMapping("/sendmail")
	public String sendMail(@RequestParam("email") String email)
	{
		emailservice.sendDemoMail(email, "HI welcome to ABCD");
		return "Sucess";
	}
}
