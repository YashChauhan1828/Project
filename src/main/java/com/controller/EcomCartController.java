package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.entity.EcomCartItemEntity;
import com.entity.EcomProductEntity;
import com.entity.UserEntity;
import com.repository.EcomCartRepository;
import com.repository.EcomProductRepository;
import com.repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EcomCartController 
{
	@Autowired
	EcomCartRepository cartdao;
	
	@Autowired
	EcomProductRepository productdao;

	@Autowired
	EcomUserRepository userdao;
	
	@GetMapping("/addtocart")
	public String addToCart(@RequestParam("productId") Integer productId,HttpSession session)
	{
		UserEntity userbean = (UserEntity)session.getAttribute("user");
		Integer userId = userbean.getUser_id();
		System.out.println(productId);
		System.out.println(userId);
		EcomProductEntity product = productdao.findById(productId).orElseThrow();
		UserEntity  user = userdao.findById(userId).orElseThrow(); 
		EcomCartItemEntity cart = new EcomCartItemEntity();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQty(product.getQty());
		cartdao.save(cart);
		return "redirect:/userproducts";
	}
	
	@GetMapping("/mycart")
	public String myCart(HttpSession session, Model model ) 
	{
		List<EcomCartItemEntity> products = cartdao.findAll();
		model.addAttribute("products",products);
		return "MyCart";
	}
	
}
