package com.microservicesproject.order_service.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.microservicesproject.order_service.DTO.InventoryResponseHandlerDTO;
import com.microservicesproject.order_service.DTO.OrderItemDTO;
import com.microservicesproject.order_service.DTO.OrderRequestDTO;
import com.microservicesproject.order_service.MessagingServices.OrderPlacedEvent;
import com.microservicesproject.order_service.Model.OrderItem;
import com.microservicesproject.order_service.Model.Orders;
import com.microservicesproject.order_service.Repository.OrderRepository;


@Service
@Transactional()
public class OrderService implements OrderServiceAPIS{

    private final OrderRepository repo;
    final RestTemplate restTemplate;
    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
    

    @Autowired
    private EmailService emailService;

    private static final String TOPIC = "order_placed";
    OrderService(OrderRepository repo, RestTemplate restTemplate){
        this.repo = repo;
        this.restTemplate = restTemplate;
		 
		 
    }
     

    private OrderItem mapOrderItemToOrderItemDTO(OrderItemDTO orderItemDTO){
        OrderItem item = new OrderItem();
        item.setId(orderItemDTO.getId());
        item.setItemCode(orderItemDTO.getItemCode());
        item.setPrice(orderItemDTO.getPrice());
        item.setQuantity(orderItemDTO.getQuantity());
        return item;
    }


    public String placeOrder(OrderRequestDTO orderRequestDTO) {
        Orders order = new Orders();
        order.setId(orderRequestDTO.getId());
        order.setOrderItemList(orderRequestDTO.getOrderItemList()
                .stream()
                .map(this::mapOrderItemToOrderItemDTO)
                .collect(Collectors.toList()));

        for (OrderItem item : order.getOrderItemList()) {
            String inventoryUrl = "http://localhost:8081/api/inventory/" + item.getItemCode();

            try {
                InventoryResponseHandlerDTO inventoryResponse = restTemplate.getForObject(inventoryUrl, InventoryResponseHandlerDTO.class);
                if (inventoryResponse != null && inventoryResponse.getQuantity() > item.getQuantity()) {
                    repo.save(order);
                    OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
                    orderPlacedEvent.setOrderId(order.getId());
                    orderPlacedEvent.setOrderItems(order.getOrderItemList().stream().map(items -> {
                        Map<String, Object> orderItemMap = new HashMap<>();
                        orderItemMap.put("itemCode", items.getItemCode());
                        orderItemMap.put("quantity", items.getQuantity());
                        return orderItemMap;
                    }).collect(Collectors.toList()));

                    kafkaTemplate.send(TOPIC, orderPlacedEvent);
                    emailService.sendOrderCompletionEmail("amit.tiwari912@gmail.com", "Order Placed ");
                } else {
                   
                    return "Failed to place order: Insufficient inventory for item: " + item.getItemCode();
                }
            } catch (RestClientException e) {
                System.err.println("Error while contacting inventory service: " + e.getMessage());
                e.printStackTrace();
                return "Failed to place order: Error contacting inventory service.";
            } catch (Exception e) {
                System.err.println("Unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                return "Failed to place order: Unexpected error.";
            }
        }

        return "Order placed successfully.";
    }


	@Override
	public Orders createOrder(Orders order) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Orders getOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Orders updateOrderStatus(Long id, String status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Orders> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}



}
