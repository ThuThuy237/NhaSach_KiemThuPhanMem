package com.mynhasach.service;

import com.mynhasach.pojo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryService {
    private Connection conn;

    public CategoryService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    public List<Category> getCates(String kw) throws SQLException {
        String sql = "SELECT * FROM categories WHERE name like concat('%', ?, '%')";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1,kw);

        ResultSet rs = preparedStatement.executeQuery();

        List<Category> categories = new ArrayList<>();
        while (rs.next()){
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setDescribe(rs.getString("describe"));

            categories.add(c);
        }
        return categories;
    }
    public boolean addCate(Category cate) {
        try {
            String sql = "INSERT INTO `nhasach`.`categories` (`name`, `describe`) VALUES (?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,cate.getName());
            stm.setString(2,cate.getDescribe());

            return stm.executeUpdate()>0;
        }catch (SQLException ex)
        {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean deleteCate(int id){
        try {
            String sql = "DELETE FROM `nhasach`.`categories` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }

    public boolean updateCate(int id, String name, String describe){
        try {
            String sql = "UPDATE `nhasach`.`categories` SET `name` = ?, `describe` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, describe);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }
}
