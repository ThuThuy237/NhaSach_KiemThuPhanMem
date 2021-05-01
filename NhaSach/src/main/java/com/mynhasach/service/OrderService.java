/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;


import com.mynhasach.pojo.Order;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class OrderService {

    private Connection conn;
    public OrderService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     * @throws java.text.ParseException
     */
    public List<Order> getOrders() throws SQLException, ParseException {
         String sql = "SELECT * FROM orders ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();
        
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
    public boolean addOrder(Order order) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`orders` (`date`, `total`, `emp_id`, `cus_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setDate(1, (Date) order.getDate());
            stm.setBigDecimal(2,order.getTotal());
            stm.setInt(3,order.getEmpId());
            stm.setInt(4,order.getCusId());

            return stm.executeUpdate()>0;
      
    }

    public boolean deleteOrder(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`orders` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

       
    }

    public boolean updateOrder(Order order) throws SQLException{
            String sql = "UPDATE `nhasach`.`orders` SET `date` = ?, `total` = ?, `emp_id` = ?, `cus_id` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, order.getId());
            preparedStatement.setDate(1, (Date) order.getDate());
            preparedStatement.setBigDecimal(2, order.getTotal());
            preparedStatement.setInt(3, order.getEmpId());
            preparedStatement.setInt(4, order.getCusId());

            return preparedStatement.executeUpdate()>0;


    }
}
