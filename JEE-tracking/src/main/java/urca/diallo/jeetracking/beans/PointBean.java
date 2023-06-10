package urca.diallo.jeetracking.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import urca.diallo.jeetracking.utils.UtilsConnexion;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Named
@ViewScoped
public class PointBean implements Serializable {
}
