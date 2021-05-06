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
public class CustomerDAO extends DBContext {

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[phone]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Customer]";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString(1));
                c.setName(rs.getString(2));
                c.setPhone(rs.getString(3));
                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    public void insert(Customer c) {
        try {
            String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Customer]\n"
                    + "           ([id]\n"
                    + "           ,[name]\n"
                    + "           ,[phone])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, c.getId());
            pre.setString(2, c.getName());
            pre.setString(3, c.getPhone());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getCustomerById(String id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[phone]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[Customer]\n"
                    + "where id = ?  ";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getString(1));
                c.setName(rs.getString(2));
                c.setPhone(rs.getString(3));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Customer c) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[Customer]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[phone] = ?\n"
                    + " WHERE [id] = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, c.getName());
            pre.setString(2, c.getPhone());
            pre.setString(3, c.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String id) {
        try {
            String sql = "DELETE FROM [ProductionAndTrade].[dbo].[Customer]\n"
                    + "      WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getCustomer(String id) {
        try {
            String sql = "select c.id,c.name,b.bid,b.[date],b.total,b.paid,p.id,p.name,p.price,bd.quantity\n"
                    + "from Customer c inner join Bill b on c.id = b.cid\n"
                    + "				inner join BillDetail bd on b.bid = bd.bid\n"
                    + "				inner join Product p on bd.pid = p.id\n"
                    + "				where c.id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            Customer customer = null;
            Bill bill = new Bill();
            bill.setBid(-1);
            while (rs.next()) {
                if (customer == null) {
                    customer = new Customer();
                    customer.setId(rs.getString(1));
                    customer.setName(rs.getString(2));
                }
                int bid = rs.getInt(3);
                if (bid != bill.getBid()) {
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
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer Statistical(String cid) {
        try {
            String sql = "select c.id,c.name,SUM(b.total)as total,SUM(b.paid) as paid\n"
                    + "from Customer c inner join Bill b on c.id = b.cid\n"
                    + "where c.id = ?\n"
                    + "group by c.id,c.name";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, cid);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getString(1));
                c.setName(rs.getString(2));
                c.setTotal(rs.getInt(3));
                c.setPaid(rs.getInt(4));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        CustomerDAO cdao = new CustomerDAO();
        Customer c = cdao.getCustomer("coly");
        for (Bill bill : c.getBills()) {
            System.out.println(bill.getBid());
            System.out.println(bill.getDate());
            System.out.println(bill.getTotal());
            System.out.println(bill.getPaid());
            for (BillDetail billDetail : bill.getBillDetails()) {
                System.out.println(billDetail.getProducts().getName());
                System.out.println(billDetail.getProducts().getPrice());
                System.out.println(billDetail.getQuantity());
            }
        }
    }
}
