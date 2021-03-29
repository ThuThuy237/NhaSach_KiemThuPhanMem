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

/**
 *
 * @author thuy
 */
public class BuyService {
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
        conn.close();
        return buys;
    }
}
