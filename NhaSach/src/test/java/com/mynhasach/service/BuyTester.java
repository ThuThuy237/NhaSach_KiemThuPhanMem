package com.mynhasach.service;

import com.mynhasach.pojo.Buy;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BuyTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BuyTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
//    @Test
//    @Tag("add-buy")
//    public void testAddBuy() {
//        try {
//            Buy buy = new Buy();
//            buy.setId(1);
//            buy.setDate(new LocalDateTime("21-05-2020", "18:12:30"));
//            buy.setTotal(new BigDecimal(240900));
//            buy.setSupId(1);
//            buy.setEmpId(1);
//            BuyService b = new BuyService();
//
//            Assertions.assertTrue(b.addBuy(buy));
//        } catch (SQLException ex) {
//            Logger.getLogger(BuyTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Test
    public void testDeleteBuy() throws SQLException {
        BuyService b = new BuyService();
        Assertions.assertTrue(b.deleteBuy(2));
    }





}
