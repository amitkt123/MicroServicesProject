package com.microservicesproject.inventory_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservicesproject.product_service.MessagingServices.ProductCreatedEvent;

@Service
public class KafkaProductListenerService {

	@Autowired
	InventoryService service;
	@KafkaListener(topics = "order_created", groupId = "consumergroup")
	public void listenProductCreatedEvent(ProductCreatedEvent event)
	{
		this.updateInventoryOnProductCreation(event);

	}
	
	private void updateInventoryOnProductCreation(ProductCreatedEvent event) {
		
		String itemCode = event.getItemCode();
		 
		service.addStock(itemCode, 1);
		
	}
}
