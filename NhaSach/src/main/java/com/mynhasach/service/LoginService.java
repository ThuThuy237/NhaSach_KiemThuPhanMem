
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Buy;
import com.mynhasach.pojo.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author thuy
 */
public class LoginService {

    private Connection conn;

    public LoginService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<Login> getLogins() throws SQLException {
         String sql = "SELECT * FROM login ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

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

    public Login getLoginByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM login Where username = ?;";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet rs = preparedStatement.executeQuery();

        Login l = new Login();
        while (rs.next()){
            l.setId(rs.getInt("id"));
            l.setAvatar(rs.getString("avatar"));
            l.setEmail(rs.getString("email"));
            l.setPassword(rs.getString("password"));
            l.setUsername(rs.getString("username"));
        }
        return l;
    }

    public boolean addLogin(Login login) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`login` (`username`, `password`, `email`, `avatar`, `login_role`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,login.getUsername());
            stm.setString(2,login.getPassword());
            stm.setString(3,login.getEmail());
            stm.setString(4, login.getAvatar());
            stm.setString(5,"USER");

            return stm.executeUpdate()>0;

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

    public boolean deleteLogin(int id) throws SQLException{
        String sql = "DELETE FROM `nhasach`.`login` WHERE (`id` = ?);";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeUpdate()>0;

    }

    public boolean updateLogin(Login login) throws SQLException{
        String sql = "UPDATE `nhasach`.`login` SET `username` = ?, `password` = ?, `email` = ?, `avatar` = ?, `login_role` = ?  WHERE (`id` = ?);";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(6, login.getId());
        preparedStatement.setString(1, login.getUsername());
        preparedStatement.setString(2, login.getPassword());
        preparedStatement.setString(3, login.getEmail());
        preparedStatement.setString(4, login.getAvatar());
        preparedStatement.setString(5,"USER");

        return preparedStatement.executeUpdate()>0;


    }
}

