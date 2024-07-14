package com.microservicesproject.order_service.MessagingServices;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderPlacedEventSerialiser implements Serializer<OrderPlacedEvent>{
	 

	@Override
	public byte[] serialize(String topic, OrderPlacedEvent data) {
		 ObjectMapper objectmapper = new ObjectMapper();
		 try {
			 return objectmapper.writeValueAsBytes(data);
		 }catch(JsonProcessingException e){
			 throw new SerializationException("Failed To serialise :", e);
			 
		 }
		 
	}

}
