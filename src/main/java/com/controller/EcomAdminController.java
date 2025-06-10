package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.DTO.EcomProductRequest;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;
import com.service.fileUploadService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EcomAdminController 
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
		List<EcomProductEntity> products = productdao.findAll();
		model.addAttribute("products", products);
		return"EcomListProducts"; 
	}
	
	@GetMapping("/viewproduct")
	public String viewProduct(EcomProductEntity productbean,Model model)
	{
		Optional<EcomProductEntity> product = productdao.findById(productbean.getProductId());
		if(product.isEmpty())
		{
			return"Error";
		}
		else
		{
			model.addAttribute("product",product.get());
			return "ViewProduct";
		}
	}
	
	@GetMapping("/deleteproducts")
	public String deleteProducts(EcomProductEntity productbean)
	{
		productdao.deleteById(productbean.getProductId());
		return "redirect:/products";
	}
	@GetMapping("/editproducts")
	public String editProducts(EcomProductEntity productbean , Model model) 
	{
		Optional<EcomProductEntity> product = productdao.findById(productbean.getProductId());
		if(product.isEmpty())
		{
			return"Error";
		}
		else
		{
		model.addAttribute("product",product.get());
		return "EditProduct";
		}
	}
	@PostMapping("/updateproduct")
	public String updateProduct(EcomProductEntity productentity , EcomProductRequest productrequest,Model model)
	{
		fileuploadservice.uploadproductImage(productrequest.getProductImage());
		String filepath = "images//products//"+productrequest.getProductImage().getOriginalFilename();
		productentity.setProductImagePath(filepath);
		productdao.save(productentity);
		return "redirect:/products";
	}
}
