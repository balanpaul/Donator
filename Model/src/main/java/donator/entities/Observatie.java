package donator.entities;

import java.io.Serializable;

public class Observatie implements Serializable {
    private int idObservatie;
    private DateSange idSange;
    private String observatie;

    public int getIdObservatie() {
        return idObservatie;
    }

    public void setIdObservatie(int idObservatie) {
        this.idObservatie = idObservatie;
    }

    public Observatie(int idObservatie, DateSange idSange, String observatie) {
        this.idObservatie = idObservatie;

        this.idSange = idSange;
        this.observatie = observatie;
    }

    public DateSange getIdSange() {

        return idSange;
    }

    public void setIdSange(DateSange idSange) {
        this.idSange = idSange;
    }

    public String getObservatie() {
        return observatie;
    }

    public void setObservatie(String observatie) {
        this.observatie = observatie;
    }
}
