package donator.entities;

import donator.entities.Centru;
import donator.entities.DateSange;

import java.io.Serializable;

public class DatesangeCentre implements Serializable {
    private int IdDatesangeCentre;
    private DateSange dateSange;
    private Centru centru;

    public DatesangeCentre(int IdDatesangeCentre, DateSange idDateSange, Centru idCentru) {
        this.IdDatesangeCentre = IdDatesangeCentre;
        dateSange = idDateSange;
        centru = idCentru;
    }

    public DatesangeCentre(DateSange dateSange, Centru centru) {
        this.dateSange = dateSange;
        this.centru = centru;
    }

    public DatesangeCentre() {
    }

    public int getIdDatesangeCentre() {
        return IdDatesangeCentre;
    }

    public void setIdDatesangeCentre(int idDatesangeCentre) {
        IdDatesangeCentre = idDatesangeCentre;
    }

    public DateSange getIdDateSange() {

        return dateSange;
    }

    public void setIdDateSange(DateSange idDateSange) {
        dateSange = idDateSange;
    }

    public Centru getIdCentru() {
        return centru;
    }

    public void setIdCentru(Centru idCentru) {
        centru = idCentru;
    }
}