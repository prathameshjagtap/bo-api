package com.blendorders.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blendorders.api.beans.Order;
import com.blendorders.api.service.OrderService;

@RestController("orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping
	public List<Order> getOrders() {
		return orderService.getCurrentOrders();
	}
	
}
