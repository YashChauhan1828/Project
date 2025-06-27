package com.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.EcomProductEntity;
import com.entity.EcomReviewEntity;
import com.entity.UserEntity;
import com.repository.EcomProductRepository;
import com.repository.EcomReviewRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomReviewController 
{

	@Autowired
	EcomProductRepository productdao;
	
	@Autowired
	EcomReviewRepository reviewdao;
	
	@PostMapping("/review/{productId}")
	public ResponseEntity<?> reviewProduct(@PathVariable("productId") Integer productId,@RequestBody EcomReviewEntity review,HttpSession session)
	{
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if(user==null)
		{
			response.put("sucess", false);
			response.put("message", "Please Login");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}
		else
		{
			EcomProductEntity product = productdao.findById(productId).orElseThrow();
			review.setUser(user);
			review.setProduct(product);
			review.setCreatedAt(LocalDateTime.now());
			reviewdao.save(review);
			return ResponseEntity.ok("Review added");
		}
	}
	
	@GetMapping("/reviews/{productId}")
	public ResponseEntity<?> getReviews(@PathVariable("productId") Integer productId)
	{
		EcomProductEntity product = productdao.findById(productId).orElseThrow();
		List<EcomReviewEntity> reviews = reviewdao.findByProduct(product);
		return ResponseEntity.ok(reviews);
	}

}
