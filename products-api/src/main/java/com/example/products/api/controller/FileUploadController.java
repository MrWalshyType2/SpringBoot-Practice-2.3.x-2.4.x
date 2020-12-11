package com.example.products.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {
		try {
			
			String workingDirectory = System.getProperty("user.dir");
			Path filePath = Paths.get(workingDirectory + File.separator 
									  + "tmp" + File.separator + file.getOriginalFilename());
			
			File dirTest = new File(Paths.get(workingDirectory + File.separator + "tmp").toAbsolutePath().toString());
			File convertFile = new File(filePath.toAbsolutePath().toString());

			// Check if dir exists, create if not
			if (!dirTest.isDirectory()) { if (!dirTest.mkdirs()) dirTest.mkdir(); };
			
			// check if file already exists, throw Exception if so
			if (!convertFile.exists()) convertFile.createNewFile();
			else throw new FileAlreadyExistsException("File already exists: " + convertFile.getName());
			
			FileOutputStream fOut = new FileOutputStream(convertFile);
			fOut.write(file.getBytes());
			fOut.close();
			return new ResponseEntity<String>("File uploaded successfully.", HttpStatus.ACCEPTED);
			
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/download")
	public ResponseEntity<Object> downloadFile() {
		try {
			String workingDirectory = System.getProperty("user.dir");
			Path filePath = Paths.get(workingDirectory + File.separator 
									  + "tmp" + File.separator + "quiz_27_05_2020.txt");
			File file = filePath.toFile();
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders headers = new HttpHeaders();
			
			headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
			
			ResponseEntity<Object> responseEntity = ResponseEntity.ok()
																  .headers(headers)
																  .contentLength(file.length())
																  .contentType(MediaType.parseMediaType("application/txt"))
																  .body(resource);
			return responseEntity;
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
