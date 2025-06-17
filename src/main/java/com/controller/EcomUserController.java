package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.UserEntity;
import com.repository.EcomUserRepository;
import com.service.fileUploadService;
import com.utils.Validators;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EcomUserController 
{
	@Autowired
	fileUploadService fileUploadService;
	
	@Autowired
	EcomUserRepository userdao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/esignup")
	public UserEntity signupPost( @RequestParam("first_name") String firstName,
	        @RequestParam("email") String email,
	        @RequestParam("password") String password,
	        @RequestParam("profilePicture") MultipartFile profilePicture,   Model model) 
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
			return user;

//		}
	}
	
//	@GetMapping("elogin")
//	public String Elogin() 
//	{
//		
//		return "EcomLogin";
//	}
//	@PostMapping("/elogin")
//	public String EcomLogin(UserEntity user , Model model , HttpSession session) 
//	{
//		
//		UserEntity dbUser = userdao.findByEmailAndPassword(user.getEmail(), user.getPassword());
//		
//		if(dbUser==null)
//		{
//			model.addAttribute("error","Inavlid Credentials");
//			return "EcomLogin";
//		}
//		else
//		{
//			session.setAttribute("user", dbUser);
//			System.out.println(dbUser.getFirst_name());
//			System.out.println(dbUser.getProfile_picture_path());
//			model.addAttribute("firstName", dbUser.getFirst_name());
//			model.addAttribute("profilePicturePath",dbUser.getProfile_picture_path());
//			return "Welcome";
//		}
//	}
	
//	@GetMapping("/updatepassword")
//	public String updatePassword()
//	{
//		return "ForgetPassword";
//	}
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
