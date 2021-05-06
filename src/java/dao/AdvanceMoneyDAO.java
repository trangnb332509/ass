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
import model.AdvanceMoney;
import model.Employee;

/**
 *
 * @author Dell Inc
 */
public class AdvanceMoneyDAO extends DBContext {

    public ArrayList<AdvanceMoney> getById(String id) {
        ArrayList<AdvanceMoney> ams = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[money]\n"
                    + "      ,[date]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[AdvanceMoney]\n"
                    + "  where eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                AdvanceMoney am = new AdvanceMoney();
                am.setId(rs.getInt(1));
                am.setMoney(rs.getInt(2));
                am.setDate(rs.getDate(3));
                ams.add(am);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ams;
    }

    public void insert(AdvanceMoney adm) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[AdvanceMoney]\n"
                    + "           ([eid]\n"
                    + "           ,[money]\n"
                    + "           ,[date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, adm.getEmp().getId());
            pre.setInt(2, adm.getMoney());
            pre.setDate(3, adm.getDate());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int id, int money) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[AdvanceMoney]\n"
                    + "SET [money] = ? \n"
                    + "WHERE id = ?";
            PreparedStatement pre = connection.prepareCall(sql);
            pre.setInt(1, money);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String id) {
        try {
            String sql = "delete from AdvanceMoney\n"
                    + "where eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public AdvanceMoney getAdvanceMoney(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[eid]\n"
                    + "      ,[money]\n"
                    + "      ,[date]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[AdvanceMoney]\n"
                    + "  where id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                AdvanceMoney adm = new AdvanceMoney();
                adm.setId(rs.getInt(1));
                Employee e = new Employee();
                e.setId(rs.getString(2));
                adm.setEmp(e);
                adm.setMoney(rs.getInt(3));
                adm.setDate(rs.getDate(4));
                return adm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotal(String id) {
        try {
            String sql = "SELECT [eid],SUM([money]) as total\n"
                    + "FROM [ProductionAndTrade].[dbo].[AdvanceMoney]\n"
                    + "group by eid\n"
                    + "having eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvanceMoneyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        AdvanceMoneyDAO dao = new AdvanceMoneyDAO();
        System.out.println(dao.getTotal("maint"));
    }
}
