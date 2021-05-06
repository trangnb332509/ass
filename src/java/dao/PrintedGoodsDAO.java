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
import model.Pattern;
import model.PrintedGoods;

/**
 *
 * @author Dell Inc
 */
public class PrintedGoodsDAO extends DBContext {

    public ArrayList<PrintedGoods> getById(String id, int pageIndex, int pageSize) {
        ArrayList<PrintedGoods> pgs = new ArrayList<>();
        try {
            String sql = "select tbl.id,tbl.[date],tbl.quantity,tbl.pid,tbl.name,tbl.price\n"
                    + "from\n"
                    + "(select ROW_NUMBER() OVER (Order by pg.id asc) as rid, pg.id,pg.[date],pg.quantity,p.id as pid,p.name,p.price\n"
                    + "from PrintedGoods pg\n"
                    + "inner join Pattern p\n"
                    + "on pg.pid = p.id\n"
                    + "where pg.eid = ?) tbl\n"
                    + "where rid between (?-1)*?+1 and ?*?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setInt(2, pageIndex);
            pre.setInt(3, pageSize);
            pre.setInt(4, pageIndex);
            pre.setInt(5, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                PrintedGoods pg = new PrintedGoods();
                pg.setId(rs.getInt(1));
                pg.setDate(rs.getDate(2));
                pg.setQuantity(rs.getInt(3));

                Pattern p = new Pattern();
                p.setId(rs.getInt(4));
                p.setName(rs.getString(5));
                p.setPrice(rs.getInt(6));

                pg.setPattern(p);
                pgs.add(pg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pgs;
    }

    public int count(String id) {
        try {
            String sql = "select COUNT(*) as total \n"
                    + "from PrintedGoods pg\n"
                    + "inner join Pattern p\n"
                    + "on pg.pid = p.id\n"
                    + "where pg.eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public PrintedGoods getPrintedGood(String id, Date date) {
        try {
            String sql = "select pg.id,pg.[date],pg.quantity,p.id,p.name \n"
                    + "from PrintedGoods pg\n"
                    + "inner join Pattern p\n"
                    + "on pg.pid = p.id\n"
                    + "where pg.eid = ? and pg.[date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                PrintedGoods pg = new PrintedGoods();
                pg.setId(rs.getInt(1));
                pg.setDate(rs.getDate(2));
                pg.setQuantity(rs.getInt(3));

                Pattern p = new Pattern();
                p.setId(rs.getInt(4));
                p.setName(rs.getString(5));
                pg.setPattern(p);

                return pg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertPrintedGoods(PrintedGoods pg) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[PrintedGoods]\n"
                    + "           ([date]\n"
                    + "           ,[quantity]\n"
                    + "           ,[pid]\n"
                    + "           ,[eid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, pg.getDate());
            pre.setInt(2, pg.getQuantity());
            pre.setInt(3, pg.getPattern().getId());
            pre.setString(4, pg.getEmp().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePrintedGoods(PrintedGoods pg) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[PrintedGoods]\n"
                    + "   SET [quantity] = ?\n"
                    + "      ,[pid] = ?\n"
                    + " WHERE eid = ? and [date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, pg.getQuantity());
            pre.setInt(2, pg.getPattern().getId());
            pre.setString(3, pg.getEmp().getId());
            pre.setDate(4, pg.getDate());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteById(String id) {
        try {
            String sql = "delete from PrintedGoods\n"
                    + "where eid = ? ";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PrintedGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        PrintedGoodsDAO pgdao = new PrintedGoodsDAO();
    }
}
