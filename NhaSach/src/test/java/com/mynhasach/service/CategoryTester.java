/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;

import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



/**
 *
 * @author thuy
 */
public class CategoryTester {

    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Test
    public void testQuantity() throws SQLException {
        List<Category> cates = new CategoryService().getCates("");
        Assertions.assertTrue(cates.size() >= 1);
    }

    @Test
    public void testAddCate() throws SQLException {
        Category category = new Category();
        category.setName("Autobiography");
        category.setDescribe("is a self-written account of one's life");
        CategoryService cs = new CategoryService();

        Assertions.assertTrue(cs.addCate(category));

    }

    @Test
    public void testAddCateWithoutName() throws SQLException {
        Category category = new Category();
        category.setName("Autobiography");
//        category.setDescribe("is a self-written account of one's life");
        CategoryService cs = new CategoryService();

        Assertions.assertTrue(cs.addCate(category));

    }

//    @Test
//    public void testDeleteCate() throws SQLException {
//        CategoryService cs = new CategoryService();
//        Assertions.assertTrue(cs.deleteCate(6));
//    }

//    @Test
//    public void testUpdateCate() throws SQLException {
//        CategoryService cs = new CategoryService();
//        Assertions.assertTrue(cs.updateCate(2, "comic",null));
//    }

}
