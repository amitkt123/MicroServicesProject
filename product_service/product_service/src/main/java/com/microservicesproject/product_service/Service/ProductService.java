package com.microservicesproject.product_service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservicesproject.product_service.DTO.ProductRequestDTO;
import com.microservicesproject.product_service.DTO.ProductResponseDTO;
import com.microservicesproject.product_service.MessagingServices.ProductCreatedEvent;
import com.microservicesproject.product_service.Model.Product;
import com.microservicesproject.product_service.ProductRepository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

/**
 *  @Author: Shaili Mishra
 */

@Service
@Slf4j
public class ProductService implements ProductServiceAPIS  {
	@Autowired
	final ProductRepo repo;
	  private static final String TOPIC = "product_created";
	@Autowired
	private  KafkaTemplate<String , ProductCreatedEvent> kafkatempl;

	ProductService(ProductRepo repository) {
		this.repo = repository;
	}
 
	
	public Optional<String> createProduct(ProductRequestDTO productDTO) {
	    try {
	        // Validate input
	        if (productDTO == null) {
	            return Optional.empty(); // Return empty Optional if productDTO is null
	        }

	        // Create Product object
	        Product product = Product.builder()
	                .name(productDTO.getName())
	                .description(productDTO.getDescription())
	                .price(productDTO.getPrice())
	                .itemCode(productDTO.getItemCode())
	                .build();

	        // Check if product already exists
	        if (!repo.checkIfItemExists(product.getItemCode())) {
	            // Save product to repository
	            Product savedProduct = repo.save(product);

	            // Publish event to Kafka
	            try {
	                ProductCreatedEvent productData = new ProductCreatedEvent(); 
	                productData.setItemCode(savedProduct.getItemCode());
	                kafkatempl.send(TOPIC, productData);
	                return Optional.of("Successfully created");
	            } catch (Exception e) {
	                // Handle Kafka publish error
	                e.printStackTrace(); // Example: Log the error
	                return Optional.of("Failed to publish to Kafka");
	            }
	        } else {
	            return Optional.of("Product already exists");
	        }
	    } catch (Exception e) {
	        // Handle general exception
	        e.printStackTrace(); // Example: Log the error
	        return Optional.of("Failed to save to DB");
	    }
	}


	private ProductResponseDTO mapToProductResponse(Product product) {
		return ProductResponseDTO.builder().id(product.getId()).price(product.getPrice()).name(product.getName())
				.description(product.getDescription()).build();

	}

	public List<ProductResponseDTO> getAllProducts() {
		List<Product> prod = repo.findAll();
		return prod.stream().map(this::mapToProductResponse).toList();
	}

	@Override
	public ProductResponseDTO getProductById(Long id) {

		return null;
	}

	@Override
	public ProductRequestDTO updateProduct(Long id, ProductRequestDTO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub

	}

}
