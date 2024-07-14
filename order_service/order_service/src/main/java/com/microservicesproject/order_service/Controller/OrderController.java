package com.microservicesproject.order_service.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesproject.order_service.DTO.OrderRequestDTO;
import com.microservicesproject.order_service.Service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{
    final OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

 
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        String resultMessage = orderService.placeOrder(orderRequestDTO);
        
        if (resultMessage.startsWith("Failed")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
        } else {
            return ResponseEntity.ok(resultMessage);
        }
    }
    
    
}
