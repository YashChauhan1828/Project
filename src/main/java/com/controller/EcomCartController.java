package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.EcomCartEntity;
import com.entity.EcomCartItemEntity;
import com.entity.EcomProductEntity;
import com.entity.UserEntity;
import com.repository.CartRepository;
import com.repository.EcomCartRepository;
import com.repository.EcomProductRepository;
import com.repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EcomCartController 
{
	@Autowired
	CartRepository cartdao;
	@Autowired
	EcomCartRepository cartitemdao;
	
	@Autowired
	EcomProductRepository productdao;

	@Autowired
	EcomUserRepository userdao;
	
	@GetMapping("/addtocart")
	public String addToCart(@RequestParam("productId") Integer productId,HttpSession session)
	{
		UserEntity userbean = (UserEntity)session.getAttribute("user");
		if(userbean == null)
		{
			return "EcomLogin";
		}
		else
		{
			Integer userId = userbean.getUser_id();
			UserEntity user = userdao.findById(userId).orElseThrow();  
			EcomCartEntity cart = cartdao.findByUser(user);
			if (cart == null) 
			{

				cart = new EcomCartEntity();
				cart.setUser(user);
				cartdao.save(cart);
			}
			EcomProductEntity product = productdao.findById(productId).orElseThrow();
			if (product != null) 
			{
				EcomCartItemEntity cartitem = new EcomCartItemEntity();
				cartitem.setProduct(product);
				cartitem.setCart(cart);
				cartitem.setQty(1);
				cartitemdao.save(cartitem);
			}
			return "redirect:/userproducts";
		}
	}
	
	@GetMapping("/mycart")
	public String myCart(HttpSession session, Model model ) 
	{
		UserEntity user = (UserEntity) session.getAttribute("user");
		if (user == null) 
		{
			return "EcomLogin";
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUser(user);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			model.addAttribute("products", products);
			return "MyCart";
		}
	}
	@GetMapping("/removecartitem")
	public String removeCartItem(@RequestParam("cartitemId") Integer cartId)
	{
		cartitemdao.deleteById(cartId);
		return "redirect:/mycart";
	}
	@GetMapping("/plusqty")
	public String plusQty(@RequestParam("cartitemId") Integer cartitemId) 
	{
		EcomCartItemEntity item = cartitemdao.findByCartitemId(cartitemId);
		item.setQty(item.getQty()+1);
		
		cartitemdao.save(item);
		return "redirect:/mycart";
	}
	@GetMapping("/minusqty")
	public String minusQty(@RequestParam("cartitemId") Integer cartitemId)
	{
		EcomCartItemEntity item = cartitemdao.findByCartitemId(cartitemId);
		if(item.getQty()!=1)
		{
			item.setQty(item.getQty() - 1);

			cartitemdao.save(item);
			return "redirect:/mycart";
		}
		else
		{
			cartitemdao.deleteById(cartitemId);
			return "redirect:/mycart";
		}
	}
	
}
