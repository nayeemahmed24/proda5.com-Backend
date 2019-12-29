package com.proda5.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proda5.message.response.UploadFileResponse;
import com.proda5.model.Product;
import com.proda5.repository.ProductRepo;
import com.proda5.sevices.FileStorageService;
import com.proda5.sevices.ProductService;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	@Autowired
	FileStorageService fileStorageService;
	@Autowired
	ProductService productService;
	
	
	
	
	@PostMapping("/uploadProduct")
	@PreAuthorize("hasRole('SHOPOWNER')")
    public Product uploadProduct(@RequestPart("files") MultipartFile[] files,@RequestPart("product")Product product_temp) {		
		return productService.uploadProduct(files, product_temp);
    }
	
	@PutMapping("/updateProduct")
	@PreAuthorize("hasRole('SHOPOWNER')")
    public Product updateProduct(@RequestPart("files") MultipartFile[] files,@RequestPart("product")Product product_temp) {		
		return productService.updateProduct(files, product_temp);
    }
	
	
	
	
}
