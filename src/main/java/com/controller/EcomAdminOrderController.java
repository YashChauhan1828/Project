package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.EcomOrderEntity;
import com.entity.EcomOrderItemEntity;
import com.repository.EcomOrderItemRepository;

@RestController
@RequestMapping("/api/admin/")
public class EcomAdminOrderController 
{
	@Autowired
	EcomOrderItemRepository orderitemdao;
	
	@GetMapping("/update-status")
	public List<EcomOrderItemEntity> updateStatus()
	{
		return orderitemdao.findAll();
		 
	}

	@GetMapping("/update/{orderItemId}")
	public String status(@PathVariable("orderItemId") Integer orderItemId)
	{
		 
		EcomOrderItemEntity orderitem = orderitemdao.findById(orderItemId).orElseThrow();
		
		orderitem.setStatus("Delivered");
		orderitemdao.save(orderitem);
		
		return "Success";
	}

	
}
