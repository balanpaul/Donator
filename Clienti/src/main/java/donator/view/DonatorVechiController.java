package donator.view;


import donator.entities.*;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
    private Stage dialogStage;


    public void setService(IServer service, Stage dialogStage) {
        this.service=service;
        this.dialogStage = dialogStage;
    }

    public DonatorVechiController() throws RemoteException{

    }

    @FXML
    private void initialize(){
        sfOra.setDisable(true);

        incOra.selectionProperty().addListener(new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observable, IndexRange oldValue, IndexRange newValue) {
                if (newValue.getStart() != 0 && newValue.getEnd() != 0) {
                    Integer number = Integer.parseInt(incOra.getText());
                    number += 1;
                    sfOra.setText(number.toString());
                }
                else{
                    sfOra.setText("");
                }
            }
        });

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
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

    @FXML
    private void handleGoBack(){
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane anchorPane;

            loader.setLocation(getClass().getResource("/donatorMainView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Donator main");

            DonatorMainViewController donatorMainViewController = loader.getController();
            donatorMainViewController.setService(service);

            stage.show();

            dialogStage.hide();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
