/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmployeeDAO;
import dao.GroupAccountDAO;
import dao.TailorGoodsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee;
import model.TailorGoods;

/**
 *
 * @author Dell Inc
 */
public class ListTailorController extends BaseRequiredAuthenticationController {

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
        String p_size = getServletContext().getInitParameter("pagesize");
        int pagesize = Integer.parseInt(p_size);
        String p_index = request.getParameter("pageindex");
        if(p_index==null)
            p_index = "1";
        int pageindex = Integer.parseInt(p_index);
        
        EmployeeDAO e = new EmployeeDAO();
        Employee ee = e.getEmployeeById(id);
        TailorGoodsDAO tgdao = new TailorGoodsDAO();
        ArrayList<TailorGoods> tgs = tgdao.getTailorGoodsByEmpID(id,pageindex,pagesize);
        
        int total_records = tgdao.count(id);
        int totalpage = (total_records%pagesize==0)
                ? total_records/pagesize
                : (total_records/pagesize)+1;
        int group = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("group")) {
                    group = Integer.parseInt(cooky.getValue());
                }
            }
        }
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("tgs", tgs);
        request.setAttribute("id", id);
        request.setAttribute("ee", ee);
        request.setAttribute("group", group);
        request.getRequestDispatcher("listTailor.jsp").forward(request, response);
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
