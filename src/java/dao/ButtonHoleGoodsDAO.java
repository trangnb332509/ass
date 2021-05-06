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
import model.ButtonHole;
import model.ButtonholeGoods;

/**
 *
 * @author Dell Inc
 */
public class ButtonHoleGoodsDAO extends DBContext {

    public ArrayList<ButtonholeGoods> getById(String id, int pageIndex, int pageSize) {
        ArrayList<ButtonholeGoods> bgs = new ArrayList<>();
        try {
            String sql = "select tbl.id,tbl.[date],tbl.quantity,tbl.bhquantity,tbl.bid,tbl.name,tbl.price\n"
                    + "from\n"
                    + "(select ROW_NUMBER() OVER (Order by bg.id asc) as rid,bg.id,bg.[date],bg.bhquantity,bg.quantity,b.id as bid,b.name,b.price \n"
                    + "from ButtonholeGoods bg\n"
                    + "inner join ButtonHole b\n"
                    + "on bg.bhid = b.id\n"
                    + "where bg.eid = ?) tbl\n"
                    + "where rid between (?-1)*?+1 and ?*?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setInt(2, pageIndex);
            pre.setInt(3, pageSize);
            pre.setInt(4, pageIndex);
            pre.setInt(5, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ButtonholeGoods bg = new ButtonholeGoods();
                bg.setId(rs.getInt(1));
                bg.setDate(rs.getDate(2));
                bg.setBhquantity(rs.getInt(3));
                bg.setQuantity(rs.getInt(4));

                ButtonHole b = new ButtonHole();
                b.setId(rs.getInt(5));
                b.setName(rs.getString(6));
                b.setPrice(rs.getInt(7));

                bg.setButtonhole(b);
                bgs.add(bg);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bgs;
    }

    public ButtonholeGoods getButtonholeGood(String id, Date date) {
        try {
            String sql = "select bhg.id,bhg.[date],bhg.bhquantity,bhg.quantity,bh.id,bh.name\n"
                    + "from ButtonholeGoods bhg\n"
                    + "inner join ButtonHole bh\n"
                    + "on bhg.bhid = bh.id\n"
                    + "where bhg.eid = ? and bhg.[date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                ButtonholeGoods bg = new ButtonholeGoods();
                bg.setId(rs.getInt(1));
                bg.setDate(rs.getDate(2));
                bg.setBhquantity(rs.getInt(3));
                bg.setQuantity(rs.getInt(4));

                ButtonHole b = new ButtonHole();
                b.setId(rs.getInt(5));
                b.setName(rs.getString(6));

                bg.setButtonhole(b);
                return bg;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertButtonHoleGoods(ButtonholeGoods bhg) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[ButtonholeGoods]\n"
                    + "           ([date]\n"
                    + "           ,[quantity]\n"
                    + "           ,[bhquantity]\n"
                    + "           ,[bhid]\n"
                    + "           ,[eid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, bhg.getDate());
            pre.setInt(2, bhg.getQuantity());
            pre.setInt(3, bhg.getBhquantity());
            pre.setInt(4, bhg.getButtonhole().getId());
            pre.setString(5, bhg.getEmp().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(ButtonholeGoods bhg) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[ButtonholeGoods]\n"
                    + "   SET [quantity] = ?\n"
                    + "	,[bhquantity] = ?\n"
                    + "      ,[bhid] = ?    \n"
                    + " WHERE [eid] = ? and [date] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, bhg.getQuantity());
            pre.setInt(2, bhg.getBhquantity());
            pre.setInt(3, bhg.getButtonhole().getId());
            pre.setString(4, bhg.getEmp().getId());
            pre.setDate(5, bhg.getDate());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteById(String id) {
        try {
            String sql = "delete from ButtonholeGoods\n"
                    + "where eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count(String id) {
        try {
            String sql = "select COUNT(*) as total \n"
                    + "from ButtonholeGoods bg\n"
                    + "inner join ButtonHole b\n"
                    + "on bg.bhid = b.id\n"
                    + "where bg.eid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleGoodsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        ButtonHoleGoodsDAO dao = new ButtonHoleGoodsDAO();
    }
}
