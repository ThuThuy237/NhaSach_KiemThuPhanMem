/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class CustomerService {

    private Connection conn;
    private int Id;
    private String name;
    private String gender;
    private String address;
    private String phone;
    private String birthday;
    private String kw;
    public List<Customer> getCustomers() throws SQLException, ParseException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM customer");
        

        List<Customer> customers = new ArrayList<>();
        while (rs.next()){
            Customer cus = new Customer();
            cus.setId(rs.getInt("id"));
            cus.setName(rs.getString("name"));
            cus.setGender(rs.getString("gender"));
            cus.setAddress(rs.getString("address"));
            cus.setPhone(rs.getString("phone"));
            cus.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("birthday")));

            customers.add(cus);
        }
        return customers;
    }
    public boolean addCustomer(Customer custom) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`customers` (`name`, `gender`, `address`, `phone`, `birthday`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,custom.getName());
            stm.setString(2,custom.getGender());
            stm.setString(3,custom.getAddress());
            stm.setString(4,custom.getPhone());
            stm.setDate(2, (Date) custom.getBirthday());


            return stm.executeUpdate()>0;
    }

    public boolean deleteCustomer(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`customers` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateCustomer(Customer custom) throws SQLException{
            String sql = "UPDATE `nhasach`.`customer` SET `name` = ?, `gender` = ?, `address` = ?, `phone` = ?, `birthday` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(6, Id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, birthday);


            return preparedStatement.executeUpdate()>0;

    }
}
