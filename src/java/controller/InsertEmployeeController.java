/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.EmployeeDAO;
import dao.EmployeeTypeDAO;
import dao.GroupAccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;
import model.EmployeeType;
import model.Group;

/**
 *
 * @author Dell Inc
 */
public class InsertEmployeeController extends BaseRequiredAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeTypeDAO etdao = new EmployeeTypeDAO();
        ArrayList<EmployeeType> eTypes = etdao.geteType();
        request.setAttribute("eTypes", eTypes);
        request.getRequestDispatcher("insertEmployee.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); 
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        EmployeeType et = new EmployeeType();
        int etid = Integer.parseInt(request.getParameter("etid"));
        
        et.setId(etid);
        String password = request.getParameter("password");
        
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setPhone(phone);
        e.setAddress(address);
        e.seteType(et);
        Account a = new Account();
        a.setUsername(id);
        a.setPassword(password);
        
        EmployeeDAO edao = new EmployeeDAO();
        edao.insertEmployee(e);
        
        AccountDAO adao = new AccountDAO();
        adao.insertAccount(a);
        
        GroupAccountDAO gadao = new GroupAccountDAO();
        if(etid==1){
            gadao.insert(2, id);
        }else if(etid==2){
            gadao.insert(4, id);
        }else{
            gadao.insert(5, id);
        }
        response.sendRedirect("listEmployee");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
