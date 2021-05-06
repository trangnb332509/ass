/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeeDAO;
import dao.EmployeeTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.EmployeeType;

/**
 *
 * @author Dell Inc
 */
public class UpdateEmployeeController extends BaseRequiredAuthenticationController {

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
        String id = request.getParameter("id");
        EmployeeTypeDAO etdao = new EmployeeTypeDAO();
        ArrayList<EmployeeType> eTypes = etdao.geteType();
        EmployeeDAO edao = new EmployeeDAO();
        Employee e = edao.getEmployeeById(id);
        
        request.setAttribute("eTypes", eTypes);
        request.setAttribute("e", e);
        request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
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
        System.out.println(id);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        EmployeeType et = new EmployeeType();
        String etid = request.getParameter("etid");
        et.setId(Integer.parseInt(etid));
        
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setPhone(phone);
        e.setAddress(address);
        e.seteType(et);

        EmployeeDAO edao = new EmployeeDAO();
        edao.updateEmployee(e);
    
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
