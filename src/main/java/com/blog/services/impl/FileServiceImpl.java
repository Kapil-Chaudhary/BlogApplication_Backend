package com.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		
		// Steps
		
		// 1. File Name
		String name = file.getOriginalFilename(); // abc.png
		
				
		// 2. Full Path --> hum usi name se file ko save kar rhe hai jo uska orginal name hai 
		
		// 2.1 isme hum ab orginal name use nhi karenge 
		String randomID = UUID.randomUUID().toString(); 
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		String filePath = path + File.separator + fileName1;	
		
		
		
		
		// 3. Created folder if not created
		File f = new File(path); // so isme image tak ka path aa gya 
		if ( !f.exists() ) f.mkdir(); // if this path is not created then create that one folder, matlab hum folder tak ka path bana rhe hai 
		
		
		// 4. file Copy	
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		// 5. return name of that file
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
		// Database login to return inputStream 
		// here we do this if we what to do this in DB
		
		return is;
	}

	
	
}
