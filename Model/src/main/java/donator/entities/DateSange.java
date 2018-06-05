package donator.entities;


import java.sql.Date;

public class DateSange {

    private int IdSange;
    private String GrupaSanguina;
    private int Sanatos;
    private int globuleRosii;
    private int plasma;
    private int trombocite;
    private Date dataRecolta;
    private Donator donator;


    public DateSange(int idSange, String grupaSanguina, int sanatos, String rh, int globuleRosii, int plasma, int trombocite, Date dataRecolta, Donator donator) {
        IdSange = idSange;
        GrupaSanguina = grupaSanguina;
        Sanatos = sanatos;

        this.globuleRosii = globuleRosii;
        this.plasma = plasma;
        this.trombocite = trombocite;
        this.dataRecolta = dataRecolta;
        this.donator = donator;
    }

    public DateSange(int idSange, String grupaSanguina, int sanatos, String rh, int globuleRosii, int plasma, int trombocite, Date dataRecolta) {
        IdSange = idSange;
        GrupaSanguina = grupaSanguina;
        Sanatos = sanatos;
        this.globuleRosii = globuleRosii;
        this.plasma = plasma;
        this.trombocite = trombocite;
        this.dataRecolta = dataRecolta;
    }

    public DateSange(String grupaSanguina, Boolean sanatos, String rh, int globuleRosii, int plasma, int trombocite) {
        GrupaSanguina = grupaSanguina;
        Sanatos = sanatos;
        this.globuleRosii = globuleRosii;
        this.plasma = plasma;
        this.trombocite = trombocite;
    }

    public DateSange() {
    }

    public int getGlobuleRosii() {
        return globuleRosii;
    }

    public void setGlobuleRosii(int globuleRosii) {
        this.globuleRosii = globuleRosii;
    }

    public int getPlasma() {
        return plasma;
    }

    public void setPlasma(int plasma) {
        this.plasma = plasma;
    }

    public int getTrombocite() {
        return trombocite;
    }

    public void setTrombocite(int trombocite) {
        this.trombocite = trombocite;
    }

    public Date getDataRecolta() {
        return dataRecolta;
    }

    public void setDataRecolta(Date dataRecolta) {
        this.dataRecolta = dataRecolta;
    }

    public int getIdSange() {
        return IdSange;
    }

    public void setIdSange(int idSange) {
        IdSange = idSange;
    }

    public String getGrupaSanguina() {
        return GrupaSanguina;
    }

    public void setGrupaSanguina(String grupaSanguina) {
        GrupaSanguina = grupaSanguina;
    }

    public int getSanatos() {
        return Sanatos;
    }

    public void setSanatos(int sanatos) {
        Sanatos = sanatos;
    }



    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }

}
