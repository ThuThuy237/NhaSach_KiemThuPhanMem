package com.mynhasach.service;

import com.mynhasach.pojo.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    public List<Category> getCates() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM categories");

        List<Category> categories = new ArrayList<>();
        while (rs.next()){
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setDescribe(rs.getString("describe"));

            categories.add(c);
        }
        conn.close();
        return categories;
    }
//    public void 
}
