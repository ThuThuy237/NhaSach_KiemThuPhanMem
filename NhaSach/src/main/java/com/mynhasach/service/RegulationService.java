/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Regulation;
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
public class RegulationService {

    private Connection conn;
    public RegulationService() throws SQLException {
        this.conn = jdbcUtils.getConn();
    }
    /**
     * get all of the books in the database
     * @return list of book in database
     * @throws SQLException if can't connect to db
     */
    public List<Regulation> getRegulations() throws SQLException {
         String sql = "SELECT * FROM regulations ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

        List<Regulation> regulations = new ArrayList<>();
        while (rs.next()){
            Regulation r = new Regulation();
            r.setId(rs.getInt("id"));
            r.setActive(rs.getInt("active"));
            r.setDebtMax(rs.getInt("debt_max"));
            r.setImportMin(rs.getInt("import_min"));
            r.setInventoryMaxWhenImport(rs.getInt("inventory_max_when_import"));
            r.setInventoryMinWhenSell(rs.getInt("inventory_min_when_sell"));
            r.setUserRole(rs.getInt("id_user"));

            regulations.add(r);
        }
        return regulations;
    }
    public boolean addRegulation(Regulation regulate) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`regulations` (`active`, `debt_max`, `import_min`, `inventory_max_when_import`, `inventory_min_when_sell`, `id_user`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1,regulate.getActive());
            stm.setInt(2,regulate.getDebtMax());
            stm.setInt(3,regulate.getImportMin());
            stm.setInt(4,regulate.getInventoryMaxWhenImport());
            stm.setInt(5,regulate.getInventoryMinWhenSell());
            stm.setInt(6,regulate.getUserRole());



            return stm.executeUpdate()>0;

    }

    public boolean deleteRegulation(int id) throws SQLException{

            String sql = "DELETE FROM `nhasach`.`regulations` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

    }

    public boolean updateRegulation(Regulation regulate) throws SQLException{
            String sql = "UPDATE `nhasach`.`regulations` SET `active` = ?, `debt_max` = ?, `import_min` = ?, `inventory_max_when_import` = ?, `inventory_min_when_sell` = ?, `id_user` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(7, regulate.getId());
            preparedStatement.setInt(1, regulate.getActive());
            preparedStatement.setInt(2, regulate.getDebtMax());
            preparedStatement.setInt(3, regulate.getImportMin());
            preparedStatement.setInt(4, regulate.getInventoryMaxWhenImport());
            preparedStatement.setInt(5, regulate.getInventoryMinWhenSell());
            preparedStatement.setInt(6, regulate.getUserRole());

            return preparedStatement.executeUpdate()>0;

    }
}
