/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.OrderDetail;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class OrderDetailService {
    public List<OrderDetail> getOrderDetails() throws SQLException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM order_detail");
        
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
        conn.close();
        return orderDetails;
    }
}
