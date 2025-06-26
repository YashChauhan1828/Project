package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Config.CloudinaryConfig;
import com.DTO.EcomProductRequest;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.entity.EcomProductEntity;
import com.repository.EcomProductRepository;
import com.service.fileUploadService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/public")
public class EcomAdminController 
{

    
	@Autowired
	fileUploadService fileuploadservice;
	
	@Autowired
	Cloudinary cloudinary;
	
	@Autowired
	EcomProductRepository productdao;

    
	
	
	
	@PostMapping("/saveproduct")
	public String saveProduct(@RequestParam("productName") String productName,
			@RequestParam("category") String category, @RequestParam("price") Integer price,
			@RequestParam("qty") Integer qty, @RequestParam("productImage") MultipartFile productImage) 
	{
		Map result;
		try {
			result = cloudinary.uploader().upload(productImage.getBytes(), ObjectUtils.emptyMap());
			EcomProductEntity productentity = new EcomProductEntity();
			productentity.setProductName(productName);
			productentity.setCategory(category);
			productentity.setQty(qty);
			productentity.setPrice(price);
			productentity.setProductImagePath(result.get("url").toString());
			productdao.save(productentity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return "Success";
	}
	
	@GetMapping("/products")
	public ResponseEntity<?> Products() 
	{
		List<EcomProductEntity> products = productdao.findAll();
		Map<String, Object> response = new HashMap<>();
		response.put("products", products);
		return ResponseEntity.ok(response); 
	}
//	
//	@GetMapping("/viewproduct")
//	public String viewProduct(EcomProductEntity productbean,Model model)
//	{
//		Optional<EcomProductEntity> product = productdao.findById(productbean.getProductId());
//		if(product.isEmpty())
//		{
//			return"Error";
//		}
//		else
//		{
//			model.addAttribute("product",product.get());
//			return "ViewProduct";
//		}
//	}
//	
//	@GetMapping("/deleteproducts")
//	public String deleteProducts(EcomProductEntity productbean)
//	{
//		productdao.deleteById(productbean.getProductId());
//		return "redirect:/products";
//	}
//	@GetMapping("/editproducts")
//	public String editProducts(EcomProductEntity productbean , Model model) 
//	{
//		Optional<EcomProductEntity> product = productdao.findById(productbean.getProductId());
//		if(product.isEmpty())
//		{
//			return"Error";
//		}
//		else
//		{
//		model.addAttribute("product",product.get());
//		return "EditProduct";
//		}
//	}
//	@PostMapping("/updateproduct")
//	public String updateProduct(EcomProductEntity productentity , EcomProductRequest productrequest,Model model)
//	{
//		fileuploadservice.uploadproductImage(productrequest.getProductImage());
//		String filepath = "images//products//"+productrequest.getProductImage().getOriginalFilename();
//		productentity.setProductImagePath(filepath);
//		productdao.save(productentity);
//		return "redirect:/products";
//	}
}
