package com.mynhasach.service;

import com.mynhasach.pojo.Buy;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        
        String sql = "SELECT * FROM buys ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        
        List<Buy> buys = new ArrayList<>();
        while(rs.next()){
            Buy b = new Buy();
            b.setId(rs.getInt("id"));
            b.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("date")));
            b.setTotal(BigDecimal.valueOf(rs.getInt("total")));
            b.setEmpId(rs.getInt("emp_id"));
            b.setSupId(rs.getInt("supplier_id"));
            
            buys.add(b);
        }
        return buys;
    }
    public boolean addBuy(Buy buy) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`buys` (`date`, `total`, `emp_id`, `sup_id`) VALUES (?, ?, ?, ?);";
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
            String sql = "UPDATE `nhasach`.`categories` SET `date` = ?, `total` = ?, `emp_id` = ?, `sup_id` = ?  WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, buy.getId());
            preparedStatement.setDate(1, (Date) buy.getDate());
            preparedStatement.setBigDecimal(2, buy.getTotal());
            preparedStatement.setInt(3, buy.getEmpId());
            preparedStatement.setInt(4, buy.getSupId());

            return preparedStatement.executeUpdate()>0;

  
    }
}
