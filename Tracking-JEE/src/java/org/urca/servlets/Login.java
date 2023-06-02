/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.urca.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mouba
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getAttribute("email");
        System.out.println("Email========>: dans do get "+email);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = (String) request.getAttribute("email");
        String password = (String) request.getAttribute("password");
        System.out.println("Email========>: " + email+ "Password========>: "+password);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
