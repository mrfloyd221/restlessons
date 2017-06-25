package com.jsonfloyd.Service;

import com.jsonfloyd.model.Order;

import java.util.List;


public interface IOrderService {
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(int userId);
    Order getOrderById(int orderId);
    boolean addOrder(Order order);
    void updateOrder(Order order) throws Exception;
    void deleteOrder(int orderId);

}
