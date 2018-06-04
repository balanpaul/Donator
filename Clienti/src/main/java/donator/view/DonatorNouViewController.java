package donator.view;

import donator.entities.Donator;
import donator.entities.Programari;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Alex on 22 mai 2018.
 */
public class DonatorNouViewController extends UnicastRemoteObject implements IClient{

    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private TextField textFieldStrada;
    @FXML
    private TextField textFieldNumar;
    @FXML
    private TextField textFieldBloc;
    @FXML
    private TextField textFieldScara;
    @FXML
    private TextField textFieldApartament;
    @FXML
    private TextField textFieldOras;
    @FXML
    private TextField textFieldJudet;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField textFieldIntervalOrar1;
    @FXML
    private TextField textFieldIntervalOrar2;

    Stage dialogStage;

    @FXML
    private void initialize() {
    }

    private IServer service;

    public void setService(IServer service) {
        this.service = service;

    }

    public DonatorNouViewController() throws RemoteException{
    }

    @FXML
    public void handleSend(){
        String nume = textFieldNume.getText();
        String prenume = textFieldPrenume.getText();
        String strada = textFieldStrada.getText();
        String numar = textFieldNumar.getText();
        String bloc = textFieldBloc.getText();
        String scara = textFieldScara.getText();
        String apartament = textFieldApartament.getText();
        String oras = textFieldOras.getText();
        String judet = textFieldJudet.getText();
        String telefon = textFieldTelefon.getText();
        String email=textFieldEmail.getText();
        LocalDate localDate = datePicker.getValue();

        String intervalOrar1 = textFieldIntervalOrar1.getText();
        String intervalOrar2 = textFieldIntervalOrar2.getText();

        try{
            Donator donator = new Donator(nume, prenume, strada, numar, bloc, scara, apartament, oras, judet, telefon, email);

            java.sql.Date p= java.sql.Date.valueOf(localDate);
            Programari programari=new Programari(Integer.valueOf(intervalOrar1),p);
            service.adaugaDonator(donator,programari);

            System.out.println("Donator adaugat!!");
        }catch (DonatorException e){
            showErrorMessage(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }



    }

    //Alert for error
    private void showErrorMessage(String msg){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Whoops");
        message.setContentText(msg);
        message.showAndWait();
    }
    @FXML
    public void handleCancel(){
        dialogStage.close();
    }

}
