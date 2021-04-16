/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Login;
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
public class LoginService {
    public List<Login> getLogins() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
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
    
}
