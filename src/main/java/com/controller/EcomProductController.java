package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.EcomProductRequest;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;
import com.service.fileUploadService;


@RestController
@RequestMapping("/api/public")
public class EcomProductController 
{
	@Autowired
	fileUploadService fileuploadservice;
	
	@Autowired
	EcomProductRepository productdao;
	
	@GetMapping("/userproducts")
	public ResponseEntity<?> userProducts(Model model)
	{
		List<EcomProductEntity> products = productdao.findTop9();
		Map<String, Object> response = new HashMap<>();
		
//			try {
//		        String imagePath = "D:/sts/project-hibernate/src/main/webapp/" + product.getProductImagePath();
//		        Path path = Paths.get(imagePath);
//		        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
//		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//		        String mimeType = Files.probeContentType(path);
//
//		        response.put("profileImageBase64", "data:" + mimeType + ";base64," + base64Image);
//		    } catch (IOException e) {
//		        response.put("profileImageBase64", null);
//		    }
		
		
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
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		}
		else
		{
			
			response.put("product",product.get());
			return ResponseEntity.ok(response);
		}
	}
	@GetMapping("/search")
	public String Search(@RequestParam("query") String query,Model model)
	{
		
		List<EcomProductEntity> products =productdao.findByCategoryContainingIgnoreCaseOrProductNameContainingIgnoreCase(query, query);
//		List<EcomProductEntity> product = productdao.findByProductNameContainingIgnoreCaseAndPriceLessThan(query, ;
		model.addAttribute("products", products);
//		model.addAttribute("products", product);
		return"SearchProducts";
	}
}
