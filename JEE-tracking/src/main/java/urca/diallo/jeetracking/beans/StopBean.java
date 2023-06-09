package urca.diallo.jeetracking.beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import urca.diallo.jeetracking.utils.UtilsConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ManagedBean
@RequestScoped
public class StopBean {
    public void stopPlanning() throws Exception {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpSession session;

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String actuTime = currentTime.format(formatter);
        session = request.getSession();
        Long idPlanning = (Long) session.getAttribute("idPlanning");
        if (idPlanning != null) {
            Connection con = UtilsConnexion.seConnecter();
            String sql = "UPDATE planning SET heuref = ? WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, actuTime);
            preparedStatement.setLong(2, idPlanning);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            con.close();
            session.removeAttribute("idPlanning");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Planning arrété", "Planning stop done...");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aucun planning en cours", "Planning none...");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
}
