/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Buy;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mynhasach.pojo.Category;
import java.sql.PreparedStatement;

/**
 *
 * @author thuy
 */
public class BuyService {

    private Connection conn;
    private String Date;
    private BigDecimal total;
    private int emm_id;
    private int supplier_id;
    private int Id;
    
    public List<Buy> getBuys() throws SQLException, ParseException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM buy");
        
        
        List<Buy> buys = new ArrayList<>();
        while(rs.next()){
            Buy b = new Buy();
            b.setId(rs.getInt("id"));
            b.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("date")));
            b.setTotal(BigDecimal.valueOf(rs.getInt("total")));
            b.setEmpId(rs.getInt("emm_id"));
            b.setSupId(rs.getInt("supplier_id"));
            
            buys.add(b);
        }
        return buys;
    }
    public boolean addBuy(Buy buy) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`buys` (`date`, `total`, `emm_id`, `supplier_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setDate(1, (java.sql.Date) buy.getDate());
            stm.setBigDecimal(2,buy.getTotal());
            stm.setInt(3,buy.getEmpId());
            stm.setInt(4,buy.getSupId());


            return stm.executeUpdate()>0;

    }

    public boolean deleteBuy(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`buys` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateCate(Buy buy) throws SQLException{
            String sql = "UPDATE `nhasach`.`categories` SET `date` = ?, `total` = ?, `emm_id` = ?, `supplier_id` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, Id);
            preparedStatement.setString(1, Date);
            preparedStatement.setBigDecimal(2, total);
            preparedStatement.setInt(3, emm_id);
            preparedStatement.setInt(4, supplier_id);

            return preparedStatement.executeUpdate()>0;

  
    }
}
