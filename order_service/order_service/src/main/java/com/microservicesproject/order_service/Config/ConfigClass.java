package com.microservicesproject.order_service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigClass {


    @Bean
    RestTemplate restTemplate() {
		return new RestTemplate();
	}

    

}
