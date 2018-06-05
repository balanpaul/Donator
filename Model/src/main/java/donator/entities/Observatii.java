package donator.entities;

import java.io.Serializable;

public class Observatii implements Serializable {
    private int idObservatie;

    public int getIdObservatie() {
        return idObservatie;
    }

    public void setIdObservatie(int idObservatie) {
        this.idObservatie = idObservatie;
    }

    private String boala;
    private DateSange dateSange;

    public Observatii(String boala, DateSange dateSange) {
        this.boala = boala;
        this.dateSange = dateSange;
    }

    public Observatii() {
    }

    public int getIdObservatia() {
        return idObservatie;
    }

    public void setIdObservatia(int idObservatia) {
        this.idObservatie = idObservatia;
    }

    public String getBoala() {
        return boala;
    }

    public void setBoala(String boala) {
        this.boala = boala;
    }

    public DateSange getDateSange() {
        return dateSange;
    }

    public void setDateSange(DateSange dateSange) {
        this.dateSange = dateSange;
    }

    @Override
    public String toString() {
        return boala;
    }
}
