/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mynhasach.service;

import com.mynhasach.pojo.Regulation;
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
public class RegulationService {
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
}
