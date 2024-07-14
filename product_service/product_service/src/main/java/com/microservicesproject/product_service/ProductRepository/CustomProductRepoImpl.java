package com.microservicesproject.product_service.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.microservicesproject.product_service.Model.Product;

public class CustomProductRepoImpl implements CustomProductRepo{

	@Autowired
	MongoTemplate mongo;
	@Override
	public boolean checkIfItemExists(String itemCode) {
		  Query query = new Query();
	      query.addCriteria(Criteria.where("itemCode").is(itemCode));
	      return mongo.exists(query, Product.class);
		 
	}

}
