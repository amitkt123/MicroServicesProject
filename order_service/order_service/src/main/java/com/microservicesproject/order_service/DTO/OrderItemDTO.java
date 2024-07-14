package com.microservicesproject.order_service.DTO;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private Long id;
    private String itemCode;
    private BigDecimal price;
    private Integer quantity;

}
