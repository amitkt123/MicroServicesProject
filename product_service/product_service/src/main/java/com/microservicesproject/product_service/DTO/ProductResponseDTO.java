package com.microservicesproject.product_service.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
	private String id;
	private String itemCode;
	private String name;
	private String description;
	private BigDecimal price;
}
