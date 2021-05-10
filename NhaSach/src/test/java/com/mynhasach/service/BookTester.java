package com.mynhasach.service;

import com.mynhasach.pojo.Book;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
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
    @Test
    @Tag("add-book")
    public void testAddBook() {
        try {
            Book book = new Book();
            book.setName("English grammar in use");
            book.setAuthor("Kiều Trinh");
            book.setInventory(200);
            book.setImportPrice(new BigDecimal(50000));
            book.setPrice(new BigDecimal(70000));
            book.setId(4);
            book.setCategoryId(3);
            BookService b = new BookService();

            Assertions.assertTrue(b.addBook(book));
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @ParameterizedTest
    @CsvSource({",newAuthor, 200, 20000, 25000, newImage, 1"})
    @DisplayName("Test Thêm sách với dữ liệu thiếu tên")
    public void testAddBookWithoutName(String name, String author, int inventory, BigDecimal importPrice, BigDecimal price, String image,int categoryId) throws SQLException {
        Book book = new Book(name, author, inventory,  importPrice,  price,  image, categoryId);
        BookService b = new BookService();
        Boolean isSuccess = false;
        try{
            if(b.addBook(book)){
                isSuccess = true;
            }
        }catch (Exception ex){

        }
        Assertions.assertFalse(isSuccess);


    }
    @ParameterizedTest
    @CsvSource({"1","2"})
    @DisplayName("Test Tìm Kiếm Sách Theo Category")
    public void getBookByCategory(int catId){
        try {
            BookService  b = new BookService();
            Boolean isSuccess = false;
            if (b.getBooksByCate(catId).isEmpty() ){
                isSuccess = false;
            }else
                isSuccess = true;
            Assertions.assertTrue(isSuccess);
        } catch (SQLException ex) {
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testDeleteBook() throws SQLException {
        BookService b = new BookService();
        Assertions.assertTrue(b.deleteBook(34));
    }




    // test thêm sách có điều kiện(
    // -số lượng sách nhập phải ít hơn inventoryMaxWhenImport của Regulation
    // -lượng sách nhập mới ít nhất phải lớn hơnn importMin của Regulation
    // -Nhập sách mới thì tạo trường mới, sách cũ update trường đã có), đợi viết xong class Regulation....
}
