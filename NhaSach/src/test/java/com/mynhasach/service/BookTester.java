package com.mynhasach.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    // test thêm sách có điều kiện(
    // -số lượng sách nhập phải ít hơn inventoryMaxWhenImport của Regulation
    // -lượng sách nhập mới ít nhất phải lớn hơnn importMin của Regulation
    // -Nhập sách mới thì tạo trường mới, sách cũ update trường đã có), đợi viết xong class Regulation....
}
