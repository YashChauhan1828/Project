package com.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.entity.EcomCartEntity;
import com.entity.EcomCartItemEntity;
import com.entity.EcomOrderEntity;
import com.entity.EcomOrderItemEntity;
import com.entity.UserEntity;
import com.repository.CartRepository;
import com.repository.EcomCartRepository;
import com.repository.EcomOrderItemRepository;
import com.repository.EcomOrderRepository;
import com.repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EcomOrderController 
{
	@Autowired
	EcomUserRepository userdao;
	
	@Autowired
	EcomOrderRepository orderdao;
	
	@Autowired
	CartRepository cartdao;
	
	@Autowired
	EcomCartRepository cartitemdao;
	
	@Autowired
	EcomOrderItemRepository orderitemdao;
	
	@GetMapping("/orderhistory")
	public String orderHistory(EcomCartItemEntity cartItem,HttpSession session,Model model)
	{
		UserEntity userbean = (UserEntity)session.getAttribute("user");
		Integer userId = userbean.getUser_id();
		UserEntity user = userdao.findById(userId).orElseThrow();
		EcomCartEntity cart = cartdao.findByUser(user);
		List<EcomCartItemEntity> cartitems = cartitemdao.findByCart(cart);
		
		EcomOrderEntity order = new EcomOrderEntity();
	        order.setUser(user);
	    
	        order = orderdao.save(order);
	        order.setOrderitems(new ArrayList<>());
		
	        for (EcomCartItemEntity cartItem1 : cartitems) {
	            EcomOrderItemEntity orderItem = new EcomOrderItemEntity();
	            orderItem.setOrder(order);
	            orderItem.setQty(cartItem1.getQty());
	            orderItem.setProduct(cartItem1.getProduct());
	           
	            order.getOrderitems().add(orderItem);
	        }
	        
	        orderitemdao.saveAll(order.getOrderitems());
	        List<EcomOrderEntity> orders = orderdao.findByUser(user);
	        model.addAttribute("orders", orders);
	        
	        
		return"OrderHistory";
	}
}
