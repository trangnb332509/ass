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
import model.Employee;
import model.EmployeeType;

/**
 *
 * @author Dell Inc
 */
public class EmployeeDAO extends DBContext {

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> emps = new ArrayList<>();
        try {
            String sql = "select e.id,e.name,e.phone,e.[address],et.id,et.name \n"
                    + "from Employee e\n"
                    + "inner join EmployeeType et\n"
                    + "on e.etid = et.id";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getString(1));
                e.setName(rs.getString(2));
                e.setPhone(rs.getString(3));
                e.setAddress(rs.getString(4));
                EmployeeType et = new EmployeeType();
                et.setId(rs.getInt(5));
                et.setName(rs.getString(6));
                e.seteType(et);
                emps.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emps;
    }

    public void insertEmployee(Employee e) {
        String sql = "INSERT INTO [ProductionAndTrade].[dbo].[Employee]\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[etid])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, e.getId());
            pre.setString(2, e.getName());
            pre.setString(3, e.getPhone());
            pre.setString(4, e.getAddress());
            pre.setInt(5, e.geteType().getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteEmployee(String id) {
        try {
            String sql = "DELETE FROM [ProductionAndTrade].[dbo].[Employee]\n"
                    + "      WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployee(Employee e) {
        try {
            String sql = "UPDATE [ProductionAndTrade].[dbo].[Employee]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[phone] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[etid] = ?\n"
                    + " WHERE id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, e.getName());
            pre.setString(2, e.getPhone());
            pre.setString(3, e.getAddress());
            pre.setInt(4, e.geteType().getId());
            pre.setString(5, e.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Employee getEmployeeById(String id) {
        try {
            String sql = "select e.id,e.name,e.phone,e.[address],et.id,et.name \n"
                    + "from Employee e\n"
                    + "inner join EmployeeType et\n"
                    + "on e.etid = et.id\n"
                    + "where e.id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getString(1));
                e.setName(rs.getString(2));
                e.setPhone(rs.getString(3));
                e.setAddress(rs.getString(4));
                EmployeeType et = new EmployeeType();
                et.setId(rs.getInt(5));
                et.setName(rs.getString(6));
                e.seteType(et);
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        EmployeeDAO e = new EmployeeDAO();
        EmployeeType et = new EmployeeType();
        et.setId(1);
        Employee emp = new Employee();
        emp.setId("trangg");
        emp.setName("Nguyen ba");
        emp.setPhone("1234567890");
        emp.setAddress("nl");
        emp.seteType(et);
        e.updateEmployee(emp);
        System.out.println("success");
    }
}
