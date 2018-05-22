package donator.entities;

public class SangeBoli {
    private int idSange;
    private int idBoala;

    public int getIdSange() {
        return idSange;
    }

    public void setIdSange(int idSange) {
        this.idSange = idSange;
    }

    public int getIdBoala() {
        return idBoala;
    }

    public void setIdBoala(int idBoala) {
        this.idBoala = idBoala;
    }

    public SangeBoli(int idSange, int idBoala) {

        this.idSange = idSange;
        this.idBoala = idBoala;
    }
}
