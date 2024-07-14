package com.microservicesproject.product_service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesproject.product_service.DTO.ProductRequestDTO;
import com.microservicesproject.product_service.DTO.ProductResponseDTO;
import com.microservicesproject.product_service.Service.ProductService;

import lombok.extern.slf4j.Slf4j;


 
@RestController
@RequestMapping(value = "/api/product")
@Slf4j
public class ProductController {
	
    private final ProductService service;
    ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> CreateProduct(@RequestBody ProductRequestDTO prod){
    	Optional<String > response = service.createProduct(prod);
    	return ResponseEntity.of(response);
  
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponseDTO> getAllProducts(){
        return service.getAllProducts();
    }



}
