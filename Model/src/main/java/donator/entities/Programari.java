package donator.entities;

import java.io.Serializable;
import java.sql.Date;

public class Programari implements Serializable {
    private int IdProgramari,ora;
    private Date DataD;
    private Donator donator;
    public Programari() {
    }

    public Programari(int idProgramari, int ora, Date dataD, Donator donator) {
        IdProgramari = idProgramari;
        this.ora = ora;
        DataD = dataD;
        this.donator = donator;
    }

    public Programari(int ora, Date dataD) {
        this.ora = ora;
        DataD = dataD;
    }

    public Programari(int idProgramari, int ora, Date dataD) {
        IdProgramari = idProgramari;
        this.ora = ora;
        DataD = dataD;
    }

    public int getIdProgramari() {
        return IdProgramari;
    }

    public void setIdProgramari(int idProgramari) {
        IdProgramari = idProgramari;
    }

    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public Date getDataD() {
        return DataD;
    }

    public void setDataD(Date dataD) {
        DataD = dataD;
    }
}
