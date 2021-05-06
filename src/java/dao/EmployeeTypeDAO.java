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
import model.EmployeeType;

/**
 *
 * @author Dell Inc
 */
public class EmployeeTypeDAO extends DBContext {

    public ArrayList<EmployeeType> geteType() {
        ArrayList<EmployeeType> eTypes = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "  FROM [ProductionAndTrade].[dbo].[EmployeeType]";
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                EmployeeType et = new EmployeeType();
                et.setId(rs.getInt(1));
                et.setName(rs.getString(2));
                eTypes.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eTypes;
    }

    public int geteTypeId(String id) {
        try {
            String sql = "select et.id from Employee e\n"
                    + "inner join EmployeeType et\n"
                    + "on e.etid = et.id\n"
                    + "where e.id = ?";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
