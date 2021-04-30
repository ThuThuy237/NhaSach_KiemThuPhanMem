/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;
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
    public boolean addOrder(Order order) {
            String sql = "INSERT INTO `nhasach`.`orders` (`date`, `total`, `emm_id`, `cus_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,order.getDate());
            stm.setBigDecimal(2,order.getTotal());
            stm.setInt(3,order.getEmpId());
            stm.setInt(4,order.getCusId());

            return stm.executeUpdate()>0;
        return false;
    }

    public boolean deleteOrder(int id){
            String sql = "DELETE FROM `nhasach`.`orders` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

        return false;
    }

    public boolean updateOrder(Order order){
            String sql = "UPDATE `nhasach`.`orders` SET `date` = ?, `total` = ?, `emm_id` = ?, `cus_id` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, order.getId);
            preparedStatement.setString(1, order.getDdate);
            preparedStatement.setBigDecimal(2, order.getTotal);
            preparedStatement.setInt(3, order.getEmm_id);
            preparedStatement.setInt(4, order.getCus_id);

            return preparedStatement.executeUpdate()>0;


        return false;
    }
}
