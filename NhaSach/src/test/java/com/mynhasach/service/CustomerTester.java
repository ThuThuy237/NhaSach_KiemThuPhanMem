package com.mynhasach.service;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Customer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerTester {
    private static Connection conn;

    @BeforeAll
    public static void setUpClass() {
        try {
            conn = jdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CustomerTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Test
    @DisplayName("Lấy về danh sách các khách hàng")
    public void testQuantity() throws SQLException, ParseException {
        List<Customer> customer = new CustomerService().getCustomers();
        Assertions.assertTrue(customer.size() >= 1);
    }

//    @ParameterizedTest
//    @CsvSource({"Trinh, female, 161A Nguyễn Văn Thủ Q.1, 1999-10-10, 0869578117"})
//    @DisplayName("Thêm khách hàng mới với đầy đủ thông tin")
//    public void testAddCus(String name, String gender, String address, LocalDate birthday, String phone) throws SQLException {
//        CustomerService cs = new CustomerService();
//
//        Assertions.assertTrue(cs.addCustomer(new Customer(name, gender, birthday, address, phone)));
//
//    }
//
//    @ParameterizedTest
//    @CsvSource({", female, Ho Chi Minh, 1970-01-01, 0941662444"})
//    @DisplayName("Thêm khách hàng mới thiếu thông tin tên")
//    @Tag("critical")
//    public void testAddCusWithoutInfo(String name, String gender, String address, LocalDate birthday, String phone) throws SQLException {
//        CustomerService cs = new CustomerService();
//        boolean isTrue = false;
//        try{
//            if (cs.addCustomer(new Customer(name, gender, birthday, address, phone))) {
//                isTrue = true;
//            }
//        }catch (Exception e){
//        }
//
//        Assertions.assertFalse(isTrue);
//
//    }
//
//
//    @ParameterizedTest
//    @CsvSource({"6"})
//    @DisplayName("Xóa khách hàng bằng id từ database ")
//    public void testDeleteCus(int Id) throws SQLException {
//        CustomerService cs = new CustomerService();
//        Assertions.assertTrue(cs.deleteCustomer(Id));
//    }


}
