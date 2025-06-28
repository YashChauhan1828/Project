package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomPaymentRequest;
import com.entity.EcomCartEntity;
import com.entity.EcomCartItemEntity;
import com.entity.UserEntity;
import com.repository.CartRepository;
import com.repository.EcomCartRepository;
import com.repository.EcomProductRepository;
import com.repository.EcomShippingRepository;
import com.repository.EcomUserRepository;
import com.service.Paymentservice;


import jakarta.servlet.http.HttpSession;

@RestController
public class EcomPaymentController 
{
	@Autowired
	Paymentservice paymentservice; 
	
	@Autowired
	EcomUserRepository userdao;
	
	@Autowired
	CartRepository cartdao;
	
	@Autowired
	EcomCartRepository cartitemdao;
	
	@Autowired
	EcomProductRepository productdao;
	
	@PostMapping("/epayment")
	public String Epayment(@RequestBody EcomPaymentRequest paymentbean,HttpSession session)
	{
		UserEntity userBean = (UserEntity)session.getAttribute("user");
		String email = userBean.getEmail();
		Integer userId = userBean.getUser_id();
		UserEntity user = userdao.findById(userId).orElseThrow();
		EcomCartEntity cart = cartdao.findByUser(user);
		List<EcomCartItemEntity> cartItems = cartitemdao.findByCart(cart);
		for(EcomCartItemEntity item : cartItems)
		{
			productdao.reduceProductQuantity(item.getQty(),item.getProduct().getProductId(),item.getQty());
		}
		paymentservice.run(paymentbean, email);
		return"Sucess";
	}
}
