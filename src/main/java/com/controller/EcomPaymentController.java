package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.DTO.EcomPaymentRequest;
import com.entity.UserEntity;
import com.repository.EcomShippingRepository;
import com.service.Paymentservice;


import jakarta.servlet.http.HttpSession;

@Controller
public class EcomPaymentController 
{
	@Autowired
	Paymentservice paymentservice; 
	
//	@Autowired
//	EcomShippingRepository shippingdao;
	
	@GetMapping("/payment")
	public String Payment()
	{
		return "Checkout";
	}
	@PostMapping("/epayment")
	public String Epayment(EcomPaymentRequest paymentbean,HttpSession session)
	{
		UserEntity userBean = (UserEntity)session.getAttribute("user");
		String email = userBean.getEmail();
		
		paymentservice.run(paymentbean, email);
		return"Sucess";
	}
}
