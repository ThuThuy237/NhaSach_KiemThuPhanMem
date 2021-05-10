/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author thuy
 */
public class jdbcUtils {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Create connection to database
     * @return connection
     * @throws java.sql.SQLException
     */
    public static Connection getConn() throws SQLException, SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/nhasach",
                "root", "12345");/*0917803105*/
    }
}
