package com.jsonfloyd.controller;

import com.jsonfloyd.Service.IOrderService;
import com.jsonfloyd.dao.OrderDao;
import com.jsonfloyd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mrflo on 18.06.2017.
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderService orders;
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orders.getAllOrders(), HttpStatus.OK);
    }
    @PostMapping("/orders/")
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order){
        orders.addOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PutMapping("/orders/")
    public ResponseEntity<Order> updateOrderById(@RequestBody Order order) throws Exception{
        orders.updateOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> removeOrderById(@PathVariable int id){
        orders.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value="/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable int id){
        return orders.getOrderById(id);
    }
    @GetMapping("/orders/userid/{user_id}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable int user_id){
        return new ResponseEntity<List<Order>>(orders.getOrdersByUserId(user_id), HttpStatus.OK);
    }
    @GetMapping("/orders/exception")
    public String exceptionCaller() throws SQLException{
        throw new SQLException("Hello");
    }
}
