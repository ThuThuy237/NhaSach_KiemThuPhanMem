/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Category;
import com.mynhasach.pojo.Regulation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thuy
 */
public class RegulationService {

    private Connection conn;
    private int Id;
    private int active;
    private int debt_max;
    private int import_min;
    private int inventory_max_when_import;
    private int inventory_min_when_sell;
    private int id_user;
    public List<Regulation> getRegulations() throws SQLException {
        Connection conn = jdbcUtils.getConn();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM regulations");

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
            preparedStatement.setInt(7, Id);
            preparedStatement.setInt(1, active);
            preparedStatement.setInt(2, debt_max);
            preparedStatement.setInt(3, import_min);
            preparedStatement.setInt(4, inventory_max_when_import);
            preparedStatement.setInt(5, inventory_min_when_sell);
            preparedStatement.setInt(6, id_user);

            return preparedStatement.executeUpdate()>0;

    }
}
