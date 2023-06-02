package org.urca.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.urca.utils.UtilsConnexion;


@WebServlet(name = "Inscription", urlPatterns = {"/register"})
public class Inscription extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Récupérez les paramètres du formulaire d'inscription
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        double poids = Double.parseDouble(request.getParameter("poids"));
        
        try{
            Connection con = UtilsConnexion.seConnecter();
            
            // Requête d'insertion des données
            String sql = "INSERT INTO sportif (nom, prenom, email, password, age, poids) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.setInt(5, age);
            preparedStatement.setDouble(6, poids);
            
              // Exécution de la requête
            preparedStatement.executeUpdate();

            // Fermeture des ressources
            preparedStatement.close();
            con.close();

            HttpSession session = request.getSession(true);
            session.setAttribute("msg", "Enregistrement réussi !");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Done!");
        }catch(Exception e) {
            // Gestion des exceptions
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("Erreur : " + e.getMessage());
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
