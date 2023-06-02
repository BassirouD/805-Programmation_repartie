package org.urca.Beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.urca.entities.Activite;
import org.urca.utils.UtilsConnexion;


@ManagedBean
@SessionScoped
public class ActiviteBean  implements Serializable {
    private List<Activite> activites;

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }
    
    @PostConstruct
    public void init() {
        this.activites = getActivitesByUsername();
    }
    
    public List<Activite> getActivitesByUsername() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session = request.getSession();
        int id1 = (int) session.getAttribute("id");
        System.out.println("----------------------------------------------------------------------------" + id1);
        List<Activite> activites = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            Connection con = UtilsConnexion.seConnecter();
            String sql = "SELECT * FROM activite WHERE sportif_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id1);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nom = rs.getString("nom");
                Activite activite = new Activite();
                activite.setId(id);
                activite.setNom(nom);
                activites.add(activite);
                System.out.println("IdAc:=======> "+activite.getNom());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activites;
    }
}
