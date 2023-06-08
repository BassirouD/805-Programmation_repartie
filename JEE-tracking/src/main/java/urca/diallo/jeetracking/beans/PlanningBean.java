package urca.diallo.jeetracking.beans;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.http.HttpServletRequest;
import urca.diallo.jeetracking.entities.Planning;
import urca.diallo.jeetracking.utils.UtilsConnexion;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class PlanningBean implements Serializable {
    private Long id;
    @ManagedProperty(value="#{param.activite_id}")
    private Long activite_id;
    private String date;
    private String heured;
    private String heuref;
    private List<Planning> plannings;

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

    public List<Planning> getPlannings() {
        return plannings;
    }

    public void setPlannings(List<Planning> plannings) {
        this.plannings = plannings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivite_id() {
        return activite_id;
    }

    public void setActivite_id(Long activite_id) {
        this.activite_id = activite_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeured() {
        return heured;
    }

    public void setHeured(String heured) {
        this.heured = heured;
    }

    public String getHeuref() {
        return heuref;
    }

    public void setHeuref(String heuref) {
        this.heuref = heuref;
    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Long activite_id1 = Long.parseLong(request.getParameter("activite_id"));
        this.plannings = getAllPlannigByActivite(activite_id1);
    }

    public List<Planning> getAllPlannigByActivite(Long activite_id){
        this.plannings = new ArrayList<>();
        ResultSet rs = null;
        try {
            Connection con = UtilsConnexion.seConnecter();
            String sql = "SELECT * FROM planning WHERE activite_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, activite_id);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id1 = rs.getLong("id");
                String date1 = rs.getString("date");
                String heured1 = rs.getString("heured");
                String heuref1 = rs.getString("heuref");
                Planning planning = new Planning();
                planning.setId(id1);
                planning.setDate(date1);
                planning.setHeured(heured1);
                planning.setHeuref(heuref1);
                plannings.add(planning);
            }



        }catch (Exception e) {
            e.printStackTrace();
        }
        return plannings;
    }

    public void redirectToPlanning(Long activite_id) throws IOException {
        getAllPlannigByActivite(activite_id);
        externalContext.redirect("planning.xhtml");
    }

}
