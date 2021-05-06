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
import model.Bill;
import model.Customer;

/**
 *
 * @author Dell Inc
 */
public class BillDAO extends DBContext {

    public ArrayList<Bill> getBillsByCidDate(String cid, Date date) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            String sql = "SELECT [bid]\n"
                    + ",[date]\n"
                    + ",[total]\n"
                    + ",[paid]\n"
                    + "FROM [ProductionAndTrade].[dbo].[Bill]\n"
                    + "where cid = ? and [date] like ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cid);
            pre.setDate(2, date);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBid(rs.getInt(1));
                bill.setDate(rs.getDate(2));
                bill.setTotal(rs.getInt(3));
                bill.setPaid(rs.getInt(4));
                bills.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bills;
    }

    public ArrayList<Bill> getBillsByCid(String cid, int pageIndex, int pageSize) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            String sql = "select tbl.bid,tbl.[date],tbl.total,tbl.paid from\n"
                    + "(select ROW_NUMBER() OVER (Order by bid asc) as rid, [bid],[date],[total],[paid]\n"
                    + "FROM [ProductionAndTrade].[dbo].[Bill]\n"
                    + "where cid = ?) tbl\n"
                    + "where rid between (?-1)*?+1 and ?*?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cid);
            pre.setInt(2, pageIndex);
            pre.setInt(3, pageSize);
            pre.setInt(4, pageIndex);
            pre.setInt(5, pageSize);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBid(rs.getInt(1));
                bill.setDate(rs.getDate(2));
                bill.setTotal(rs.getInt(3));
                bill.setPaid(rs.getInt(4));
                bills.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bills;
    }

    public int count(String cid) {
        try {
            String sql = "select COUNT(*)\n"
                    + "FROM [ProductionAndTrade].[dbo].[Bill]\n"
                    + "where cid = ?";
            PreparedStatement pre =connection.prepareStatement(sql);
            pre.setString(1, cid);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void insert(Bill bill) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Bill]\n"
                    + "           ([date]\n"
                    + "           ,[total]\n"
                    + "           ,[paid]\n"
                    + "           ,[cid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, bill.getDate());
            pre.setInt(2, bill.getTotal());
            pre.setInt(3, bill.getPaid());
            pre.setString(4, bill.getCustomer().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertBill(Bill bill) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Bill]\n"
                    + "           ([date]\n"
                    + "           ,[total]\n"
                    + "           ,[paid]\n"
                    + "           ,[cid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setDate(1, bill.getDate());
            pre.setInt(2, bill.getTotal());
            pre.setInt(3, bill.getPaid());
            pre.setString(4, bill.getCustomer().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Bill bill) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[Bill]\n"
                    + "   SET [total] = ?\n"
                    + "      ,[paid] = ?\n"
                    + " WHERE bid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, bill.getTotal());
            pre.setInt(2, bill.getPaid());
            pre.setInt(3, bill.getBid());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bill getBillByBid(int bid) {
        try {
            String sql = "SELECT [bid]\n"
                    + "      ,[date]\n"
                    + "      ,[total]\n"
                    + "      ,[paid]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Bill]\n"
                    + "  where bid = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, bid);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBid(rs.getInt(1));
                bill.setDate(rs.getDate(2));
                bill.setTotal(rs.getInt(3));
                bill.setPaid(rs.getInt(4));
                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Bill getNearestBill() {
        try {
            String sql = "SELECT top 1 [bid]\n"
                    + "      ,[date]\n"
                    + "      ,[total]\n"
                    + "      ,[paid]\n"
                    + "      ,[cid]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Bill]\n"
                    + "  order by bid desc";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBid(rs.getInt(1));
                bill.setDate(rs.getDate(2));
                bill.setTotal(rs.getInt(3));
                bill.setPaid(rs.getInt(4));
                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        BillDAO bdao = new BillDAO();
        Customer c = new Customer();
        c.setId("lammanh");
        Bill b = new Bill();
        Date date = Date.valueOf("2021-03-20");
        b.setDate(date);
        b.setCustomer(c);
        b.setTotal(0);
        b.setPaid(0);
        bdao.insert(b);
    }
}
