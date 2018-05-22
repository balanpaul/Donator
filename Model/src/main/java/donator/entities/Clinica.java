package donator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Clienti")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int IdClinica;

    @OneToMany(mappedBy = "clinici", fetch = FetchType.EAGER)
    @JsonBackReference
    private int IdPersonal;

    @Column(name = "Contact")
    private String Contact;

    @Column(name = "Adresa")
    private String Adresa;

    @OneToMany(mappedBy = "centre", fetch = FetchType.EAGER)
    @JsonBackReference
    private int IdSange;

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
