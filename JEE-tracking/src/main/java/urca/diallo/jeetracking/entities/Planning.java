package urca.diallo.jeetracking.entities;

public class Planning {
    private Long id;
    private Long activite_id;
    private String date;
    private String heured;
    private String heuref;

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


}
