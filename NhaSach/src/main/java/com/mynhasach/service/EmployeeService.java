/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Employee;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thuy
 */
public class EmployeeService {

    private Connection conn;
    private int Id;
    private String name;
    private String title;
    private String hireDate;
    public List<Employee> getEmployees() throws SQLException, ParseException {
        this.conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM employee");

        List<Employee> employees = new ArrayList<>();
        while (rs.next()){
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setTitle(rs.getString("title"));
            e.setHireDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("hireDate")));

            employees.add(e);
        }
        return employees;
    }
    public boolean addEmployee(Employee employ) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`employees` (`name`, `title`, `hireDate`) VALUES (?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,employ.getName());
            stm.setString(2,employ.getTitle());
            stm.setDate(3, (Date) employ.getHireDate());


            return stm.executeUpdate()>0;

    }

    public boolean deleteEmployee(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`employees` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    
    }

    public boolean updateEmployee(Employee employ) throws SQLException{
            String sql = "UPDATE `nhasach`.`employees` SET `name` = ?, `title` = ?, `hireDate` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(4, Id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, hireDate);

            return preparedStatement.executeUpdate()>0;

        
    }
}
