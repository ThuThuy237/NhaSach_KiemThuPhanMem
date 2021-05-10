package com.mynhasach.service;

import com.mynhasach.pojo.Buy;
import com.mynhasach.pojo.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

/**
 *
 * @author thuy
 */
public class BuyService {

    private Connection conn;
    
    public BuyService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
   
    
    public List<Buy> getBuys() throws SQLException, ParseException{
        
        String sql = "SELECT * FROM buy ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        
        List<Buy> buys = new ArrayList<>();
        while(rs.next()){
            Buy b = new Buy();
            b.setId(rs.getInt("id"));
            b.setDate(LocalDateTime.parse(rs.getString("date")));
            b.setTotal(BigDecimal.valueOf(rs.getInt("total")));
            b.setEmpId(rs.getInt("emp_id"));
            b.setSupId(rs.getInt("supplier_id"));
            
            buys.add(b);
        }
        return buys;
    }
    public boolean addBuy(Buy buy) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`buy` (`date`, `total`, `emp_id`, `supplier_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1, buy.getDate().toString());
            stm.setBigDecimal(2,buy.getTotal());
            stm.setInt(3,buy.getEmpId());
            stm.setInt(4,buy.getSupId());


            return stm.executeUpdate()>0;

    }
    public int getLastId() throws SQLException{
        String sql = "SELECT (nhasach.buy.id) FROM nhasach.buy;";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();
        Buy o = new Buy();
        while(rs.next()){
            o.setId(rs.getInt("id"));
        }
        return o.getId();

    }

    public boolean deleteBuy(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`buy` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateCate(Buy buy) throws SQLException{
            String sql = "UPDATE `nhasach`.`buy` SET `date` = ?, `total` = ?, `emp_id` = ?, `supplier_id` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, buy.getId());
            preparedStatement.setString(1, buy.getDate().toString());
            preparedStatement.setBigDecimal(2, buy.getTotal());
            preparedStatement.setInt(3, buy.getEmpId());
            preparedStatement.setInt(4, buy.getSupId());

            return preparedStatement.executeUpdate()>0;

  
    }
}
