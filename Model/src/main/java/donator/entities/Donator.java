package donator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Donatori")
public class Donator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "donatorId")
    private int IdDonator;

    @Column(name = "CNP")
    private String Cnp;

    @Column(name = "Nume")
    private String Nume;

    @Column(name = "Prenume")
    private String Prenume;

    @Column(name = "Data_Nasterii")
    private String DataNasterii;

    @Column(name = "nrTelefon")
    private String NrTelefon;

    @Column(name = "email")
    private String Email;

    @OneToMany(mappedBy = "donatori", fetch = FetchType.EAGER)
    @JsonBackReference
    private int IdSange;

    @Column(name = "Judet")
    private String Judet;

    @Column(name = "Oras")
    private String Oras;

    @Column(name = "Strada")
    private String Strada;

    @Column(name = "Apartament")
    private String Apartament;

    @Column(name = "Bloc")
    private String Bloc;

    @Column(name = "Scara")
    private String Scara;

    @Column(name = "Numar")
    private String Numar;

    @Column(name = "CodPostal")
    private int CodPostal;

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

    public int getCodPostal() {
        return CodPostal;
    }

    public void setCodPostal(int codPostal) {
        CodPostal = codPostal;
    }

    public Donator(int idDonator, String cnp, String nume, String prenume, String dataNasterii, String nrTelefon, String email, int idSange, String judet, String oras, String strada, String apartament, String bloc, String scara, String numar, int codPostal) {
        IdDonator = idDonator;
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

}
