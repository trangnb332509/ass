/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Employee;
import model.TailorGoods;

/**
 *
 * @author Dell Inc
 */
public class TailorGoodsDAO extends DBContext {

    public ArrayList<TailorGoods> getTailorGoodsByEmpID(String id, int pageIndex, int pageSize) {
        ArrayList<TailorGoods> tgs = new ArrayList<>();
        try {
            String sql = "select tbl.id,tbl.[date],tbl.quantity,tbl.cid,tbl.name,tbl.price\n"
                    + "from\n"
                    + "(select ROW_NUMBER() OVER (Order by t.id asc) as rid,t.id,t.[date],t.quantity,c.id as cid,c.name,c.price \n"
                    + "from TailorGoods t\n"
                    + "inner join Category c\n"
                    + "on t.cid = c.id\n"
                    + "where t.eid = ?) tbl\n"
                    + "where tbl.rid between (?-1)*?+1 and ?*?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setInt(2, pageIndex);
            pre.setInt(3, pageSize);
            pre.setInt(4, pageIndex);
            pre.setInt(5, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                TailorGoods tg = new TailorGoods();
                tg.setId(rs.getInt(1));
                tg.setDate(rs.getDate(2));
                tg.setQuantity(rs.getInt(3));
                Category c = new Category();
                c.setId(rs.getInt(4));
                c.setName(rs.getString(5));
                c.setPrice(rs.getInt(6));
                tg.setCategory(c);
                tgs.add(tg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tgs;
    }

    public TailorGoods getTailorGoods(String id, Date date) {
        try {
            String sql = "select t.id,t.[date],t.quantity,c.id,c.name\n"
                    + "from TailorGoods t\n"
                    + "inner join Category c\n"
                    + "on t.cid = c.id\n"
                    + "where t.eid = ? and t.[date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                TailorGoods tg = new TailorGoods();
                tg.setId(rs.getInt(1));
                tg.setDate(rs.getDate(2));
                tg.setQuantity(rs.getInt(3));
                Category c = new Category();
                c.setId(rs.getInt(4));
                c.setName(rs.getString(5));
                tg.setCategory(c);
                return tg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertTailorGoods(TailorGoods tg) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[TailorGoods]\n"
                    + "           ([date]\n"
                    + "           ,[quantity]\n"
                    + "           ,[cid]\n"
                    + "           ,[eid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, tg.getDate());
            pre.setInt(2, tg.getQuantity());
            pre.setInt(3, tg.getCategory().getId());
            pre.setString(4, tg.getEmp().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTailorGoods(TailorGoods tg) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[TailorGoods]\n"
                    + "   SET[quantity] = ?\n"
                    + "      ,[cid] = ?\n"
                    + " WHERE [eid] = ? and [date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, tg.getQuantity());
            pre.setInt(2, tg.getCategory().getId());
            pre.setString(3, tg.getEmp().getId());
            pre.setDate(4, tg.getDate());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteById(String id) {
        try {
            String sql = "delete from TailorGoods\n"
                    + "where eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count(String id) {
        try {
            String sql = "select COUNT(*) as total\n"
                    + "from TailorGoods t\n"
                    + "inner join Category c\n"
                    + "on t.cid = c.id\n"
                    + "where t.eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TailorGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        TailorGoodsDAO tgdao = new TailorGoodsDAO();
        ArrayList<TailorGoods> tgs = tgdao.getTailorGoodsByEmpID("maint", 1, 10);
        int total = tgdao.count("maint");
        System.out.println(tgs.size());
        System.out.println(total);
    }
}
