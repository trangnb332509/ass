/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AdvanceMoneyDAO;
import dao.ButtonHoleDAO;
import dao.CategoryDAO;
import dao.EmployeeDAO;
import dao.EmployeeTypeDAO;
import dao.PatternDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ButtonHole;
import model.Category;
import model.Employee;
import model.Pattern;

/**
 *
 * @author Dell Inc
 */
public class StatisticalTailorController extends BaseRequiredAuthenticationController {

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
        int sumMoney = 0;
        EmployeeDAO e = new EmployeeDAO();
        Employee ee = e.getEmployeeById(id);
        EmployeeTypeDAO etdao = new EmployeeTypeDAO();
        int etid = etdao.geteTypeId(id);
        if (etid == 1) {
            CategoryDAO cdao = new CategoryDAO();
            ArrayList<Category> common = cdao.statistical(id);
            for (Category category : common) {
                sumMoney += category.getMoney();
            }
            request.setAttribute("common", common);
        } else if (etid == 2) {
            PatternDAO pdao = new PatternDAO();
            ArrayList<Pattern> common = pdao.statisPatterns(id);
            for (Pattern pattern : common) {
                sumMoney += pattern.getMoney();
            }
            request.setAttribute("common", common);
        } else {
            ButtonHoleDAO bhdao = new ButtonHoleDAO();
            ArrayList<ButtonHole> common = bhdao.statisButtonHole(id);
            for (ButtonHole buttonHole : common) {
                sumMoney += buttonHole.getMoney();
            }
            request.setAttribute("common", common);
        }
        
        
        
        AdvanceMoneyDAO addao = new AdvanceMoneyDAO();
        int total = addao.getTotal(id);
        
        int currencyAmount = total;
        Locale vn = new Locale("en", "VN");
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(vn);
        String formatMoney = vnFormat.format(currencyAmount).substring(3) + " VNĐ";
        String sSumMoney = vnFormat.format(sumMoney).substring(3) + " VNĐ";
        String debt = vnFormat.format(sumMoney-currencyAmount).substring(3) + " VNĐ";
        
        request.setAttribute("id", id);
        request.setAttribute("formatMoney", formatMoney);
        request.setAttribute("sSumMoney", sSumMoney);
        request.setAttribute("debt", debt);
        request.setAttribute("etid", etid);
        request.setAttribute("ee", ee);
        request.getRequestDispatcher("statisticalTailor.jsp").forward(request, response);
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
