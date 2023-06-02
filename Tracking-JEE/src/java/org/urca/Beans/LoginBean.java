package org.urca.Beans;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.urca.utils.UtilsConnexion;

@ManagedBean
@RequestScoped
public class LoginBean {
    private String email;
    private String password;
    private String msg;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() throws IOException{
        ResultSet rs = null;
        try{
            Connection con = UtilsConnexion.seConnecter();
            
            // Requête SQL pour vérifier les informations de connexion
            String sql = "SELECT * FROM sportif WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = Integer.parseInt(rs.getString(3));
                nom = rs.getString(5);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("utilisateur", email);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message succes", "Auth résussi." + nom);
                msg = "Auth résussi." + id;
                
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
                
                HttpServletRequest request1 = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                System.out.println("#################################"+id);
                request1.getSession().setAttribute("id", id);
                
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
                session.setAttribute("nom", nom);
                
                
                int valeur = (int) session.getAttribute("id");
                
                externalContext.redirect("accueil.xhtml");
            } else {
                msg = "Identifiants invalides.";
                // L'authentification a échoué
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiants invalides", "Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }catch(Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de base de données", "Une erreur s'est produite lors de la connexion à la base de données.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
     public void envoyerFormulaire() throws IOException {
        // Accéder à la requête HTTP
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        
        String contextPath = externalContext.getRequestContextPath();
        // URL de la servlet
        String servletURL = "http://localhost:8080/Tracking-JEE/Login";
        URL url = new URL(servletURL);
         System.out.println("============================"+url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
 // Paramètres à envoyer à la servlet
        String params = "email=" + email + "&password=" + password;

        // Ajouter les données dans la requête
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        // Ajouter d'autres attributs du formulaire à la requête
        
        // Envoi des paramètres à la servlet
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] postData = params.getBytes(StandardCharsets.UTF_8);
            outputStream.write(postData);
        }

        
    }
}
