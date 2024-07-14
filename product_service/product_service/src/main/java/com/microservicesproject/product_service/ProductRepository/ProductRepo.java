package com.microservicesproject.product_service.ProductRepository;

import com.microservicesproject.product_service.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepo extends MongoRepository<Product, String>, CustomProductRepo {
	
  
}
