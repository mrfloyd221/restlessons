package com.jsonfloyd.Service;

import com.jsonfloyd.dao.OrderDao;
import com.jsonfloyd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderDao orders;

    @Override
    public List<Order> getAllOrders() {
        List<Order> allOrders = orders.getAllOrders();
        return allOrders;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> selectedOrders = orders.getOrdersByUserId(userId);
        return selectedOrders;
    }

    @Override
    public Order getOrderById(int orderId) {
        Order order = orders.getOrderById(orderId);
        return order;
    }

    @Override
    public boolean addOrder(Order order) {
        if (orders.isOrderExist(order.getUserId(), order.getPositionId())) {
            return false;
        } else {
            orders.addOrder(order);
            return true;
        }
    }

    @Override
    public void updateOrder(Order order) {
        orders.updateOrder(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        orders.removeOrderById(orderId);
    }
}
