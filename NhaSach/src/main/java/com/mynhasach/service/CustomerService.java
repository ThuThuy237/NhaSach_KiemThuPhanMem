/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Customer;
import java.sql.Connection;
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
            cus.setAddress(rs.getString("adress"));
            cus.setPhone(rs.getString("phone"));
            cus.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("birthday")));

            customers.add(cus);
        }
        return customers;
    }
}
