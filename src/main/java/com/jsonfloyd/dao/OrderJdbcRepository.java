package com.jsonfloyd.dao;


import com.jsonfloyd.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class OrderDbRepository implements OrderDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OrderDbRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void addOrder(Order order) {
       jdbcTemplate.update("insert into orders(user_id,position_id,order_date) values ( ?, ?, ?)", order.getUserId(), order.getPositionId(), order.getOrderDate());
    }

    @Override
    public void removeOrderById(int id) {
        jdbcTemplate.update("delete from orders where id= ?", id);
    }

    @Override
    public void updateOrderById(int id, Order order) {
        jdbcTemplate.update("UPDATE orders set user_id= ?, position_id= ?, order_date= ? WHERE id= ?", order.getUserId(), order.getPositionId(), order.getOrderDate(), id);
    }

    @Override
    public Order getOrderById(int id) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE id= ?", rs -> {
            if (rs.next()) {

                return mapOrder(rs);
            }
            return null;
        }, id);
    }

    @Override
    public List<Order> getOrdersByUserId(int id) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE user_id= ?", (rs, rowNum) -> mapOrder(rs));

    }
    private Order mapOrder(ResultSet rs) throws SQLException{

        Order result = new Order();
        result.setId(rs.getInt("id"));
        result.setUserId(rs.getInt("user_id"));
        result.setPositionId(rs.getInt("position_id"));
        result.setOrderDate(rs.getTimestamp("order_date"));
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query("SELECT * FROM orders", (rs, rowNum) -> mapOrder(rs));
    }
}
