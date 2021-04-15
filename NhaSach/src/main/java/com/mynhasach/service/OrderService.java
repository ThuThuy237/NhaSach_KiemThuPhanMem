/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Order;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class OrderService {
    public List<Order> getOrders() throws SQLException, ParseException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM order");
        
        List<Order> orders = new ArrayList<>();
        while(rs.next()){
            Order o = new Order();
            o.setId(rs.getInt("id"));
            o.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("date")));
            o.setTotal(BigDecimal.valueOf(rs.getInt("total")));
            o.setEmpId(rs.getInt("emm_id"));
            o.setCusId(rs.getInt("cus_id"));
            
            orders.add(o);
        }
        return orders;
    }
}
