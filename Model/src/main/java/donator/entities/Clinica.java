package donator.entities;


import java.io.Serializable;

public class Clinica implements Serializable {


    private int IdClinica;


    private int IdPersonal;


    private String Contact;


    private String Adresa;


    private int IdSange;

    public Clinica() {
    }

    public Clinica(int IdClinica, int IdPersonal, String Contact, String Adresa, int IdSange){
        this.IdClinica=IdClinica;
        this.IdPersonal=IdPersonal;
        this.Contact=Contact;
        this.Adresa=Adresa;
        this.IdSange=IdSange;
    }


    public int getIdClinica() {
        return IdClinica;
    }

    public void setIdClinica(int idClinica) {
        IdClinica = idClinica;
    }

    public int getIdPersonal() {
        return IdPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        IdPersonal = idPersonal;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAdresa() {
        return Adresa;
    }

    public void setAdresa(String adresa) {
        Adresa = adresa;
    }

    public int getIdSange() {
        return IdSange;
    }

    public void setIdSange(int idSange) {
        IdSange = idSange;
    }
}
