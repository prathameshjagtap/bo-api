package com.blendorders.api.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.blendorders.api.beans.Order;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class DynamoOrderRepository implements OrderRepository {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	DynamoDB dynamoDB;
	
	@Autowired
	ObjectMapper mapper;
	
	private static final String CURRENT_ORDER_TABLE = "orders_current";
	
	public List<Order> getCurrentOrders(String clientId) {
		logger.info("+getCurrentOrders");
		
		Table table = dynamoDB.getTable(CURRENT_ORDER_TABLE);
		
		QuerySpec spec = new QuerySpec()
			    .withKeyConditionExpression("client_id = :client_id")
			    .withValueMap(new ValueMap()
			        .withString(":client_id", clientId));
		
		ItemCollection<QueryOutcome> items = table.query(spec);

		List<Order> orders = new ArrayList<>();
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
		    Item item = iterator.next();
		    Order order;
			try {
				order = mapper.readValue(item.toJSON(), Order.class);
			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Invalid object in DB", e);
				throw new RuntimeException("Invalid object in DB");
			}
		    
		    orders.add(order);
		}
		
		logger.info("-getCurrentOrders");
		return orders;
	}

}
