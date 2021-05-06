/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Group;

/**
 *
 * @author Dell Inc
 */
public class AccountDAO extends DBContext {

    public String getPassword(String username) {
        String password = null;
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[password]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Account]\n"
                    + "  where username = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                password = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return password;
    }

    public Account getByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select a.username, a.password, ISNULL(g.id,-1) as gid, g.name, f.id, f.url \n"
                    + "from Account a left join [GroupAccount] ga on a.username = ga.username\n"
                    + "			left join [Group] g on g.id = ga.gid\n"
                    + "			left join FeatureGroup fg on fg.gid = g.id\n"
                    + "			left join Feature f on f.id = fg.fid\n"
                    + "where a.username = ? and a.password = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2, password);
            ResultSet rs = pre.executeQuery();
            Account account = null;
            Group g = new Group();
            g.setId(-1);
            while (rs.next()) {
                if (account == null) {
                    account = new Account();
                    account.setUsername(rs.getString(1));
                    account.setPassword(rs.getString(2));
                }
                int gid = rs.getInt(3);
                if (gid != -1) {
                    if (gid != g.getId()) {
                        g = new Group();
                        g.setId(rs.getInt(3));
                        g.setName(rs.getString(4));
                        account.getGroups().add(g);
                    }
                    Feature f = new Feature();
                    f.setId(rs.getInt(5));
                    f.setUrl(rs.getString(6).trim());
                    g.getFeatures().add(f);
                }
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccount(Account a) {
        String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, a.getUsername());
            pre.setString(2, a.getPassword());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String username) {
        try {
            String sql = "DELETE FROM [ProductionAndTrade].[dbo].[Account]\n"
                    + "      WHERE username = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, username);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        Account acc = a.getByUsernameAndPassword("trang", "123");
        System.out.println(acc.getUsername());
    }

}
