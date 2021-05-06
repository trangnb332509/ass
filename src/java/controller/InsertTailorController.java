/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dao.TailorGoodsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Employee;
import model.TailorGoods;

/**
 *
 * @author Dell Inc
 */
public class InsertTailorController extends BaseRequiredAuthenticationController {

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
        CategoryDAO cdao = new CategoryDAO();
        ArrayList<Category> categories = cdao.getCategory();
        
        request.setAttribute("id", id);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("insertTailor.jsp").forward(request, response);
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
        TailorGoodsDAO tgdao= new TailorGoodsDAO();
        TailorGoods tg = new TailorGoods();
        Date date = Date.valueOf(request.getParameter("date"));
        tg.setDate(date);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        tg.setQuantity(quantity);
        Category c = new Category();
        int cid = Integer.parseInt(request.getParameter("cid"));
        c.setId(cid);
        tg.setCategory(c);
        Employee e = new Employee();
        e.setId(id);
        tg.setEmp(e);
        tgdao.insertTailorGoods(tg);
        
        response.sendRedirect("listTailor?id="+id);
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
