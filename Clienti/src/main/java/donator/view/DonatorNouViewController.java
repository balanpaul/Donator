package donator.view;

import donator.entities.Donator;
import donator.entities.Programari;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import donator.validatori.ValidationException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
        textFieldIntervalOrar2.setDisable(true);

        textFieldIntervalOrar1.selectionProperty().addListener(new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observable, IndexRange oldValue, IndexRange newValue) {
                if (newValue.getStart() != 0 && newValue.getEnd() != 0) {
                    Integer number = Integer.parseInt(textFieldIntervalOrar1.getText());
                    number += 1;
                    textFieldIntervalOrar2.setText(number.toString());
                }
                else{
                    textFieldIntervalOrar2.setText("");
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

    private IServer service;

    public void setService(IServer service, Stage dialogStage) {
        this.service = service;
        this.dialogStage = dialogStage;
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

        try{
            Donator donator = new Donator(nume, prenume, strada, numar, bloc, scara, apartament, oras, judet, telefon, email);

            java.sql.Date p= java.sql.Date.valueOf(localDate);
            Programari programari=new Programari(Integer.valueOf(intervalOrar1),p);
            service.adaugaDonator(donator,programari);

            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setTitle("Trimitere");
            message.setContentText("ai fost programat");
            message.showAndWait();
        }catch (DonatorException e){
            showErrorMessage(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            showErrorMessage(e.getMessage());
        }


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
