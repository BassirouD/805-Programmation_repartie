package urca.diallo.jeetracking.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import urca.diallo.jeetracking.utils.UtilsConnexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "EnregistrementPointsServlet", value = "/EnregistrementPointsServlet")
public class EnregistrementPointsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("coucou");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection con = UtilsConnexion.seConnecter();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            String todayDate = dtf.format(now);
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String actuTime = currentTime.format(formatter);

            String sql = "INSERT INTO planning (activite_id, date, heured) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
