package com.microservicesproject.order_service.Service;

import java.util.List;

import com.microservicesproject.order_service.DTO.OrderRequestDTO;
import com.microservicesproject.order_service.Model.Orders;

public interface OrderServiceAPIS {
    Orders createOrder(Orders order);
    Orders getOrderById(Long id);
    Orders updateOrderStatus(Long id, String status);
    void deleteOrder(Long id);
    List<Orders> getAllOrders();
    public String placeOrder(OrderRequestDTO orderRequestDTO);
}