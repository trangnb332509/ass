/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Product;

/**
 *
 * @author Dell Inc
 */
public class StatisticalCustomerController extends BaseRequiredAuthenticationController {

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
        ProductDAO pdao = new ProductDAO();
        ArrayList<Product> products = pdao.Statistical(cid);
        CustomerDAO cdao = new CustomerDAO();
        Customer c = cdao.Statistical(cid);
        
        int total = c.getTotal();
        int paid = c.getPaid();
        Locale vn = new Locale("en", "VN");
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vn);
        String tMoney = vnFormat.format(total).substring(3) + " VNĐ";
        String pMoney = vnFormat.format(paid).substring(3) + " VNĐ";
        String lMoney = vnFormat.format(total-paid).substring(3) + " VNĐ";
        
        request.setAttribute("c", c);
        request.setAttribute("tMoney", tMoney);
        request.setAttribute("pMoney", pMoney);
        request.setAttribute("lMoney", lMoney);
        request.setAttribute("products", products);
        request.getRequestDispatcher("statisticalCustomer.jsp").forward(request, response);
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
