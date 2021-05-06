/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import model.Customer;
import model.Product;

/**
 *
 * @author Dell Inc
 */
public class InsertBillController extends BaseRequiredAuthenticationController {

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
        
        CustomerDAO cdao = new CustomerDAO();
        Customer c = cdao.getCustomer(cid);
                
        request.setAttribute("c", c);
        request.getRequestDispatcher("insertBill.jsp").forward(request, response);
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
        String cid = request.getParameter("cid");
        Date date = Date.valueOf(request.getParameter("date"));
        String raw_paid = request.getParameter("paid");
        if(raw_paid==null || raw_paid.length()==0){
            raw_paid = "0";
        }
        int paid = Integer.parseInt(raw_paid);
        BillDAO bdao = new BillDAO();
        Bill bill = new Bill();
        bill.setDate(date);
        bill.setTotal(0);
        bill.setPaid(paid);
        Customer c = new Customer();
        c.setId(cid);
        bill.setCustomer(c);
        
        bdao.insertBill(bill);
        
        request.getSession().setAttribute("bill", bill);
        response.sendRedirect("insertBillDetail?cid="+cid);
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
