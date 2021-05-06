/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell Inc
 */
public class GroupAccountDAO extends DBContext {

    public void insert(int gid, String username) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[GroupAccount]\n"
                    + "           ([gid]\n"
                    + "           ,[username])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, gid);
            pre.setString(2, username);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String username) {
        try {
            String sql = "DELETE FROM [ProductionAndTrade].[dbo].[GroupAccount]\n"
                    + "      WHERE username = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getGroupByUsername(String username) {
        try {
            String sql = "SELECT [gid]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[GroupAccount]\n"
                    + "  where username = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
             if(rs.next()){
                return rs.getInt(1);
             }
        } catch (SQLException ex) {
            Logger.getLogger(GroupAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
