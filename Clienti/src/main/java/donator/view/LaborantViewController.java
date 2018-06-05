package donator.view;

import donator.entities.DateSange;
import donator.entities.DatesangeCentre;
import donator.entities.Observatii;
import donator.entities.Personal;
import donator.service.DonatorException;
import donator.service.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LaborantViewController {
    @FXML
    TableView sangeVerificat;

    @FXML
    TableColumn<DateSange,String> numeColumn, prenumeColumn, cnpColumn;

    @FXML
    ListView observatii;

    @FXML
    TextField nume, prenume, grupaSange, adaugaObservatii;

    private Personal personal;
    private Stage dialogStage;
    private IServer service;
    private ObservableList<String> modelObservatie;
    private ObservableList<DateSange> modelDateSange;

    public LaborantViewController(){
    }

    public void setService(IServer service, Personal personal,Stage stage){
        this.service=service;
        this.personal=personal;
        this.dialogStage=stage;
        loadTable();



    }


    @FXML
    public void initialize(){

    }
    private void loadTable(){
        try {
            this.modelDateSange = FXCollections.observableArrayList(service.getSange());
            sangeVerificat.setItems(modelDateSange);
            sangeVerificat.getSelectionModel().selectFirst();
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void clickItem(MouseEvent event)
    {

        try {
            DateSange d= (DateSange) sangeVerificat.getSelectionModel().getSelectedItem();
          //  wait(100);
            nume.setText(d.getNume());
            prenume.setText(d.getPrenume());
            grupaSange.setText(d.getGrupaSanguina());
            List<Observatii> observaties=service.listaObservatii(d.getIdSange());
          List<String> strings = observaties.stream()
                    .map(object -> Objects.toString(object))
                    .collect(Collectors.toList());
           // List<String> strings=new ArrayList<>();
            this.modelObservatie = FXCollections.observableArrayList(strings);
            observatii.setItems(modelObservatie);
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }
    @FXML
    public void onEnter(ActionEvent ae){
        DateSange z= (DateSange) sangeVerificat.getSelectionModel().getSelectedItem();
        Observatii observatie=new Observatii(adaugaObservatii.getText().toString(),z);
        try {
            service.adaugaObservatie(observatie);
            ArrayList<Observatii> observaties= (ArrayList<Observatii>) service.listaObservatii(z.getIdSange());
            List<String> strings = observaties.stream()
                    .map(object -> Objects.toString(object))
                    .collect(Collectors.toList());
            this.modelObservatie = FXCollections.observableArrayList(strings);
            observatii.setItems(modelObservatie);
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onClickSangeCeTrebuieVerificat(ActionEvent actionEvent) {
        try {
            ArrayList<DateSange> dateSanges = (ArrayList)service.sangeNerverificat();
            sangeVerificat.setItems(FXCollections.observableArrayList(dateSanges));

        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }



    }
    @FXML
    public void trimitere(ActionEvent actionEvent){
        try{
            DateSange z= (DateSange) sangeVerificat.getSelectionModel().getSelectedItem();
            if(z.getDataRecolta()==null){
                String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(date , formatter);
                z.setDataRecolta(Date.valueOf(localDate));
                z.setGlobuleRosii(1);
                z.setPlasma(1);
                z.setTrombocite(1);
                z.setGrupaSanguina(grupaSange.getText().toString());
                if(service.listaObservatii(z.getIdSange()).size()==0) {
                    z.setSanatos(1);
                    DatesangeCentre datesangeCentre=new DatesangeCentre(z,this.personal.getCentru());
                    service.adaugareCentruRecoltare(datesangeCentre);
                }
                else
                    z.setSanatos(2);
                service.verificare(z.getDonator(),z);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void ultimele7(ActionEvent actionEvent){
        try{
            String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDate ;
            localDate=LocalDate.now().minusDays(7);
            ArrayList<DateSange> dateSanges = (ArrayList)service.recent(Date.valueOf(localDate));
            sangeVerificat.setItems(FXCollections.observableArrayList(dateSanges));
        }catch (RemoteException e) {
            e.printStackTrace();
        } catch (DonatorException e) {
            e.printStackTrace();
        }
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
