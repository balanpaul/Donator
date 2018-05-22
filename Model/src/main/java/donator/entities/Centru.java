package donator.entities;


import javax.naming.Name;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Centre")
public class Centru {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCentru")
    private int IdCentru;

    @Column(name = "Nume")
    private String Nume;

    @OneToMany(mappedBy = "centre", fetch = FetchType.EAGER)
    @JsonBackReference
    private int IdPersonal;

    @OneToMany(mappedBy = "centre", fetch = FetchType.EAGER)
    @JsonBackReference
    private int IdDonator;

    public Centru(int IdCentru, String Nume, int IdPersonal, int IdDonator){
        this.IdCentru=IdCentru;
        this.Nume=Nume;
        this.IdPersonal=IdPersonal;
        this.IdDonator=IdDonator;
    }

    public int getIdCentru() {
        return IdCentru;
    }
    public String getNume() {
        return Nume;
    }
    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdCentru(int idCentru) {
        IdCentru = idCentru;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public void setIdPersonal(int idPersonal) {
        IdPersonal = idPersonal;
    }

    public void setIdDonator(int idDonator) {
        IdDonator = idDonator;
    }

    public int getIdDonator() {
        return IdDonator;
    }
}
