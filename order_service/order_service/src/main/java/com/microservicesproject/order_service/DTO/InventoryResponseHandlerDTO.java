package com.microservicesproject.order_service.DTO;

 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseHandlerDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    // Add other fields as needed based on your response structure
}
