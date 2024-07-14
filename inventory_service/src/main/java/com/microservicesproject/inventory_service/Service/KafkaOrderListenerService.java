package com.microservicesproject.inventory_service.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservicesproject.order_service.MessagingServices.OrderPlacedEvent;


@Service
public class KafkaOrderListenerService {

	@Autowired
	InventoryService service;

	  @KafkaListener(topics = "order_placed", groupId = "consumergroup")
	    public void listenOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {

	        System.out.println("Received order placed event: " + orderPlacedEvent);

	        updateInventory(orderPlacedEvent);
	    }



	  private void updateInventory(OrderPlacedEvent orderPlacedEvent) {
		 List<Map<String, Object>> orders=  orderPlacedEvent.getOrderItems();
		  for (Map<String, Object> map : orders) {
			    String itemCode = (String) map.get("itemCode");
			    Integer quantity = (Integer) map.get("quantity");

			    service.reduceStock(itemCode, quantity);
			}
	        System.out.println("Updating inventory for order: " + orderPlacedEvent.getOrderId());

	    }

}
