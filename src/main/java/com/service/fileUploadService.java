package com.service;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class fileUploadService
{
	public void uploadproductImage(MultipartFile masterImage) {
		System.out.println("Image: "+masterImage.getOriginalFilename() );
		String path = "D:\\sts\\project-hibernate\\src\\main\\webapp\\images\\products";
		try 
		{
			byte b[] = masterImage.getBytes();
			File file = new File(path,masterImage.getOriginalFilename());
			FileUtils.writeByteArrayToFile(file, b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void uploadUserImage(MultipartFile masterImage , String email) {
		System.out.println("Image: "+masterImage.getOriginalFilename() );
		String path = "D:\\sts\\project-hibernate\\src\\main\\webapp\\images\\profilepicture";
		File dir = new File(path,email);
		dir.mkdir();
		try 
		{
			byte b[] = masterImage.getBytes();
			File file = new File(dir,masterImage.getOriginalFilename());
			FileUtils.writeByteArrayToFile(file, b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}