package com.mynhasach.service;

import com.mynhasach.pojo.OrderDetail;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDetailTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderDetailTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    @Test
    @DisplayName("Lấy về các thông tin chi tiết đơn bán cho khách")
    public void testQuantity() throws SQLException {
        List<OrderDetail> od = new OrderDetailService().getOrderDetails();
        Assertions.assertTrue(od.size() >= 1);
    }

//    @ParameterizedTest
//    @CsvSource({"2, 1,1, 20000", "5,1,3, 250000", "2,1,2, 60000"})
//    @DisplayName("Thêm chi tiết đơn hàng mới vào database")
//    public void testAddOd(int bookId, int quantity, int orderId, BigDecimal price) throws SQLException {
//        OrderDetail orderDetail = new OrderDetail(bookId,quantity,orderId,price);
//        OrderDetailService ods = new OrderDetailService();
//
//        Assertions.assertTrue(ods.addOrderDetail(orderDetail));
//
//    }
//

//    @ParameterizedTest
//    @CsvSource({"2, 1000,1, 20000", "5,1000,3, 250000", "2,1000,2, 60000"})
//    @DisplayName("Thêm chi tiết đơn hàng với orderId chưa có trong database ")
//    public void testAddOdWithoutInfo(int bookId, int quantity, int orderId, BigDecimal price) throws SQLException {
//        OrderDetail orderDetail = new OrderDetail(bookId,quantity,orderId,price);
//        OrderDetailService ods = new OrderDetailService();
//        Boolean isSuccess = false;
//        try{
//            if (ods.addOrderDetail(orderDetail))
//                isSuccess = true;
//        }catch (Exception e){
//
//        }
//        Assertions.assertFalse(isSuccess);
//
//    }

//
//    @ParameterizedTest
//    @CsvSource({"2"})
//    @DisplayName("Xóa chi chi tiết đơn hàng từ database ")
//    public void testDeleteOD(int Id) throws SQLException {
//        OrderDetailService ods = new OrderDetailService();
//        Assertions.assertTrue(ods.deleteOrderDetail(Id));
//    }
//
//    @ParameterizedTest
//    @CsvSource({"2, 200, 20000, 3, 1"})
//    @DisplayName("sửa chi tiết đơn hàng ")
//    @Tag("critical")
//    public void testUpdateOD(int id, int quantity, BigDecimal price, int bookId, int orderId) throws SQLException {
//        OrderDetailService ods = new OrderDetailService();
//        Assertions.assertTrue(ods.updateOrderDetail(new OrderDetail(id,bookId, orderId, quantity, price )));
//    }

}
