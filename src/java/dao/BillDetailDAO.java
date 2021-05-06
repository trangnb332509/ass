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
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Product;

/**
 *
 * @author Dell Inc
 */
public class BillDetailDAO extends DBContext {

    public Customer getCustomer(String cid, int bid) {
        try {
            String sql = "select c.id,c.name,b.bid,b.[date],b.total,b.paid,p.id,p.name,p.price,bd.quantity\n"
                    + "from Customer c inner join Bill b on c.id = b.cid\n"
                    + "				inner join BillDetail bd on b.bid = bd.bid\n"
                    + "				inner join Product p on bd.pid = p.id\n"
                    + "				where c.id = ? and b.bid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cid);
            pre.setInt(2, bid);
            ResultSet rs = pre.executeQuery();
            Customer customer = null;
            Bill bill = null;
            while (rs.next()) {
                if (customer == null) {
                    customer = new Customer();
                    customer.setId(rs.getString(1));
                    customer.setName(rs.getString(2));
                }
                if (bill == null) {
                    bill = new Bill();
                    bill.setBid(bid);
                    bill.setDate(rs.getDate(4));
                    bill.setTotal(rs.getInt(5));
                    bill.setPaid(rs.getInt(6));
                    customer.getBills().add(bill);
                }
                Product p = new Product();
                p.setId(rs.getInt(7));
                p.setName(rs.getString(8));
                p.setPrice(rs.getInt(9));
                BillDetail billDetail = new BillDetail();
                billDetail.setProducts(p);
                billDetail.setQuantity(rs.getInt(10));
                bill.getBillDetails().add(billDetail);
            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<BillDetail> getBilldetails(int bid) {
        ArrayList<BillDetail> bds = new ArrayList<>();
        try {
            String sql = "SELECT p.name,p.price,bd.quantity\n"
                    + "FROM [ProductionAndTrade].[dbo].[BillDetail] bd\n"
                    + "inner join Product p\n"
                    + "on bd.pid = p.id\n"
                    + "where bid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, bid);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setPrice(rs.getInt(2));
                BillDetail bd = new BillDetail();
                bd.setProducts(p);
                bd.setQuantity(rs.getInt(3));
                bds.add(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bds;
    }

    public void insert(BillDetail bd) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[BillDetail]\n"
                    + "           ([bid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, bd.getBill().getBid());
            pre.setInt(2, bd.getProducts().getId());
            pre.setInt(3, bd.getQuantity());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        BillDetailDAO bddao = new BillDetailDAO();
        ArrayList<BillDetail> bds = bddao.getBilldetails(47);
        for (BillDetail bd : bds) {
            System.out.println(bd.getProducts().getName());
        }
    }
}
