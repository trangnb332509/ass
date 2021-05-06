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
import model.ButtonHole;

/**
 *
 * @author Dell Inc
 */
public class ButtonHoleDAO extends DBContext {

    public ArrayList<ButtonHole> getButtonHole() {
        ArrayList<ButtonHole> buttonholes = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[ButtonHole]";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ButtonHole bh = new ButtonHole();
                bh.setId(rs.getInt(1));
                bh.setName(rs.getString(2));
                bh.setPrice(rs.getInt(3));
                buttonholes.add(bh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buttonholes;
    }

    public ArrayList<ButtonHole> statisButtonHole(String id) {
        ArrayList<ButtonHole> buttonholes = new ArrayList<>();
        try {
            String sql = "select b.name,b.price,s.bhquantity,s.sum from ButtonHole b\n"
                    + "inner join \n"
                    + "(select SUM(bg.quantity) as [sum], bh.id,bg.bhquantity\n"
                    + "from ButtonholeGoods bg\n"
                    + "inner join ButtonHole bh\n"
                    + "on bg.bhid = bh.id\n"
                    + "where bg.eid = ?\n"
                    + "group by bh.id, bg.bhquantity) s\n"
                    + "on s.id = b.id";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                ButtonHole bh = new ButtonHole();
                bh.setName(rs.getString(1));
                bh.setPrice(rs.getInt(2));
                bh.setBhquantity(rs.getInt(3));
                bh.setSum(rs.getInt(4));
                bh.setMoney();
                buttonholes.add(bh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ButtonHoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return buttonholes;
    }

    public static void main(String[] args) {
        ButtonHoleDAO bhdao = new ButtonHoleDAO();
        ArrayList<ButtonHole> buttonholes = bhdao.statisButtonHole("thangnb");
        for (ButtonHole buttonhole : buttonholes) {
            System.out.println(buttonhole.getName());
            System.out.println(buttonhole.getPrice());
            System.out.println(buttonhole.getSum());
            System.out.println(buttonhole.getMoney());
        }
    }
}
