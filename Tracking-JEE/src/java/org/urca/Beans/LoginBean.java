package org.urca.Beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.urca.utils.UtilsConnexion;

@ManagedBean
@RequestScoped
public class LoginBean {
    private String email;
    private String password;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
                System.out.println("---------------------"+rs.getString(1));
                // L'utilisateur est authentifié avec succès
                // Effectuez les actions nécessaires, par exemple, enregistrer l'utilisateur dans la session
                // et rediriger vers une autre page
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("utilisateur", email);
                FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message succes", "Auth résussi.");
                message = "Auth résussi.";
                
            } else {
                message = "Identifiants invalides.";
                // L'authentification a échoué
                FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Identifiants invalides", "Veuillez vérifier votre nom d'utilisateur et votre mot de passe.");
                FacesContext.getCurrentInstance().addMessage(null, message2);
            }
        }catch(Exception e) {
            System.out.println("Error " + e.getMessage());
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur de base de données", "Une erreur s'est produite lors de la connexion à la base de données.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
