package donator.entities;


import java.io.Serializable;

public class Donator implements Serializable{

    private int IdDonator;
    private String Cnp;
    private String Nume;
    private String Prenume;
    private String DataNasterii;
    private String NrTelefon;
    private String Email;
    private String Judet;
    private String Oras;
    private String Strada;
    private String Apartament;
    private String Bloc;
    private String Scara;
    private String Numar;
    private int CodPostal;
    private double latitudine;
    private double longitudine;

    public Donator() {
    }

    public Donator(int idDonator, String cnp, String nume, String prenume, String dataNasterii, String nrTelefon, String email, String judet, String oras, String strada, String apartament, String bloc, String scara, String numar, int codPostal, double latitudine, double longitudine) {
        IdDonator = idDonator;
        Cnp = cnp;
        Nume = nume;
        Prenume = prenume;
        DataNasterii = dataNasterii;
        NrTelefon = nrTelefon;
        Email = email;
        Judet = judet;
        Oras = oras;
        Strada = strada;
        Apartament = apartament;
        Bloc = bloc;
        Scara = scara;
        Numar = numar;
        CodPostal = codPostal;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Donator(String nume, String prenume, String email) {
        Nume = nume;
        Prenume = prenume;
        Email = email;
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

    public int getIdDonator() {
        return IdDonator;
    }

    public void setIdDonator(int idDonator) {
        IdDonator = idDonator;
    }


    public String getCnp() {
        return Cnp;
    }

    public void setCnp(String cnp) {
        Cnp = cnp;
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

    public String getDataNasterii() {
        return DataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        DataNasterii = dataNasterii;
    }

    public String getNrTelefon() {
        return NrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        NrTelefon = nrTelefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getJudet() {
        return Judet;
    }

    public void setJudet(String judet) {
        Judet = judet;
    }

    public String getOras() {
        return Oras;
    }

    public void setOras(String oras) {
        Oras = oras;
    }

    public String getStrada() {
        return Strada;
    }

    public void setStrada(String strada) {
        Strada = strada;
    }

    public String getApartament() {
        return Apartament;
    }

    public void setApartament(String apartament) {
        Apartament = apartament;
    }

    public String getBloc() {
        return Bloc;
    }

    public void setBloc(String bloc) {
        Bloc = bloc;
    }

    public String getScara() {
        return Scara;
    }

    public void setScara(String scara) {
        Scara = scara;
    }

    public String getNumar() {
        return Numar;
    }

    public void setNumar(String numar) {
        Numar = numar;
    }

    public int getCodPostal() {
        return CodPostal;
    }

    public void setCodPostal(int codPostal) {
        CodPostal = codPostal;
    }

    public Donator(String cnp, String nume, String prenume, String dataNasterii, String nrTelefon, String email, String judet, String oras, String strada, String apartament, String bloc, String scara, String numar, int codPostal) {

        Cnp = cnp;
        Nume = nume;
        Prenume = prenume;
        DataNasterii = dataNasterii;
        NrTelefon = nrTelefon;
        Email = email;

        Judet = judet;
        Oras = oras;
        Strada = strada;
        Apartament = apartament;
        Bloc = bloc;
        Scara = scara;
        Numar = numar;
        CodPostal = codPostal;
    }
    public Donator(String nume, String prenume, String strada, String numar, String bloc, String scara, String apartament, String oras, String judet, String nrTelefon, String email) {

        //Cnp = cnp;
        Nume = nume;
        Prenume = prenume;
        //DataNasterii = dataNasterii;
        NrTelefon = nrTelefon;
        Email = email;
        //IdSange = idSange;
        Judet = judet;
        Oras = oras;
        Strada = strada;
        Apartament = apartament;
        Bloc = bloc;
        Scara = scara;
        Numar = numar;
        //CodPostal = codPostal;
    }

}
