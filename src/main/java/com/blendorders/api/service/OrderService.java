package com.blendorders.api.service;

import java.util.List;

import com.blendorders.api.beans.Order;

public interface OrderService  {
	public List<Order> getCurrentOrders();
}
