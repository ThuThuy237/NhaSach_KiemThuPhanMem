/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.OrderDetail;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class OrderDetailService {

    private Connection conn;
    public OrderDetailService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<OrderDetail> getOrderDetails() throws SQLException {
         String sql = "SELECT * FROM orderdetails ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();
        
        List<OrderDetail> orderDetails = new ArrayList<>();
        while(rs.next()){
            OrderDetail od = new OrderDetail();
            od.setId(rs.getInt("id"));
            od.setQuantity(rs.getInt("quantity"));
            od.setPrice(BigDecimal.valueOf(rs.getInt("price")));
            od.setBookId(rs.getInt("book_id"));
            od.setOrderId(rs.getInt("order_id"));
            
            orderDetails.add(od);
        }
        return orderDetails;
    }
    public boolean addOrderDetail(OrderDetail order) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`orderdetails` (`quanlity`, `price`, `book_id`, `order_id` ) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1,order.getQuantity());
            stm.setBigDecimal(2,order.getPrice());
            stm.setInt(3,order.getBookId());
            stm.setInt(4,order.getOrderId());

            return stm.executeUpdate()>0;

    }

    public boolean deleteOrderDetail(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`orderdetails` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateOrderDetail(OrderDetail order) throws SQLException{
            String sql = "UPDATE `nhasach`.`orderdetails` SET `quantity` = ?, `price` = ?, `book_id` = ?, `order_id` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, order.getId());
            preparedStatement.setInt(1, order.getQuantity());
            preparedStatement.setBigDecimal(2, order.getPrice());
            preparedStatement.setInt(3, order.getBookId());
            preparedStatement.setInt(4, order.getOrderId());

            return preparedStatement.executeUpdate()>0;

    }
}
