package com.mynhasach.service;

import com.mynhasach.pojo.Customer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class CustomerService {

    private Connection conn;
    
    public CustomerService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     * @throws java.text.ParseException
     */
    public List<Customer> getCustomers() throws SQLException, ParseException {
         String sql = "SELECT * FROM customers ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();
        

        List<Customer> customers = new ArrayList<>();
        while (rs.next()){
            Customer cus = new Customer();
            cus.setId(rs.getInt("id"));
            cus.setName(rs.getString("name"));
            cus.setGender(rs.getString("gender"));
            cus.setAddress(rs.getString("address"));
            cus.setPhone(rs.getString("phone"));
            cus.setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("birthday")));

            customers.add(cus);
        }
        return customers;
    }
    public boolean addCustomer(Customer custom) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`customers` (`name`, `gender`, `address`, `phone`, `birthday`) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,custom.getName());
            stm.setString(2,custom.getGender());
            stm.setString(3,custom.getAddress());
            stm.setString(4,custom.getPhone());
            stm.setDate(2, (Date) custom.getBirthday());


            return stm.executeUpdate()>0;
    }

    public boolean deleteCustomer(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`customers` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateCustomer(Customer custom) throws SQLException{
            String sql = "UPDATE `nhasach`.`customer` SET `name` = ?, `gender` = ?, `address` = ?, `phone` = ?, `birthday` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(6, custom.getId());
            preparedStatement.setString(1, custom.getName());
            preparedStatement.setString(2, custom.getGender());
            preparedStatement.setString(3, custom.getAddress());
            preparedStatement.setString(4, custom.getPhone());
            preparedStatement.setDate(5, (Date) custom.getBirthday());


            return preparedStatement.executeUpdate()>0;

    }
}
