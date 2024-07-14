package com.microservicesproject.inventory_service.Service;

import com.microservicesproject.inventory_service.DTO.InventoryResponseDTO;


public interface InventoryServiceAPIS {
    InventoryResponseDTO getInventoryByItemCode(String item_code);
    void addStock(String productId, int quantity);
    void reduceStock(String productId, int quantity);
    void reserveStock(String productId, int quantity);
    void releaseStock(String productId, int quantity);
    void adjustStock(String productId, int quantity);
}
