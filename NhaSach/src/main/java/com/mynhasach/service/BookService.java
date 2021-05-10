/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Book;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class BookService {
    private Connection conn;

    public BookService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<Book> getBooks() throws SQLException {
         String sql = "SELECT * FROM books ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

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
        return books;
    }

    public List<Book> getBooksByCate(int catID) throws SQLException {
        String sql = "SELECT * FROM books WHERE (`cat_id` = ?); ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, catID);

        ResultSet rs = preparedStatement.executeQuery();

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
        return books;
    }

    /**
     * add a new book to database
     * @param book
     * @throws SQLException
     */
    public Boolean addBook(Book book) throws SQLException {
        Connection conn = jdbcUtils.getConn();
        PreparedStatement preS;
        preS = conn.prepareStatement("INSERT INTO books (name, author, inventory, import_price, price, image, cat_id)" +
                "VALUES (?,?,?,?,?,?,?)");
        preS.setString(1,book.getName());
        preS.setString(2,book.getAuthor());
        preS.setInt(3,book.getInventory());
        preS.setBigDecimal(4,book.getImportPrice());
        preS.setBigDecimal(5,book.getPrice());
        preS.setString(6,book.getImage());
        preS.setInt(7,book.getCategoryId());

        return preS.executeUpdate()>0;

    }
    public boolean deleteBook(int id) throws SQLException {
        String sql = "DELETE FROM `nhasach`.`books` WHERE (`id` = ?);";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        return preparedStatement.executeUpdate()>0;
    }

    public boolean updateBook(Book book) throws SQLException {
            String sql = "UPDATE `nhasach`.`books` SET `name` = ?, `author` = ?, `inventory` = ?, `import_price` = ?, `price` = ?, `image` = ?, `cat_id` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(8, book.getId());
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getInventory());
            preparedStatement.setBigDecimal(4, book.getImportPrice());
            preparedStatement.setBigDecimal(5, book.getPrice());
            preparedStatement.setString(6, book.getImage());
            preparedStatement.setInt(7, book.getCategoryId());

            return preparedStatement.executeUpdate()>0;
    }
}
