package com.proda5.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.proda5.model.Product;

public interface ProductRepo extends CrudRepository<Product, String> {
	Product findByPid(String pid);
}
