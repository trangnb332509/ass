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
import model.Pattern;

/**
 *
 * @author Dell Inc
 */
public class PatternDAO extends DBContext {

    public ArrayList<Pattern> getPatterns() {
        ArrayList<Pattern> patterns = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Pattern]";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Pattern p = new Pattern();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                patterns.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatternDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patterns;
    }

    public ArrayList<Pattern> statisPatterns(String id) {
        ArrayList<Pattern> patterns = new ArrayList<>();
        try {
            String sql = "select pt.name,pt.price,s.sum from Pattern pt\n"
                    + "inner join\n"
                    + "(select SUM(pg.quantity) as [sum], p.id\n"
                    + "from PrintedGoods pg\n"
                    + "inner join Pattern p\n"
                    + "on pg.pid = p.id\n"
                    + "where pg.eid = ?\n"
                    + "group by p.id) s\n"
                    + "on s.id = pt.id";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Pattern p = new Pattern();
                p.setName(rs.getString(1));
                p.setPrice(rs.getInt(2));
                p.setSum(rs.getInt(3));
                p.setMoney();
                patterns.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatternDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patterns;
    }

    public static void main(String[] args) {
        PatternDAO pdao = new PatternDAO();
        ArrayList<Pattern> patterns = pdao.statisPatterns("truongdv");
        for (Pattern pattern : patterns) {
            System.out.println(pattern.getName());
            System.out.println(pattern.getPrice());
            System.out.println(pattern.getSum());
            System.out.println(pattern.getMoney());
        }
    }
}
