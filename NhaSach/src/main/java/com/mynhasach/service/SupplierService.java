package com.mynhasach.service;


import com.mynhasach.pojo.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author thuy
 */
public class SupplierService {

    private Connection conn;
    public SupplierService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<Supplier> getSuppliers() throws SQLException {
         String sql = "SELECT * FROM supplier ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

        List<Supplier> suppliers = new ArrayList<>();
        while (rs.next()){
            Supplier sup = new Supplier();
            sup.setId(rs.getInt("id"));
            sup.setName(rs.getString("name"));
            sup.setAddress(rs.getString("address"));
            sup.setPhone(rs.getString("phone"));

            suppliers.add(sup);
        }
        return suppliers;
    }
    public boolean addSupplier(Supplier supply) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`supplier` (`name`, `address`, `phone`) VALUES (?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setString(1,supply.getName());
            stm.setString(2,supply.getAddress());
            stm.setString(3,supply.getPhone());

            return stm.executeUpdate()>0;

    }

    public boolean deleteSupplier(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`supplier` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;
        
    }

    public boolean updateSupplier(Supplier supply) throws SQLException{
            String sql = "UPDATE `nhasach`.`supplier` SET `name` = ?, `address` = ?, `phone` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(4, supply.getId());
            preparedStatement.setString(1, supply.getName());
            preparedStatement.setString(2, supply.getAddress());
            preparedStatement.setString(3, supply.getPhone());

            return preparedStatement.executeUpdate()>0;

    }
}
