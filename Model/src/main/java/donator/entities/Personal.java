package donator.entities;


import java.io.Serializable;

public class Personal implements Serializable {

    private int IdPersonal;
    private String Nume;
    private String Prenume;
    private String NrTelefon;
    private String parola;

    public Personal() {
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

    public Personal(int idPersonal, String nume, String prenume, String nrTelefon, String parola) {
        IdPersonal = idPersonal;
        Nume = nume;
        Prenume = prenume;
        NrTelefon = nrTelefon;
        this.parola = parola;
    }
}
