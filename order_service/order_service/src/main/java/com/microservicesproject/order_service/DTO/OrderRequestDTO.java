package com.microservicesproject.order_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OrderRequestDTO {
    private Long id;
    private List<OrderItemDTO> orderItemList;

}
