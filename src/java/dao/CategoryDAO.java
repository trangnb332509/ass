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
import model.Category;

/**
 *
 * @author Dell Inc
 */
public class CategoryDAO extends DBContext {

    public ArrayList<Category> getCategory() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Category]";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setPrice(rs.getInt(3));
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public ArrayList<Category> statistical(String id) {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sql = "select ca.name,ca.price,s.sum from Category ca\n"
                    + "inner join\n"
                    + "(select SUM(tg.quantity) as [sum], c.id\n"
                    + "from TailorGoods tg\n"
                    + "inner join Category c\n"
                    + "on c.id = tg.cid\n"
                    + "where eid = ?\n"
                    + "group by c.id) s\n"
                    + "on ca.id = s.id";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setName(rs.getString(1));
                c.setPrice(rs.getInt(2));
                c.setSum(rs.getInt(3));
                c.setMoney();
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public static void main(String[] args) {
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> categories = cdao.statistical("maint");
        for (Category category : categories) {
            System.out.println(category.getName());
            System.out.println(category.getPrice());
            System.out.println(category.getSum());
            System.out.println(category.getMoney());
        }
    }
}
