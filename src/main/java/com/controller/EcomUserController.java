package com.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.UserEntity;
import com.repository.EcomUserRepository;
import com.service.TokenService;
import com.service.fileUploadService;
import com.utils.Validators;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/public")
public class EcomUserController {
	@Autowired
	fileUploadService fileUploadService;

	@Autowired
	EcomUserRepository userdao;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	TokenService tokenservice;

	@PostMapping("/esignup")
	public ResponseEntity<?> signupPost(@RequestParam("first_name") String firstName,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("profilePicture") MultipartFile profilePicture, Model model) 
	{
		UserEntity userop = userdao.findByEmail(email);
		if (userop!=null) {
			Map<String, Object> response = new HashMap<>();
			response.put("sucess", false);
			response.put("message", "Email Already Exists");
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(response);
		} else 
		{
			String encryptedPassword = passwordEncoder.encode(password);

//		if(result.hasErrors())
//		{
//			model.addAttribute("result",result);
//			return "EcomSignUp";
//		}
//		else
//		{
			UserEntity user = new UserEntity();
			user.setFirst_name(firstName);
			user.setEmail(email);
			user.setPassword(encryptedPassword);

			fileUploadService.uploadUserImage(profilePicture, user.getEmail());

			String filePath = "images\\profilepicture\\" + user.getEmail() + "\\"
					+ profilePicture.getOriginalFilename();

			user.setProfile_picture_path(filePath);
			
			userdao.save(user);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			return ResponseEntity.ok(response);
		}

//		}
	}

	@PostMapping("/elogin")
	public ResponseEntity<?> EcomLogin(@RequestBody UserEntity user, Model model, HttpSession session) 
	{

		UserEntity dbUser = userdao.findByEmail(user.getEmail());

		if (dbUser==null || !passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			Map<String, Object> response = new HashMap<>();
			response.put("sucess", false);
			response.put("message", "Invalid Credentials");
			return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(response);
		} 
		else 
		{
			String authToken = tokenservice.generateToken();
			dbUser.setToken(authToken);
			userdao.save(dbUser);
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("message", "Login successful");
			response.put("token", dbUser.getToken());
			
			return ResponseEntity.ok(response);
		}
	}

	
//	
//	@PostMapping("/updatepassword")
//	public String EupdatePassword(@RequestParam("email") String email, @RequestParam("newpassword") String password,
//			@RequestParam("confirmpassword") String password2 , Model model )
//	{
//	boolean isError = false;
//	if(Validators.isBlank(password))
//	{
//		isError = true;
//		model.addAttribute("passwordError","PLease enter new password");
//
//	}
//	else if(Validators.isPass(password)==false)
//	{
//		isError = true;
//		model.addAttribute("passwordError","PLease enter valid password");
//
//	}
//	else if(password.matches(password2)==false)
//	{
//		isError = true;
//		model.addAttribute("passwordError","Password must match");
//	}
//	else
//	{
//		model.addAttribute("passwordValue",password);
//	}
//	if(Validators.isBlank(email))
//	{
//		isError = true;
//		model.addAttribute("emailError","PLease enter your Email");
//
//	}
//	else if(Validators.isEmail(email)==false)
//	{
//		isError = true;
//		model.addAttribute("emailError","PLease enter valid Email");
//	}
//	else
//	{
//		model.addAttribute("emailValue",email);
//	}
//	if(isError)
//	{
//		return "ForgetPassword";
//	}
//	else
//	{
//		UserEntity user = userdao.findByEmail(email);
//		user.setPassword(password);
//		userdao.save(user);
//		model.addAttribute("updatepassword", "Password Updated Sucessfully");
//		return "EcomLogin";
//	}
//}
//	
//	@GetMapping("/ehome")
//	public String eHome()
//	{
//		return "Welcome";
//	}
//	@GetMapping("/logout")
//	public String logout(HttpSession session)
//	{
//		session.invalidate();
//		return "redirect:/elogin";
//	}
}
