package com.microservicesproject.product_service.Service;
import java.util.List;
import java.util.Optional;

import com.microservicesproject.product_service.DTO.ProductRequestDTO;
import com.microservicesproject.product_service.DTO.ProductResponseDTO;

public interface ProductServiceAPIS {
    ProductResponseDTO getProductById(Long id);
    Optional<String> createProduct(ProductRequestDTO product);
    ProductRequestDTO updateProduct(Long id, ProductRequestDTO product);
    void deleteProduct(Long id);
    List<ProductResponseDTO> getAllProducts();
}
