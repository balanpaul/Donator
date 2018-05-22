package donator.entities;

import javax.persistence.*;

//import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "DateSange")
public class DateSange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSange")
    private int IdSange;

    @Column(name = "GrupaSanguina")
    private String GrupaSanguina;

    @Column(name = "Sanatos")
    private Boolean Sanatos;

    @Column(name = "RH")
    private String Rh;

    @Column(name = "Trombocite")
    private int Trombocite;

    @Column(name = "Leucocite")
    private String Leucocite;

    @Column(name = "Hematii")
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
