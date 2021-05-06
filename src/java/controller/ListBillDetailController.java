/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author Dell Inc
 */
public class ListBillDetailController extends BaseRequiredAuthenticationController {

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
        String cid = request.getParameter("cid");
        String raw_Date = request.getParameter("date");
        Date date = Date.valueOf(raw_Date);
        request.setAttribute("date", raw_Date);
        int bid = Integer.parseInt(request.getParameter("bid"));
        BillDetailDAO bddao = new BillDetailDAO();
        Customer c = bddao.getCustomer(cid, bid);
        int total = c.getBills().get(0).getTotal();
        int paid = c.getBills().get(0).getPaid();
        Locale vn = new Locale("en", "VN");
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vn);
        String tMoney = vnFormat.format(total).substring(3) + " VNĐ";
        String pMoney = vnFormat.format(paid).substring(3) + " VNĐ";
        
        request.setAttribute("tMoney", tMoney);
        request.setAttribute("pMoney", pMoney);
        request.setAttribute("c", c);
        request.getRequestDispatcher("listBillDetail.jsp").forward(request, response);
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
