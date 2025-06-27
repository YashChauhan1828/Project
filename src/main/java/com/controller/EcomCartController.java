package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.EcomCartEntity;
import com.entity.EcomCartItemEntity;
import com.entity.EcomProductEntity;
import com.entity.EcomWishCartItemEntity;
import com.entity.EcomWishList;
import com.entity.UserEntity;
import com.repository.CartRepository;
import com.repository.EcomCartRepository;
import com.repository.EcomProductRepository;
import com.repository.EcomUserRepository;
import com.repository.EcomWishItemRepository;
import com.repository.EcomWishRepository;

import jakarta.servlet.http.HttpSession;

@RestController
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

	@Autowired
	EcomWishRepository wishdao;
	
	@Autowired
	EcomWishItemRepository wishitemdao;
	
	@GetMapping("/addtocart/{productId}")
	public String addToCart(@PathVariable("productId") Integer productId, HttpSession session)
	{
		UserEntity userbean = (UserEntity) session.getAttribute("user");

		if (userbean == null) 
		{
			return "Fail";
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
			return "Success";
		}
	}

	@GetMapping("/addtowishcart/{productId}")
	public String addToWishCart(@PathVariable("productId") Integer productId,HttpSession session)
	{
		UserEntity userbean = (UserEntity)session.getAttribute("user");
		if(userbean==null)
		{
			return "User not found";
		}
		else
		{
			Integer userId = userbean.getUser_id();
			UserEntity user = userdao.findById(userId).orElseThrow();
			EcomWishList wishcart = wishdao.findByUser(user);
			if(wishcart==null)
			{
				wishcart = new EcomWishList();
				wishcart.setUser(user);
				wishdao.save(wishcart);
			}
			EcomProductEntity product = productdao.findById(productId).orElseThrow();
			if(product!=null)
			{
				EcomWishCartItemEntity wishitem = new EcomWishCartItemEntity();
				wishitem.setProduct(product);
				wishitem.setWishcart(wishcart);
				wishitemdao.save(wishitem); 
			}
			return "Success";
		}
	}
	
	@GetMapping("/mywishcart")
	public ResponseEntity<?> wishCart(HttpSession session)
	{
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if (user == null)
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} 
		else 
		{
			EcomWishList wishcart = wishdao.findByUser(user);
//			System.out.println(wishcart.getWishcartId());
			List<EcomWishCartItemEntity> products = wishitemdao.findByWishcart(wishcart);
			response.put("products", products);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/mycart")
	public ResponseEntity<?> myCart(HttpSession session, Model model) 
	{
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if (user == null)
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUser(user);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			response.put("products", products);
			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping("/removecartitem/{cartitemId}")
	public ResponseEntity<?> removeCartItem(@PathVariable("cartitemId") Integer cartId, HttpSession session) 
	{
		cartitemdao.deleteById(cartId);
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if (user == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUser(user);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			response.put("products", products);
			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping("/removewishcartitem/{wishcartitemId}")
	public ResponseEntity<?> removeWishCartItem(@PathVariable("wishcartitemId") Integer cartId, HttpSession session) 
	{
		wishitemdao.deleteById(cartId);
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if (user == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} 
		else 
		{
			EcomWishList wishcart = wishdao.findByUser(user);
			List<EcomWishCartItemEntity> products = wishitemdao.findByWishcart(wishcart);
			response.put("products", products);
			return ResponseEntity.ok(response);
		}
	}
	
	@PutMapping("/plusqty/{cartitemId}")
	public ResponseEntity<?> plusQty(@PathVariable("cartitemId") Integer cartitemId, HttpSession session) 
	{
		EcomCartItemEntity item = cartitemdao.findByCartitemId(cartitemId);
		item.setQty(item.getQty() + 1);
		cartitemdao.save(item);
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
		if (user == null) 
		{
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} 
		else 
		{
			EcomCartEntity cart = cartdao.findByUser(user);
			List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
			response.put("products", products);
			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/minusqty/{cartitemId}")
	public ResponseEntity<?> minusQty(@PathVariable("cartitemId") Integer cartitemId, HttpSession session) 
	{
		EcomCartItemEntity item = cartitemdao.findByCartitemId(cartitemId);
		Map<String, Object> response = new HashMap<>();
		if (item.getQty() != 1) 
		{
			item.setQty(item.getQty() - 1);
			cartitemdao.save(item);
			UserEntity user = (UserEntity) session.getAttribute("user");
			if (user == null) 
			{
				response.put("sucess", false);
				response.put("message", "Email Already Exists");
				return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
			} 
			else 
			{
				EcomCartEntity cart = cartdao.findByUser(user);
				List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
				response.put("products", products);
				return ResponseEntity.ok(response);
			}
		} 
		else 
		{
			cartitemdao.deleteById(cartitemId);
			UserEntity user = (UserEntity) session.getAttribute("user");
			if (user == null) 
			{
				response.put("sucess", false);
				response.put("message", "Email Already Exists");
				return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
			} 
			else 
			{
				EcomCartEntity cart = cartdao.findByUser(user);
				List<EcomCartItemEntity> products = cartitemdao.findByCart(cart);
				response.put("products", products);
				return ResponseEntity.ok(response);
			}
		}
		
	}
}
