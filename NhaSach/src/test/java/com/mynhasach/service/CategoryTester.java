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

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


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
    @DisplayName("Lấy về các loại danh mục sách")
    public void testQuantity() throws SQLException {
        List<Category> cates = new CategoryService().getCates("");
        Assertions.assertTrue(cates.size() >= 1);
    }

//    @ParameterizedTest
//    @CsvSource({"Autobiography,is a self-written account of one's life"})
//    @DisplayName("Thêm danh muc mới vào database")
//    public void testAddCate( String name, String description) throws SQLException {
//        Category category = new Category(name,description);
//        CategoryService cs = new CategoryService();
//
//        Assertions.assertTrue(cs.addCate(category));
//
//    }

//
//    @ParameterizedTest
//    @CsvSource({"6"})
//    @DisplayName("Xóa danh mục từ database ")
//    public void testDeleteCate(int Id) throws SQLException {
//        CategoryService cs = new CategoryService();
//        Assertions.assertTrue(cs.deleteCate(Id));
//    }

//    @Test
//    @ParameterizedTest
//    @CsvSource({"5, test, test"})
//    @DisplayName("sửa danh mục ")
//    @Tag("critical")
//    public void testUpdateCate(int Id, String name, String des) throws SQLException {
//        CategoryService cs = new CategoryService();
//        Assertions.assertTrue(cs.updateCate(new Category(Id, name, des)));
//    }

}
