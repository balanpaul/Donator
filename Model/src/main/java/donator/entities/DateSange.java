package donator.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


public class DateSange {
    private int IdSange;
    private String GrupaSanguina;
    private Boolean Sanatos;
    private String Rh;
    private int Trombocite;
    private String Leucocite;
    private String Hematii;

    public DateSange(int idSange, String grupaSanguina, Boolean sanatos, String rh, int trombocite, String leucocite, String hematii) {
        IdSange = idSange;
        GrupaSanguina = grupaSanguina;
        Sanatos = sanatos;
        Rh = rh;
        Trombocite = trombocite;
        Leucocite = leucocite;
        Hematii = hematii;
    }

    public int getIdSange() {
        return IdSange;
    }

    public void setIdSange(int idSange) {
        IdSange = idSange;
    }

    public String getGrupaSanguina() {
        return GrupaSanguina;
    }

    public void setGrupaSanguina(String grupaSanguina) {
        GrupaSanguina = grupaSanguina;
    }

    public Boolean getSanatos() {
        return Sanatos;
    }

    public void setSanatos(Boolean sanatos) {
        Sanatos = sanatos;
    }

    public String getRh() {
        return Rh;
    }

    public void setRh(String rh) {
        Rh = rh;
    }

    public int getTrombocite() {
        return Trombocite;
    }

    public void setTrombocite(int trombocite) {
        Trombocite = trombocite;
    }

    public String getLeucocite() {
        return Leucocite;
    }

    public void setLeucocite(String leucocite) {
        Leucocite = leucocite;
    }

    public String getHematii() {
        return Hematii;
    }

    public void setHematii(String hematii) {
        Hematii = hematii;
    }
}
