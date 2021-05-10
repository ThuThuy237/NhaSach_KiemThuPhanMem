//package com.mynhasach.service;
//
//import com.mynhasach.pojo.Login;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class LoginTester {
//    private static Connection conn;
//
//    @BeforeAll
//    public static void setUpClass() {
//        try {
//            conn = jdbcUtils.getConn();
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//        if (conn != null)
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//
//    @Test
//    @DisplayName("Đăng Nhập Với Username empty")
//    @Tag("critical")
//    public void testLoginUsernamePasswordEmpty() {
//        try {
//            LoginService s = new LoginService(conn);
//            String username = "";
//            String password = "";
//            System.out.println(username + password);
//            boolean isHaveLogin = false;
////            if (s.Login(username, password) != null) {
////                isHaveLogin = true;
////            }
//            Assertions.assertFalse(isHaveLogin);
//        } catch (SQLException ex) {
//            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @ParameterizedTest
//    @CsvSource({"tuan123,''", "'',123"})
//    @DisplayName("Đăng Nhập Với Username Hoặc password empty")
//    @Tag("critical")
//    public void  testLoginUsernamePasswordEmpty(String username, String password) throws SQLException {
//        LoginService s = new LoginService(conn);
//        boolean isHaveLogin = false;
////        if (s.Login(username, password) != null) {
////            isHaveLogin = true;
////        }
//        Assertions.assertFalse(isHaveLogin);
//    }
//
//    @ParameterizedTest
//    @CsvSource({"tuan123,'123'"})
//    @DisplayName("Đăng Nhập sai username password")
//    public void  testLoginFailed(String username, String password) throws SQLException {
//        LoginService s = new LoginService(conn);
//        boolean isHaveLogin = false;
//        if (s.getPasswordByUsername(username) != null) {
//            isHaveLogin = true;
//        }
//        Assertions.assertFalse(isHaveLogin);
//    }
//    @ParameterizedTest
//    @CsvSource({"tuan123,'1'"})
//    @DisplayName("Đăng Nhập thành công username password")
//    public void  testLoginSuccess(String username, String password) throws SQLException {
//        LoginService s = new LoginService(conn);
//        boolean isHaveLogin = false;
////        if (s.addLogin(new Login(username, password,"","","","USER")) ) {
////            isHaveLogin = true;
////        }
//        Assertions.assertTrue(isHaveLogin);
//    }
//
//}
