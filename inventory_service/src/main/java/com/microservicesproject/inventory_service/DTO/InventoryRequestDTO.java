package com.microservicesproject.inventory_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryRequestDTO {
	private long id;
	private String itemCode;
	private Integer quantity;
}
