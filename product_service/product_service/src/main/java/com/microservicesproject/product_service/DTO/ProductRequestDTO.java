package com.microservicesproject.product_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
	private String itemCode;
    private String name;
    private String description;
    private BigDecimal price;
}
