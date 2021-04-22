/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author thuy
 */
public class LoginService {

    private Connection conn;

    public LoginService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }

    public List<Login> getLogins() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM login");

        List<Login> logins = new ArrayList<>();
        while (rs.next()){
            Login l = new Login();
            l.setId(rs.getInt("id"));
            l.setAvatar(rs.getString("avatar"));
            l.setEmail(rs.getString("email"));
            l.setPassword(rs.getString("password"));
            l.setUsername(rs.getString("username"));

            logins.add(l);
        }
        return logins;
    }

    public boolean addLogin(Login login) {
        try {
            String sql = "INSERT INTO `nhasach`.`login` (`username`, `password`, `email`, `avatar`, `login_role`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,login.getUsername());
            stm.setString(2,login.getPassword());
            stm.setString(3,login.getEmail());
            stm.setString(4, login.getAvatar());
            stm.setString(5,"USER");

            return stm.executeUpdate()>0;
        }catch (SQLException ex)
        {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public String getPasswordByUsername(String userName) throws SQLException {
        String sql = "SELECT password FROM nhasach.login where username = ?;";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        preparedStatement.setString(1,userName);

        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public Boolean findUsername(String username) throws SQLException {
        String sql = "SELECT * FROM nhasach.login where username = ?;";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        preparedStatement.setString(1,username);

        return preparedStatement.executeQuery().next();
    }

}
