package donator.view;

import donator.entities.DateSange;
import donator.entities.Donator;
import donator.service.IClient;
import donator.service.IServer;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class AsistentaInformatiiController extends UnicastRemoteObject implements IClient {
    @FXML
    private TextField nume, prenume, strada, numar, bloc, scara, apartament, oras, judet, telefon, email, cnp, grupaSange;
    @FXML
    private CheckBox respectaNormEU;

    private Donator donator; //donator primit ca parametru de la dclick din view
    private IServer service;

    public AsistentaInformatiiController() throws RemoteException {
    }


    public void setService(IServer service){
        this.service = service;
    }

    @FXML
    public void initialize(Donator donator){

        nume.setText(donator.getNume());
        prenume.setText(donator.getPrenume());
        //strada.setText(donator.getStrada());
        //numar.setText(donator.getNumar());
        //bloc.setText(donator.getBloc());
        //scara.setText(donator.getScara());
        //apartament.setText(donator.getApartament());
        //oras.setText(donator.getOras());
        //judet.setText(donator.getJudet());
        //telefon.setText(donator.getNrTelefon());
       // email.setText(donator.getEmail());
        //cnp.setText(donator.getCnp());
        //int idSange = donator.getIdSange();
        //urmeaza sa fie implementat in ServerImpl
      //  DateSange dateSange = null;
        //DateSange dateSange = service.getGrupaSanguina(int idSange);
       // grupaSange.setText(dateSange.getGrupaSanguina());
      //  if(dateSange.getSanatos() == Boolean.TRUE){
           // respectaNormEU.setSelected();

    }

    @FXML
    public void onClickTrimite(ActionEvent actionEvent){
        /*
        String numeM, prenumeM,stradaM, orasM, judetM, nrTelefonM;
        String emailM, cnpM, numarM, blocM, scaraM, apartamentM;
        int idSangeM;*/
        donator.setNume(nume.getText());
        donator.setPrenume(prenume.getText());
        donator.setStrada(strada.getText());
        donator.setNumar(numar.getText());
        donator.setBloc(bloc.getText());
        donator.setScara(bloc.getText());
        donator.setApartament(bloc.getText());
        donator.setOras(oras.getText());
        donator.setJudet(judet.getText());
        donator.setNrTelefon(telefon.getText());
        donator.setEmail(email.getText());
        donator.setCnp(cnp.getText());
        //in service va fi imp aceasta functie
        //service.updateDonator(donator);
    }
}
