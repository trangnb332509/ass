/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDAO;
import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import model.Customer;

/**
 *
 * @author Dell Inc
 */
public class UpdateBillController extends BaseRequiredAuthenticationController {

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
        int bid = Integer.parseInt(request.getParameter("bid"));
        CustomerDAO cdao = new CustomerDAO();
        Customer c = cdao.getCustomer(cid);
        request.setAttribute("c", c);
        
        BillDAO bdao = new BillDAO();
        Bill b = bdao.getBillByBid(bid);
        request.setAttribute("b", b);
        request.getRequestDispatcher("updateBill.jsp").forward(request, response);
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
        int bid = Integer.parseInt(request.getParameter("bid"));
        Date date = Date.valueOf(request.getParameter("date"));
        int total = Integer.parseInt(request.getParameter("total"));
        int paid = Integer.parseInt(request.getParameter("paid"));
        Bill b = new Bill();
        b.setBid(bid);
        b.setDate(date);
        b.setTotal(total);
        b.setPaid(paid);
        BillDAO bdao = new BillDAO();
        bdao.update(b);
        
        response.sendRedirect("listBill?cid="+cid);
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
