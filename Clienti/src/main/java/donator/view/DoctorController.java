package donator.view;

import donator.entities.Centru;
import donator.service.DonatorException;
import donator.service.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class DoctorController {
    @FXML
    TableView centreTable;

    @FXML
    TableColumn numeColumn, adresaColumn, telefonColumn, emailColumn;

    @FXML
    TextField grupaSangeField, cantitateField;

    @FXML
    RadioButton trombociteRadio, plasmaRadio, globuleRosiiRadio;

    @FXML
    ComboBox prioritateComboBox;
    private Stage dialogStage;

    private IServer service;
    public DoctorController(){

    }

    private ObservableList<Centru> model;

    public void setService(IServer service,Stage stage){
        this.service=service;
        /*try{
            model = FXCollections.observableArrayList(service.listaCentre());
            centreTable.setItems(model);
        }catch(DonatorException | RemoteException ex){}*/
        loadTable();
        this.dialogStage=stage;
    }

    @FXML
    public void initialize(){}

    @FXML
    public void onCickCautaCentru(){
        String grupaSange = grupaSangeField.getText();
        int unitatiSanguine;
        boolean trombocite = false;
        boolean plasma = false;
        boolean globuleRosii = false;

        unitatiSanguine = Integer.parseInt(cantitateField.getText());

        if(trombociteRadio.isSelected()){
            trombocite = true;
        }
        else if(plasmaRadio.isSelected()){
            plasma = true;
        }
        else if(globuleRosiiRadio.isSelected()){
            globuleRosii = true;
        }
        ArrayList<Centru> listaSpecificatii = null;

        if(prioritateComboBox.getValue().toString().compareTo("URGENTA") == 0){
            try {
                listaSpecificatii = (ArrayList<Centru>) service.cautaCentreUrgenta(grupaSange, unitatiSanguine, trombocite, plasma, globuleRosii);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (DonatorException e) {
                e.printStackTrace();
            }
        }
        else if(prioritateComboBox.getValue().toString().compareTo("NORMALA") == 0){
            try {
                listaSpecificatii = (ArrayList<Centru>) service.cautaCentreNormala(grupaSange, unitatiSanguine, trombocite, plasma, globuleRosii);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (DonatorException e) {
                e.printStackTrace();
            }
        }
        loadTableSpecific(listaSpecificatii);
    }

    private void loadTable() {
        try {
            this.model = FXCollections.observableArrayList(service.listaCentre());
            centreTable.setItems(model);
            centreTable.getSelectionModel().selectFirst();
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void loadTableSpecific(ArrayList<Centru> list) {

            this.model = FXCollections.observableArrayList(list);
            centreTable.setItems(model);
            centreTable.getSelectionModel().selectFirst();

    }
    @FXML
    public void handleGoBack(){
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane anchorPane;

            loader.setLocation(getClass().getResource("/loginView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");

            LoginView loginView = loader.getController();
            loginView.setService(service);

            stage.show();

            dialogStage.hide();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
