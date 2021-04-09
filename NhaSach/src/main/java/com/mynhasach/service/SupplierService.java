/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Supplier;
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
public class SupplierService {
    public List<Supplier> getSuppliers() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM supplier");

        List<Supplier> suppliers = new ArrayList<>();
        while (rs.next()){
            Supplier sup = new Supplier();
            sup.setId(rs.getInt("id"));
            sup.setName(rs.getString("name"));
            sup.setAddress(rs.getString("adress"));
            sup.setPhone(rs.getString("phone"));

            suppliers.add(sup);
        }
        conn.close();
        return suppliers;
    }
}
