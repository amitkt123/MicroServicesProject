package com.microservicesproject.inventory_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inventory {

	@Id
	private Long id;
	private String itemCode;
	private Integer quantity;
}
