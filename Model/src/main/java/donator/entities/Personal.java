package donator.entities;


import java.io.Serializable;

public class Personal implements Serializable {

    private int IdPersonal;
    private String Nume;
    private String Prenume;
    private String NrTelefon;
    private String parola;
    private String functie;
    private Centru centru;
    public Personal() {
    }

    public Centru getCentru() {
        return centru;
    }

    public void setCentru(Centru centru) {
        this.centru = centru;
    }

    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        IdPersonal = idPersonal;
    }


    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public String getNrTelefon() {
        return NrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        NrTelefon = nrTelefon;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public Personal(int idPersonal, String nume, String prenume, String nrTelefon, String parola, String functie) {
        IdPersonal = idPersonal;
        Nume = nume;
        Prenume = prenume;
        NrTelefon = nrTelefon;
        this.parola = parola;
        this.functie = functie;
    }
}
