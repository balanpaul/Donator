package donator.view;

import donator.entities.DateSange;
import donator.entities.Donator;
import donator.service.DonatorException;
import donator.service.IServer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class LaborantViewController {
    @FXML
    TableView sangeVerificat;

    @FXML
    TableColumn numeColumn, prenumeColumn, cnpColumn;

    @FXML
    ListView observatii;

    @FXML
    TextField nume, prenume, grupaSange, adaugaObservatii;

    private Stage dialogStage;
    private IServer service;
    private ObservableList<Donator> modelDonator;
    private ObservableList<DateSange> modelDateSange;

    public LaborantViewController(){
    }

    public void setService(IServer service){
        this.service=service;

        modelDonator = FXCollections.observableArrayList();
        try {
            for(DateSange sange:service.getNeverificati()){
                modelDonator.add(sange.getDonator());
            }
            sangeVerificat.setItems(modelDonator);
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //modelDateSange = FXCollections.observableArrayList(service.getNeverificati());


    }

    /*

    TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
 firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Person, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Person, String> p) {
         // p.getValue() returns the Person instance for a particular TableView row
         return p.getValue().firstNameProperty();
     }
  });
 }

     */

    @FXML
    private void initialize(){

        numeColumn.setCellValueFactory(new PropertyValueFactory<Donator, String>("Nume"));
        /*
        numeColumn = new TableColumn<DateSange,Donator>("Donator");
        numeColumn.setCellValueFactory(   new Callback<TableColumn.CellDataFeatures<DateSange, Donator>, ObservableValue<Donator>>(){

            public ObservableValue<Donator> call(TableColumn.CellDataFeatures<DateSange,Donator> p) {
                p.getValue().getDonator().getNume();
                return null;
            }
        });
        prenumeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DateSange,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<Donator> call(TableColumn.CellDataFeatures<DateSange,String> p) {
                p.getValue().getDonator().getPrenume();
            }
        });
        cnpColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DateSange,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<Donator> call(TableColumn.CellDataFeatures<DateSange,String> p) {
                p.getValue().getDonator().getCnp();
            }
        });*/
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<Donator,String>("Prenume"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<Donator,String>("Cnp"));

    }

    @FXML
    public void onClickSangeCeTrebuieVerificat(ActionEvent actionEvent) {
        try {
            ArrayList<DateSange> dateSanges = (ArrayList)service.getSange();
            sangeVerificat.setItems(FXCollections.observableArrayList(dateSanges));

        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }



    }


}
