/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Employee;
import java.sql.Connection;
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
    public List<Employee> getEmployees() throws SQLException, ParseException {
        Connection conn = jdbcUtils.getConn();
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
        conn.close();
        return employees;
    }
}
