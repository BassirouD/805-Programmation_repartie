package org.urca.Beans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.urca.utils.UtilsConnexion;


@ManagedBean
@RequestScoped
public class InscriptionBean {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int age;
    private Double poids;
    
    private String message;

    public InscriptionBean() {
    }

    public InscriptionBean(String nom, String prenom, String email, String password, int age, Double poids) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.age = age;
        this.poids = poids;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }
    
    public void register() throws IOException{
        
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
            message="Inscription réussie avec succès!!!";
            nom="";
            prenom="";
            email="";
            password="";
            age=0;
            poids=0.0;
        }catch(Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        
        
    }
    
}
