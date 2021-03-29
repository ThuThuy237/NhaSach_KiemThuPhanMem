/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Book;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class BookService {
    public List<Book> getBooks() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM books");

        List<Book> books = new ArrayList<>();
        while (rs.next()){
            Book b = new Book();
            b.setId(rs.getInt("id"));
            b.setName(rs.getString("name"));
            b.setAuthor(rs.getString("author"));
            b.setImage(rs.getString("image"));
            b.setInventory(rs.getInt("inventory"));
            b.setImportPrice(BigDecimal.valueOf(rs.getInt("import_price")));
            b.setPrice(BigDecimal.valueOf(rs.getInt("price")));
            b.setCategoryId(rs.getInt("cat_id"));
            

            books.add(b);
        }
        conn.close();
        return books;
    }
}
