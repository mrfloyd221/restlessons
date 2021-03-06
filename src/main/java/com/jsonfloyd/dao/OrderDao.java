package com.jsonfloyd.dao;

import com.jsonfloyd.model.Order;
import java.util.List;
public interface OrderDao {
    void addOrder(Order order);
    void removeOrderById(int id);
    void updateOrder(Order order) throws Exception;
    Order getOrderById(int id);
    List<Order> getOrdersByUserId(int id);
    List<Order> getAllOrders();
    boolean isOrderExist(int userId, int positionId);

}
