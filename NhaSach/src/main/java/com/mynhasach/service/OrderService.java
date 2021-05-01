///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mynhasach.service;
//
//import com.mynhasach.pojo.Category;
//import com.mynhasach.pojo.Order;
//import java.math.BigDecimal;
//import java.sql.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author thuy
// */
//public class OrderService {
//    private Connection conn;
//
//    public OrderService() throws SQLException {
//        this.conn = jdbcUtils.getConn();
//    }
//    public List<Order> getOrders() throws SQLException, ParseException{
//        Connection conn = jdbcUtils.getConn();
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM order");
//
//        List<Order> orders = new ArrayList<>();
//        while(rs.next()){
//            Order o = new Order();
//            o.setId(rs.getInt("id"));
//            o.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("date")));
//            o.setTotal(BigDecimal.valueOf(rs.getInt("total")));
//            o.setEmpId(rs.getInt("emm_id"));
//            o.setCusId(rs.getInt("cus_id"));
//
//            orders.add(o);
//        }
//        return orders;
//    }
//    public boolean addOrder(Order order) throws SQLException {
//
//            String sql = "INSERT INTO `nhasach`.`orders` (`date`, `total`, `emm_id`, `cus_id`) VALUES (?, ?, ?, ?);";
//            PreparedStatement stm = this.conn.prepareStatement(sql);
//            stm.setString(1,order.getDate().toString());
//            stm.setInt(2,Integer.parseInt(String.valueOf(order.getTotal())));
//            stm.setInt(3,order.getEmpId());
//            stm.setInt(4,order.getCusId());
//
//            return stm.executeUpdate()>0;
//
//    }
//
//    public boolean deleteOrder(int id){
//        try {
//            String sql = "DELETE FROM `nhasach`.`orders` WHERE (`id` = ?);";
//            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//
//            return preparedStatement.executeUpdate()>0;
//        } catch (SQLException throwables) {
//            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, throwables);
//        }
//
//        return false;
//    }
//
//    public boolean updateOrder(int id, String date, Int total, Int emm_id, Int cus_id){
//        try {
//            String sql = "UPDATE `nhasach`.`orders` SET `date` = ?, `total` = ?, `emm_id` = ?, `cus_id` = ?  WHERE (`id` = ?);";
//            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
//            preparedStatement.setInt(5, id);
//            preparedStatement.setString(1, date);
//            preparedStatement.setInt(2, total);
//            preparedStatement.setInt(3, emm_id);
//            preparedStatement.setInt(4, cus_id);
//
//            return preparedStatement.executeUpdate()>0;
//        } catch (SQLException throwables) {
//            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, throwables);
//        }
//
//        return false;
//    }
//}
