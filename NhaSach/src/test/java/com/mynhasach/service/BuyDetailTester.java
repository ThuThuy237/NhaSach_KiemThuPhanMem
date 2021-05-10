package com.mynhasach.service;

import com.mynhasach.pojo.BuyDetail;
import com.mynhasach.pojo.Category;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyDetailTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BuyDetailTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BuyDetailTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Test
    @Tag("add-product")
    public void testAddBuyDetail() {
        try {
            BuyDetail buyDetail = new BuyDetail();
            buyDetail.setId(1);
            buyDetail.setQuantity(20);
            buyDetail.setPrice(new BigDecimal(100000));
            buyDetail.setBookId(2);
            buyDetail.setBuyId(10);

            BuyDetailService bd = new BuyDetailService();
            Assertions.assertTrue(bd.addBuyDetail(buyDetail));
        } catch (SQLException ex) {
            Logger.getLogger(BuyDetailTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testQuantity() throws SQLException {
        List<BuyDetail> buyDetails = new BuyDetailService().getBuyDetails();
        Assertions.assertTrue(buyDetails.size() >= 1);
    }
    @Test
    public void testDeleteBuyDetail() throws SQLException {
        BuyDetailService bd = new BuyDetailService();
        Assertions.assertTrue(bd.deleteBuyDetail(11));
    }

}
