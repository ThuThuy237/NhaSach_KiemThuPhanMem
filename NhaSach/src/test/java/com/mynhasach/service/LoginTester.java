package com.mynhasach.service;

import com.mynhasach.nhasach.Util;
import com.mynhasach.pojo.Login;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Test
    @DisplayName("Đăng Nhập Với Username và Password rỗng")
    @Tag("critical")
    public void testLoginUsernamePasswordEmpty() {
        try {
            LoginService s = new LoginService();
            String username = "";
            String password = "";
            boolean isLogin = false;
            Util ut = new Util();
            if (s.getPasswordByUsername(username).equals(ut.md5(password))) {
                isLogin = true;
            }
            Assertions.assertFalse(isLogin);
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @ParameterizedTest
    @CsvSource({"thuy,''", "'',thuy"})
    @DisplayName("Đăng Nhập Với Username Hoặc password rỗng")
    @Tag("critical")
    public void testLoginUsernamePasswordEmpty(String username, String password) throws SQLException {
        LoginService s = new LoginService();
        boolean isLogin = false;
        Util ut = new Util();
        try{
            if (s.getPasswordByUsername(username).equals(ut.md5(password)) ) {
                isLogin = true;
            }
        }catch (Exception ex){
            isLogin = false;
        }
        Assertions.assertFalse(isLogin);
    }

    @ParameterizedTest
    @CsvSource({"thuy,'thu'", "thuuthuy,thuy"})
    @DisplayName("Đăng Nhập sai username password")
    public void  testLoginFailed(String username, String password) throws SQLException {
        LoginService s = new LoginService();
        boolean isLogin = false;
        Util ut = new Util();
        try{
            if (s.getPasswordByUsername(username).equals(ut.md5(password)) ) {
                isLogin = true;
            }
        }catch (Exception ex){
            isLogin = false;
        }
        Assertions.assertFalse(isLogin);
    }

    @ParameterizedTest
    @CsvSource({"thuy,'thuy'"})
    @DisplayName("Đăng Nhập thành công username password")
    public void testLoginSuccess(String username, String password) throws SQLException {
        LoginService s = new LoginService();
        boolean isLogin = false;
        Util ut = new Util();
        if (s.getPasswordByUsername(username).equals(ut.md5(password))) {
            isLogin = true;
        }
        Assertions.assertTrue(isLogin);
    }

}
