package donator.view;


import donator.entities.*;
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
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class DonatorVechiController extends RemoteObject implements IClient {
    @FXML
    private TextField Nume,Email,Prenume,incOra,sfOra;

    @FXML
    private DatePicker datePicker;
    private IServer service;


    public void setService(IServer service) {
        this.service=service;
    }

    public DonatorVechiController() throws RemoteException{

    }


    @FXML
    public void onClickTrimitere(ActionEvent actionEvent ) {
        LocalDate data = datePicker.getValue();
        String nume=Nume.getText();
        String email=Email.getText();
        String prenume=Prenume.getText();
        int ora= Integer.parseInt(incOra.getText());
        try {
            Donator d=service.cautareDonator(email);
            Programari pr=service.cautaPlanifica(d.getIdDonator());
           Period intervalPeriod = Period.between(pr.getDataD().toLocalDate(), data);
            if(intervalPeriod.getMonths()<=6){
                throw  new DonatorException("perioada invalida");
            }
            Programari p=new Programari(ora, Date.valueOf(data));
            service.planificare(d,p);
        } catch (DonatorException e) {
            showErrorMessage(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void showErrorMessage(String msg){
        Alert message = new Alert(Alert.AlertType.ERROR);
        message.setTitle("Whoops");
        message.setContentText(msg);
        message.showAndWait();
    }
}
