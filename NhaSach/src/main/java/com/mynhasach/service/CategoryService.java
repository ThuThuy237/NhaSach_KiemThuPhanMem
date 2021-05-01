package com.mynhasach.service;

import com.mynhasach.pojo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean addCate(Category cate) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`categories` (`name`, `describe`) VALUES (?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,cate.getName());
            stm.setString(2,cate.getDescribe());

            return stm.executeUpdate()>0;
    }

    public boolean deleteCate(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`categories` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateCate(Category cate) throws SQLException{
            String sql = "UPDATE `nhasach`.`categories` SET `name` = ?, `describe` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(3, cate.getId());
            preparedStatement.setString(1, cate.getName());
            preparedStatement.setString(2, cate.getDescribe());

            return preparedStatement.executeUpdate()>0;

    }
}
