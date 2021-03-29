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
        }
        conn.close();
        return buyDetails;
    }
}
