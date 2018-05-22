package donator.entities;

public class Donator {
    private int Cnp;
    private String Nume;
    private String Prenume;
    private String DataNasterii;
    private String NrTelefon;
    private String Email;
    private int IdSange;
    private String Judet;
    private String Oras;
    private String Strada;
    private String Apartament;
    private String Bloc;
    private String Scara;
    private String Numar;

    public int getCnp() {
        return Cnp;
    }

    public void setCnp(int cnp) {
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

    public int getIdSange() {
        return IdSange;
    }

    public void setIdSange(int idSange) {
        IdSange = idSange;
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

    public String getCodPostal() {
        return CodPostal;
    }

    public void setCodPostal(String codPostal) {
        CodPostal = codPostal;
    }

    public Donator(int cnp, String nume, String prenume, String dataNasterii, String nrTelefon, String email, int idSange, String judet, String oras, String strada, String apartament, String bloc, String scara, String numar, String codPostal) {

        Cnp = cnp;
        Nume = nume;
        Prenume = prenume;
        DataNasterii = dataNasterii;
        NrTelefon = nrTelefon;
        Email = email;
        IdSange = idSange;
        Judet = judet;
        Oras = oras;
        Strada = strada;
        Apartament = apartament;
        Bloc = bloc;
        Scara = scara;
        Numar = numar;
        CodPostal = codPostal;
    }

    private String CodPostal;
}
