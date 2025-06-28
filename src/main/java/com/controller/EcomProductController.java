package com.controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.EcomProductEntity;
import com.entity.EcomReviewEntity;
import com.entity.UserEntity;
import com.repository.EcomProductRepository;
import com.repository.EcomReviewRepository;
import com.service.fileUploadService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/public")
public class EcomProductController 
{
	@Autowired
	fileUploadService fileuploadservice;
	
	@Autowired
	EcomProductRepository productdao;
	
	@Autowired
	EcomReviewRepository reviewdao;
	
	@GetMapping("/userproducts")
	public ResponseEntity<?> userProducts(Model model)
	{
		List<EcomProductEntity> products = productdao.findTop9();
		Map<String, Object> response = new HashMap<>();
		response.put("products", products);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/userproductview/{productId}")
	public ResponseEntity<?> userProductView(@PathVariable("productId") Integer productId,EcomProductEntity productbean,Model model)
	{
		Optional<EcomProductEntity>	product=productdao.findById(productId);
		Map<String, Object> response = new HashMap<>();
		if(product == null)
		{
			response.put("sucess", false);
			response.put("message", "No Product Available");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}
		else
		{
			
			response.put("product",product.get());
			return ResponseEntity.ok(response);
		}
	}
	
		
	@GetMapping("/search")
	public ResponseEntity<?> Search(@RequestParam("query") String query,Model model)
	{
		
		List<EcomProductEntity> products =productdao.findByCategoryContainingIgnoreCaseOrProductNameContainingIgnoreCase(query, query);
		Map<String, Object> response = new HashMap<>();
		if(products == null)
		{
			response.put("sucess", false);
			response.put("message", "No Product Available");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}
		else
		{
			response.put("product",products);
			return ResponseEntity.ok(response);
		}
	}
}