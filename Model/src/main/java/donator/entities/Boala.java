package donator.entities;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="Boli")
public class Boala {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdBoala;
    private String Denumire;
    private String Descriere;

    public Boala(int IdBoala, String Denumire, String Descriere)
    {
        this.IdBoala=IdBoala;
        this.Denumire=Denumire;
        this.Descriere=Descriere;
    }


    public int getIdBoala() {
        return IdBoala;
    }

    public String getDenumire() {
        return Denumire;
    }

    public String getDescriere() {
        return Descriere;
    }

    public void setIdBoala(int idBoala) {
        IdBoala = idBoala;
    }

    public void setDenumire(String denumire) {
        Denumire = denumire;
    }

    public void setDescriere(String descriere) {
        Descriere = descriere;
    }
}
