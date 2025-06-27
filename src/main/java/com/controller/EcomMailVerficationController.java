package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.EmailService;
import com.service.TokenService;

@RestController
public class EcomMailVerficationController 
{
	@Autowired
	EmailService emailservice;
	
	@Autowired
	TokenService tokenservice;
	
	@PostMapping("/sendmail")
	public String sendMail(@RequestParam("email") String email)
	{
		String otp = tokenservice.otp();
		System.out.println(otp);
		emailservice.sendDemoMail(email, "HI welcome to ABCD\nYour EmailVerification OTP is: "+otp);
		return otp;
	}
}
