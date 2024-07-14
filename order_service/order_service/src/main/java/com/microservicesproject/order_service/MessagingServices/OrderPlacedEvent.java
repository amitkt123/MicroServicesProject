package com.microservicesproject.order_service.MessagingServices;

 
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor


public class OrderPlacedEvent{
	private Long orderId;
	private List<Map<String,Object>> orderItems;
	private String status;

}
