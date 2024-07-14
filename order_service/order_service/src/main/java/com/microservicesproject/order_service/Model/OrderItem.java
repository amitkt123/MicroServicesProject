package com.microservicesproject.order_service.Model;


import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;
    

    // Getters and Setters
}
