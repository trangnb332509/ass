/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import model.BillDetail;
import model.Customer;
import model.Product;

/**
 *
 * @author Dell Inc
 */
public class InsertBillDetailController extends BaseRequiredAuthenticationController {

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
        ArrayList<Product> products = pdao.getProducts();

        
        CustomerDAO cdao = new CustomerDAO();
        Customer customer = cdao.getCustomer(cid);
        request.setAttribute("customer", customer);
        
        BillDetailDAO bddao = new BillDetailDAO();
        Bill bill = (Bill) request.getSession().getAttribute("bill");
        BillDAO bdao = new BillDAO();
        Bill billid = bdao.getNearestBill();
        ArrayList<BillDetail> bds = bddao.getBilldetails(billid.getBid());
        System.out.println(bds.size());
        request.setAttribute("bds", bds);
        request.setAttribute("bill", bill);
        request.setAttribute("products", products);
        request.getRequestDispatcher("insertBillDetail.jsp").forward(request, response);
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
        BillDAO bdao = new BillDAO();
        Bill bill = bdao.getNearestBill();
        
        int pid = Integer.parseInt(request.getParameter("pid"));
        ProductDAO pdao = new ProductDAO();
        Product p = pdao.getProductById(pid);
        
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        BillDetailDAO bddao = new BillDetailDAO();
        BillDetail bd = new BillDetail();
        bd.setBill(bill);
        bd.setProducts(p);
        bd.setQuantity(quantity);
        bddao.insert(bd);
        
        int total = p.getPrice()*quantity+bill.getTotal();
        bill.setTotal(total);
        
        p.setQuantity((p.getQuantity()-quantity));
        
        pdao.updateProduct(p);
        bdao.update(bill);
        
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
