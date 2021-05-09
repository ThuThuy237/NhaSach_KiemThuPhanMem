package com.mynhasach.service;

import com.mynhasach.pojo.BuyDetail;
import java.math.BigDecimal;
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
public class BuyDetailService {
    
    private Connection conn;

    public BuyDetailService() throws SQLException{
        this.conn = jdbcUtils.getConn();
        
    }
    
    public List<BuyDetail> getBuyDetails() throws SQLException {
         String sql = "SELECT * FROM buy_detail ";

        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();
        
        List<BuyDetail> buyDetails = new ArrayList<>();
        while(rs.next()){
            BuyDetail bd = new BuyDetail();
            bd.setId(rs.getInt("id"));
            bd.setQuantity(rs.getInt("quantity"));
            bd.setPrice(BigDecimal.valueOf(rs.getInt("price")));
            bd.setBookId(rs.getInt("book_id"));
            bd.setBuyId(rs.getInt("buy_id"));
            
            buyDetails.add(bd);
        }
        return buyDetails;
        
    }

    /**
     *
     * @param buy
     * @return
     * @throws SQLException
     */
    public boolean addBuyDetail(BuyDetail buy) throws SQLException {
            String sql = "INSERT INTO `nhasach`.`buy_detail` (`quantity`, `price`, `book_id`, `buy_id`) VALUES (?, ?, ?, ?);";
            PreparedStatement stm = this.conn.prepareStatement(sql);
            stm.setInt(1,buy.getQuantity());
            stm.setBigDecimal(2,buy.getPrice());
            stm.setInt(3,buy.getBookId());
            stm.setInt(4,buy.getBuyId());

            return stm.executeUpdate()>0;

    }
    public boolean deleteBuyDetail(int id) throws SQLException{
            String sql = "DELETE FROM `nhasach`.`buy_detail` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate()>0;

       
    }

    public boolean updateBuyDetail(BuyDetail buy) throws SQLException{
            String sql = "UPDATE `nhasach`.`buy_detail` SET `quantity` = ?, `price` = ?, `book_id` = ?, `buy_id` = ? WHERE (`id` = ?);";
            PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
            preparedStatement.setInt(5, buy.getId());
            preparedStatement.setInt(1, buy.getQuantity());
            preparedStatement.setBigDecimal(2, buy.getPrice());
            preparedStatement.setInt(3, buy.getBookId());
            preparedStatement.setInt(4, buy.getBuyId());

            return preparedStatement.executeUpdate()>0;

    }

        

}
