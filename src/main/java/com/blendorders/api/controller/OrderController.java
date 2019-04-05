package com.blendorders.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blendorders.api.beans.Order;

@RestController("orders")
public class OrderController {

	@GetMapping
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		return orders;
	}
	
}
