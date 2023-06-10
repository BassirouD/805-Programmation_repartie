package urca.diallo.jeetracking.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EnregistrementPositionGPS implements Runnable {
    private Long idPlanning;
    private Long activite_id;
    private int cmpt = 0;

    public EnregistrementPositionGPS(Long idPlanning) {
        this.idPlanning = idPlanning;
    }

    public EnregistrementPositionGPS(Long idPlanning, Long activite_id) {
        this.idPlanning = idPlanning;
        this.activite_id = activite_id;
    }

    @Override
    public void run() {
        ResultSet rs = null;
        try {
            Connection con = UtilsConnexion.seConnecter();
            String sql1 = "SELECT * FROM planning WHERE id = ?";
            PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
            preparedStatement1.setLong(1, idPlanning);
            rs = preparedStatement1.executeQuery();
            if (rs.next()) {
                String heuref = rs.getString(5);
                if (heuref == null) {
                    String sql = "INSERT INTO point (planning_id, latitude, longitude, heure) VALUES (?, ?, ?, ?)";
                    Double longitude = Math.random() * (90 - (-90)) - 90;
                    Double latitude = Math.random() * (180 - (-180)) - 180;
                    LocalTime currentTime = LocalTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String hour = currentTime.format(formatter);
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setLong(1, idPlanning);
                    preparedStatement.setDouble(2, latitude);
                    preparedStatement.setDouble(3, longitude);
                    preparedStatement.setString(4, hour);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    con.close();
                }else {

                }
            }


        } catch (Exception e) {
            // Gérer les exceptions
        }
    }
}
