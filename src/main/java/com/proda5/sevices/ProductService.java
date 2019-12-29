package com.proda5.sevices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.proda5.model.Product;
import com.proda5.repository.ProductRepo;

@Service
public class ProductService {
	
	
	@Autowired
	ProductRepo productRepo;
	@Autowired
	FileStorageService fileStorageService;
	
	public Product uploadProduct(MultipartFile[] files,Product product_temp) {
		
		if(files.length == 1) {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2("empty.jpg");
			product_temp.setSmallImage2("smallempty.jpg" );

			product_temp.setImage3("empty.jpg");
			product_temp.setSmallImage3("smallempty.jpg" );
			
		}
		else if(files.length == 2) {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2(files[1].getOriginalFilename());
			product_temp.setSmallImage2("small" +files[1].getOriginalFilename());
			fileStorageService.storeFile(files[1]);
			fileStorageService.storeSmallFile(files[1]);
			
			product_temp.setImage3("empty.jpg");
			product_temp.setSmallImage3("smallempty.jpg" );
			
		}
		else {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2(files[1].getOriginalFilename());
			product_temp.setSmallImage2("small" +files[1].getOriginalFilename());
			fileStorageService.storeFile(files[1]);
			fileStorageService.storeSmallFile(files[1]);
			
			product_temp.setImage3(files[2].getOriginalFilename());
			product_temp.setSmallImage3("small" +files[2].getOriginalFilename());
			fileStorageService.storeFile(files[2]);
			fileStorageService.storeSmallFile(files[2]);
			
		}
		productRepo.save(product_temp);
		return product_temp;
	}
	
	
	
	public Product updateProduct(MultipartFile[] files,Product product_temp) {
		
		Product temp = new Product();
		temp = productRepo.findByPid(product_temp.getPid());
		
		if(files.length == 1) {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2(temp.getImage2());
			product_temp.setSmallImage2(temp.getSmallImage2());

			product_temp.setImage3(temp.getImage3());
			product_temp.setSmallImage3(temp.getSmallImage3());
			
		}
		else if(files.length == 2) {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2(files[1].getOriginalFilename());
			product_temp.setSmallImage2("small" +files[1].getOriginalFilename());
			fileStorageService.storeFile(files[1]);
			fileStorageService.storeSmallFile(files[1]);
			
			product_temp.setImage3(temp.getImage3());
			product_temp.setSmallImage3(temp.getSmallImage3());
			
		}
		else {
			product_temp.setImage1(files[0].getOriginalFilename());
			product_temp.setSmallImage1("small" +files[0].getOriginalFilename());
			fileStorageService.storeFile(files[0]);
			fileStorageService.storeSmallFile(files[0]);
			
			product_temp.setImage2(files[1].getOriginalFilename());
			product_temp.setSmallImage2("small" +files[1].getOriginalFilename());
			fileStorageService.storeFile(files[1]);
			fileStorageService.storeSmallFile(files[1]);
			
			product_temp.setImage3(files[2].getOriginalFilename());
			product_temp.setSmallImage3("small" +files[2].getOriginalFilename());
			fileStorageService.storeFile(files[2]);
			fileStorageService.storeSmallFile(files[2]);
			
		}
		productRepo.save(product_temp);
		return product_temp;
	}
	
	
	
	
	
	
}
