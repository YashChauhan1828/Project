package com.controller;

import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/userproducts")
	public String userProducts(Model model)
	{
		List<EcomProductEntity> products = productdao.findTop9();
		model.addAttribute("products", products);
		return"EcomHomeProducts";
	}
	@GetMapping("/userproductview")
	public String userProductView(EcomProductEntity productbean,Model model)
	{
		Optional<EcomProductEntity>	product=productdao.findById(productbean.getProductId());
		if(product == null)
		{
			return "Error";
		}
		else
		{
			model.addAttribute("product",product.get());
			return"UserViewProduct";
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
