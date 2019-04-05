package com.blendorders.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blendorders.api.beans.Order;
import com.blendorders.api.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	OrderRepository orderRepository;
	
	
	public List<Order> getCurrentOrders() {
		logger.info("+getCurrentOrders");
		
		String clientId = "abc123";
		
		List<Order> orders = orderRepository.getCurrentOrders(clientId);
		
		logger.info("-getCurrentOrders");

		return orders;
	}

}
