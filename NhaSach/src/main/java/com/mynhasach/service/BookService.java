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
import connection.ConnectJDBC;

/**
 *
 * @author thuy
 */
public class BookService {
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<Book> getBooks() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM books;");

        List<Book> books = new ArrayList<>();
        while (rs.next()) {
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
    public void addBook(Book book) throws SQLException {
        Connection conn = jdbcUtils.getConn();
        PreparedStatement preS = conn.prepareStatement("INSERT INTO books (name, author, inventory, import_price, price, image, cat_id)" +
                "VALUES (?,?,?,?,?,?,?)");
        preS.setObject(1,book.getName());
        preS.setObject(2,book.getAuthor());
        preS.setObject(3,book.getInventory());
        preS.setObject(4,book.getImportPrice());
        preS.setObject(5,book.getPrice());
        preS.setObject(6,book.getImage());
        preS.setObject(7,book.getCategoryId());

//        if (preS.executeUpdate()>0)
//            System.out.println("Thêm dữ liệu thành công!!!");
        preS.close();

    }
    public boolean deleteBook(int id){
        try {
            String sql = "DELETE FROM `nhasach`.`books` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }
    public boolean updateBook(int id, String name, String describe){
        try {
            String sql = "UPDATE `nhasach`.`books` SET `name` = ?, `author` = ?, `inventory` = ?, `import_price` = ?, `price` = ?, `image` = ?, `cat_id` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(8, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, inventory);
            preparedStatement.setInt(4, importprice);
            preparedStatement.setInt(5, price);
            preparedStatement.setString(6, image);
            preparedStatement.setInt(7, categoryid);

            return preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, throwables);
        }

        return false;
    }
}
