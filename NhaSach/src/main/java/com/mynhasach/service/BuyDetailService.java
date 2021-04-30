/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.BuyDetail;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mynhasach.pojo.Category;
/**
 *
 * @author thuy
 */
public class BuyDetailService {
    public List<BuyDetail> getBuyDetails() throws SQLException{
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM buy_detail");
        
        List<BuyDetail> buyDetails = new ArrayList<>();
        while(rs.next()){
            BuyDetail bd = new BuyDetail();
            bd.setId(rs.getInt("id"));
            bd.setQuantity(rs.getInt("quantity"));
            bd.setPrice(BigDecimal.valueOf(rs.getInt("price")));
            bd.setBookId(rs.getInt("book_id"));
            bd.setBuyId(rs.getInt("buy_id"));
            
            buyDetails.add(bd);
        return buyDetails;
    }
    public boolean addBuyDetail(BuyDetail buy) {
            String sql = "INSERT INTO `nhasach`.`buydetails` (`quantily`, `price`, `book_id`, `buy_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1,buy.getQuantily());
            stm.setBigDecimal(2,buy.getPrice());
            stm.setInt(3,buy.getBookId());
            stm.setInt(4,buy.getBuyId());

            return stm.executeUpdate()>0;

        return false;
    }
    public boolean deleteBuyDetail(int id){
            String sql = "DELETE FROM `nhasach`.`buydetails` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

        return false;
    }

    public boolean updateBuyDetail(BuyDetail buy){
            String sql = "UPDATE `nhasach`.`buydetails` SET `quanlity` = ?, `price` = ?, `book_id` = ?, `buy_id` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, buy.getId);
            preparedStatement.setInt(1, buy.get.Quanlity);
            preparedStatement.setBigDecimal(2, buy.getPrice);
            preparedStatement.setInt(3, buy.getBook_id);
            preparedStatement.setInt(4, buy.getBuy_id);

            return preparedStatement.executeUpdate()>0;

        return false;
    }

}
