package donator.entities;


import java.io.Serializable;

public class Centru implements Serializable{
    private int idCentru;
    private String nume;
    private String adresa;
    private String telefon;
    private String email;
    private double latitudine;
    private double longitudine;


    public Centru() {
    }

    public Centru(int idCentru, String nume, String adresa, String telefon, String email, double latitudine, double longitudine) {

        this.idCentru = idCentru;
        this.nume = nume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }



    public int getIdCentru() {

        return idCentru;
    }

    public void setIdCentru(int idCentru) {
        this.idCentru = idCentru;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }


}
