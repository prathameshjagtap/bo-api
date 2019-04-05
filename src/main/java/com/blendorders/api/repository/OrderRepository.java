package com.blendorders.api.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.blendorders.api.beans.Order;

public interface OrderRepository  {
	public List<Order> getCurrentOrders(String clientId);
}
