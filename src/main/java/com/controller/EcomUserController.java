package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.DTO.EcomUserRequest;
import com.entity.UserEntity;
import com.repository.EcomUserRepository;
import com.service.fileUploadService;

@Controller
public class EcomUserController 
{
	@Autowired
	fileUploadService fileUploadService;
	
	@Autowired
	EcomUserRepository userdao;
	
	@GetMapping("/esignup")
	public String SignUp()
	{
		return "EcomSignUp";
	}
	
	@PostMapping("/esignup")
	public String signupPost(EcomUserRequest userRequest, Model model) {
	    boolean isError = false;

	    // Upload user image (save to file system)
	    if (userRequest.getProfilePicture() != null && !userRequest.getProfilePicture().isEmpty()) {
	        fileUploadService.uploadUserImage(userRequest.getProfilePicture(), userRequest.getEmail());

	        String filePath = "images\\profilepicture\\" + userRequest.getEmail() + "\\" + userRequest.getProfilePicture().getOriginalFilename();

	        // Store file path in entity
	        UserEntity user = new UserEntity();
	        user.setFirstName(userRequest.getFirstName());
	        user.setEmail(userRequest.getEmail());
	        user.setPassword(userRequest.getPassword());
	        user.setProfilePicturePath(filePath);

	        // Continue with validations...

	        userdao.save(user);
	        return "EcomLogin";
	    }

	    // handle if file is not uploaded
	    model.addAttribute("error", "Profile picture is required");
	    return "EcomSignUp";
	}

}
