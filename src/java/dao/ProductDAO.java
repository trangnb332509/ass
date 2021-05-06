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
import model.Product;

/**
 *
 * @author Dell Inc
 */
public class ProductDAO extends DBContext {

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[quantity]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Product]\n"
                    + "  order by id desc";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product getProductById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "  FROM [ProductionAndTrade].[dbo].[Product]\n"
                + "  where id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void insertProduct(Product p) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Product]\n"
                    + "           ([name]\n"
                    + "           ,[price]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getName());
            pre.setInt(2, p.getPrice());
            pre.setInt(3, p.getQuantity());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProduct(Product p) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[Product]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, p.getName());
            pre.setInt(2, p.getPrice());
            pre.setInt(3, p.getQuantity());
            pre.setInt(4, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProduct(String id) {
        try {
            String sql = "DELETE FROM [ProductionAndTrade].[dbo].[Product]\n"
                    + "      WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> Statistical(String cid) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select p.id,p.name,p.price,SUM(bd.quantity)\n"
                    + "from Customer c inner join Bill b on c.id = b.cid\n"
                    + "				inner join BillDetail bd on b.bid = bd.bid\n"
                    + "				inner join Product p on bd.pid = p.id\n"
                    + "				where c.id = ?\n"
                    + "group by p.id,p.name,p.price";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cid);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPrice(rs.getInt(3));
                p.setQuantity(rs.getInt(4));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }
}
