package urca.diallo.jeetracking.utils;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import urca.diallo.jeetracking.beans.PlanningBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class EnregistrementPositionGPS implements Runnable {
    private static final double MIN_LATITUDE = 36.879466;
    private static final double MAX_LATITUDE = 37.988888;
    private static final double MIN_LONGITUDE = 30.667648;
    private static final double MAX_LONGITUDE = 31.789216;
    private Long idPlanning;
    private Long activite_id;
    public static int is_stop = 0;

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
                    Random random = new Random();
                    DecimalFormat decimalFormat = new DecimalFormat("#.######");
                    Double longitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * random.nextDouble();
                    Double latitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * random.nextDouble();
                    String formattedLatitude = decimalFormat.format(latitude);
                    String formattedLongitude = decimalFormat.format(longitude);
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
                } else {
                    this.is_stop = 1;
                    PlanningBean.scheduler.shutdown();
                }
            }
        } catch (Exception e) {
            // Gérer les exceptions
        }
    }
}
