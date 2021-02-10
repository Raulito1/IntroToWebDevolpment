/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raulalvarado
 */
@WebServlet(name = "FlooringServlet", urlPatterns = {"/FlooringServlet"})
public class FlooringServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BigDecimal width = new BigDecimal(request.getParameter("width"));
        BigDecimal length = new BigDecimal(request.getParameter("length"));
        BigDecimal costPerSqFt = new BigDecimal(request.getParameter("costPerSqFt"));


        //do our calculations
        BigDecimal totalSqFt = width.multiply(length);
        BigDecimal totalCost = costPerSqFt.multiply(totalSqFt);
        BigDecimal totalLabor = totalSqFt.divide(new BigDecimal("20"), RoundingMode.UP).multiply(new BigDecimal("86"));
        BigDecimal overall = totalCost.add(totalLabor);

        //set results to attributes on the request
        //set all of the results on the requestattributes map
        request.setAttribute("width", width);
        request.setAttribute("length", length);
        request.setAttribute("costPerSqFt", costPerSqFt);
        request.setAttribute("totalSqFt", totalSqFt);
        request.setAttribute("totalCost", totalCost);
        request.setAttribute("totalLabor", totalLabor);
        request.setAttribute("overall", overall);
        

        
       
                
//        request.setAttribute("isPrime", isPrime);
//        request.setAttribute("isPerfect", isPerfect);

        //forward the control over to the result.jsp        //place to dipatch to
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        //forward it on to result.jsp
        rd.forward(request, response);
    
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
