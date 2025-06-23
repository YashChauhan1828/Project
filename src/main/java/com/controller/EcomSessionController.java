package com.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.repository.EcomUserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class EcomSessionController 
{
	@Autowired
	EcomUserRepository userdao;
	
	
	@GetMapping("/ehome")
	public ResponseEntity<?> homePage(HttpSession session) 
	{
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		Map<String, Object> response = new HashMap<>();
//		String name = user.getFirst_name();
		response.put("firstName", user.getFirst_name());
		 try {
		        String imagePath = "D:/sts/project-hibernate/src/main/webapp/" + user.getProfile_picture_path();
		        Path path = Paths.get(imagePath);
		        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
		        String mimeType = Files.probeContentType(path);
		        
		        response.put("profileImageBase64", "data:" + mimeType + ";base64," + base64Image);
		    } catch (IOException e) {
		        response.put("profileImageBase64", null);
		    }
	    
//		response.put("profilePicturePath", "http://localhost:9999/api/user/profile-image");
		return ResponseEntity.ok(response);
	}
//	@GetMapping("/api/user/profile-image")
//	public ResponseEntity<FileSystemResource> getUserProfileImage(HttpSession session) {
//	    UserEntity user = (UserEntity) session.getAttribute("user");
//
//	    if (user == null) {
//	        return ResponseEntity.status(401).build();
//	    }
//
//	    // Build absolute path to file
//	    String imagepath = "D:/sts/project-hibernate/src/main/webapp/" + user.getProfile_picture_path();
//	    Path path = Paths.get(imagepath);
//
//	    if (!Files.exists(path)) {
//	        return ResponseEntity.notFound().build();
//	    }
//
//	    FileSystemResource resource = new FileSystemResource(path.toFile());
//	    String mimeType = guessMimeType(imagepath);
//
//	    return ResponseEntity.ok()
//	            .header("Content-Type", mimeType)
//	            .body(resource);
//	}
//
//	private String guessMimeType(String path) {
//	    if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
//	    if (path.endsWith(".png")) return "image/png";
//	    return "application/octet-stream";
//	}

}
