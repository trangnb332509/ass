/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PatternDAO;
import dao.PrintedGoodsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.Pattern;
import model.PrintedGoods;

/**
 *
 * @author Dell Inc
 */
public class InsertPrintedController extends BaseRequiredAuthenticationController {

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
        PatternDAO pdao = new PatternDAO();
        ArrayList<Pattern> patterns = pdao.getPatterns();
        
        request.setAttribute("patterns", patterns);
        request.setAttribute("id", id);
        request.getRequestDispatcher("insertPrinted.jsp").forward(request, response);
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
        String id = request.getParameter("eid");
        Date date = Date.valueOf(request.getParameter("date"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        Pattern p = new Pattern();
        p.setId(pid);
        Employee e = new Employee();
        e.setId(id);
        PrintedGoodsDAO pgdao = new PrintedGoodsDAO();
        PrintedGoods pg = new PrintedGoods();
        pg.setDate(date);
        pg.setQuantity(quantity);
        pg.setPattern(p);
        pg.setEmp(e);
        pgdao.insertPrintedGoods(pg);
        response.sendRedirect("listPrinted?id="+id);
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
