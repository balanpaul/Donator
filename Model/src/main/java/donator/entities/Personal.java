package donator.entities;




public class Personal {


    private int IdPersonal;


    private String Functie;


    private String Nume;


    private String Prenume;


    private String NrTelefon;

    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        IdPersonal = idPersonal;
    }

    public String getFunctie() {
        return Functie;
    }

    public void setFunctie(String functie) {
        Functie = functie;
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

    public Personal(int idPersonal, String functie, String nume, String prenume, String nrTelefon) {

        IdPersonal = idPersonal;
        Functie = functie;
        Nume = nume;
        Prenume = prenume;
        NrTelefon = nrTelefon;
    }
}
