package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.DTO.EcomProductRequest;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;
import com.service.fileUploadService;


@Controller
public class EcomProductController 
{
	@Autowired
	fileUploadService fileuploadservice;
	
	@Autowired
	EcomProductRepository productdao;
	
	@GetMapping("/newproduct")
	public String newProduct() 
	{
		return "EcomNewProduct";
	}
	
	@PostMapping("/saveproduct")
	public String saveProduct(EcomProductRequest productrequest , Model model) 
	{
		fileuploadservice.uploadproductImage(productrequest.getProductImage());
		String filepath = "images//products//"+productrequest.getProductImage().getOriginalFilename();
		EcomProductEntity productentity = new EcomProductEntity();
		productentity.setProductName(productrequest.getProductName());
		productentity.setCategory(productrequest.getCategory());
		productentity.setQty(productrequest.getQty());
		productentity.setPrice(productrequest.getPrice());
		productentity.setProductImagePath(filepath);
		productdao.save(productentity);
		return "redirect:/products";
	}
	
	@GetMapping("/products")
	public String Products(Model model) 
	{
		List<EcomProductEntity> products = productdao.findTop9();
		model.addAttribute("products", products);
		return"EcomListProducts"; 
	}
	
	@GetMapping("/userproducts")
	public String userProducts(Model model)
	{
		List<EcomProductEntity> products = productdao.findTop9();
		model.addAttribute("products", products);
		return"EcomHomeProducts";
	}
}
