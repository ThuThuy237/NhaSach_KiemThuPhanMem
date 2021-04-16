/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Supplier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectJDBC;

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
            sup.setAddress(rs.getString("address"));
            sup.setPhone(rs.getString("phone"));

            suppliers.add(sup);
        }
        return suppliers;
    }
    public boolean addSupplier(Supplier supply) {
        try {
            String sql = "INSERT INTO `nhasach`.`suppliers` (`name`, `address`, `phone`) VALUES (?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,supply.getName());
            stm.setString(2,supply.getAddress());
            stm.setString(3,supply.getPhone());

            return stm.executeUpdate()>0;
        }catch (SQLException ex)
        {
            Logger.getLogger(SupplierService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteSupplier(int id){
        try {
            String sql = "DELETE FROM `nhasach`.`suppliers` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(SupplierService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }

    public boolean updateSupplier(int id, String name, String address, String phone){
        try {
            String sql = "UPDATE `nhasach`.`suppliers` SET `name` = ?, `address` = ?, `phone` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(4, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(SupplierService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }
}
