package com.jsonfloyd.controller;

import com.jsonfloyd.dao.OrderDao;
import com.jsonfloyd.dao.OrderDbRepository;
import com.jsonfloyd.model.Order;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by mrflo on 18.06.2017.
 */
@RestController
public class OrderController {
    @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")  // 404
    public class OrderNotFoundException extends RuntimeException {

    }
    private OrderDao orders;
    public OrderController(OrderDao orders){
        this.orders = orders;
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orders.getAllOrders(), HttpStatus.OK);
    }
    @PostMapping("/orders/")
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order){
        orders.addOrder(order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable int id, @RequestBody Order order){
        orders.updateOrderById(id, order);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Order> removeOrderById(@PathVariable int id){
        orders.removeOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id){
        return new ResponseEntity<Order>(orders.getOrderById(id), HttpStatus.OK);
    }
    @GetMapping("/orders/{user_id}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable int user_id){
        return new ResponseEntity<List<Order>>(orders.getOrdersByUserId(user_id), HttpStatus.OK);
    }
    @GetMapping("/orders/exception")
    public String exceptionCaller() throws SQLException{
        throw new SQLException("Hello");
    }
}
