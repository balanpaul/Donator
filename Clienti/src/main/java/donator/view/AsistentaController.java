package donator.view;

import donator.entities.DateSange;
import donator.entities.Donator;
import donator.entities.Observatii;
import donator.entities.Programari;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AsistentaController extends UnicastRemoteObject implements IClient{

    @FXML
    private TableView<Donator> tableDonator;

    @FXML
    private TableColumn<String, Donator> numeColumn;

    @FXML
    private TableColumn<String, Donator> prenumeColumn;

    @FXML
    private TableColumn<String, Donator> cnpColumn;

    @FXML
    private TableColumn<String, Donator> telefonColumn;

    @FXML
    private ListView<Observatii> listViewBoli;

    @FXML
    private ListView<String> listViewIstoric;

    @FXML
    private TextField textFieldNumePrenume, textFieldDoneazaPentru;

    @FXML
    private CheckBox checkBoxDatePicker, checkBoxDoneazaPentru;

    @FXML
    private DatePicker datePicker;

    private IServer service;
    private Stage dialogStage;
    private AsistentaInformatiiController asistentaInformatiiController;
    private AsistentaController asistentaController;
    private ObservableList<Donator> model;

    public AsistentaController() throws RemoteException{
    }

    public void setService(IServer service, AsistentaController asistentaController, Stage dialogStage) {
        this.service = service;
        this.asistentaController = asistentaController;
        this.dialogStage = dialogStage;

        refresh();
    }

    public void refresh(){
        try{
            model = FXCollections.observableArrayList(service.getAll());
            tableDonator.setItems(model);
        } catch (DonatorException e){
            System.out.println(e);
        } catch (RemoteException e){
            e.printStackTrace();
        }

        textFieldDoneazaPentru.setDisable(true);
        checkBoxDoneazaPentru.selectedProperty().setValue(false);
    }

    @FXML
    public void initialize() {

        // Initialize the student table with the three columns.
        numeColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Nume"));
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Prenume"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Cnp"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("NrTelefon"));
        tableDonator.setEditable(false);

        datePicker.setValue(NOW_LOCAL_DATE());

        //listener pentru filtrare cu checkbox
        checkBoxDatePicker.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
                if (new_val) {
                    if (textFieldNumePrenume.getText() != null || !"".equals(textFieldNumePrenume.getText())) {
                        //avem text in nume si prenume
                        String str = "";
                        String[] aux = textFieldNumePrenume.getText().split(" ");
                        if (aux.length == 2) {
                            for (int i = 0; i < aux.length; i++)
                                str = str + " " + aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1);
                            str = str.substring(1);
                            aux = str.split(" ");
                            try {
                                //sortare cu text si date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(aux[0], aux[1], (Date.valueOf(datePicker.getValue())))));
                            } catch (DonatorException e) {
                                System.out.println(e);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                //sortare numai cu date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(" ", " ", (Date.valueOf(datePicker.getValue())))));
                            } catch (DonatorException e) {
                                System.out.println(e);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            textFieldNumePrenume.clear();
                            datePicker.setValue(NOW_LOCAL_DATE());

                            model = FXCollections.observableArrayList(service.getAll());
                            tableDonator.setItems(model);
                        } catch (DonatorException e) {
                            System.out.println(e);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                        refresh();
                        datePicker.setValue(NOW_LOCAL_DATE());
                    }
                }

        });



        //listener pentru schimbarea datei
        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            if(checkBoxDatePicker.selectedProperty().getValue()) {
                if (textFieldNumePrenume.getText() != null || !"".equals(textFieldNumePrenume.getText())) {
                    //avem text in nume si prenume
                    String str = "";
                    String[] aux = textFieldNumePrenume.getText().split(" ");
                    if (aux.length == 2) {
                        for (int i = 0; i < aux.length; i++)
                            str = str + " " + aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1);
                        str = str.substring(1);
                        aux = str.split(" ");
                        try {
                            //sortare cu text si date picker
                            tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(aux[0], aux[1], (Date.valueOf(newValue)))));
                        } catch (DonatorException e){
                            System.out.println(e);
                        } catch (RemoteException e){
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            //sortare numai cu date picker
                            tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(" ", " ", (Date.valueOf(newValue)))));
                        } catch (DonatorException e){
                            System.out.println(e);
                        } catch (RemoteException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        //listener pentru checkbox doneaza pentru
        checkBoxDoneazaPentru.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov,Boolean old_val, Boolean new_val) {
                if (new_val)
                    textFieldDoneazaPentru.setDisable(false);
                else
                    textFieldDoneazaPentru.setDisable(true);
            }
        });

        textFieldNumePrenume.selectionProperty().addListener(new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observable, IndexRange oldValue, IndexRange newValue) {
                if (newValue.getStart() != 0 && newValue.getEnd() != 0) {
                    Boolean bool = checkBoxDatePicker.selectedProperty().getValue();
                    //fara date picker
                    String str = "";
                    String[] aux = textFieldNumePrenume.getText().split(" ");
                    if(aux.length == 2) {
                        for (int i = 0; i < aux.length; i++)
                            str = str + " " + aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1);
                        str = str.substring(1);
                        aux = str.split(" ");
                        try {
                            if(bool) {
                                //cu date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(aux[0], aux[1], (Date.valueOf(datePicker.getValue())))));
                            } else {
                                //fara date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNume(aux[0], aux[1])));
                            }
                        } catch (DonatorException e){
                            System.out.println(e);
                        } catch (RemoteException e){
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            if(bool) {
                                //cu date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNumeSiData(" ", " ", (Date.valueOf(datePicker.getValue())))));
                            } else {
                                //fara date picker
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNume(" "," ")));
                            }
                        } catch (DonatorException e){
                            System.out.println(e);
                        } catch (RemoteException e){
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        model = FXCollections.observableArrayList(service.getAll());
                        tableDonator.setItems(model);
                    } catch (DonatorException e){
                        System.out.println(e);
                    } catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void handleSelection(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            Donator donator = tableDonator.getSelectionModel().getSelectedItem();
            listaBoli(donator);
            listaIstoric(donator);
        } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            Donator donator = tableDonator.getSelectionModel().getSelectedItem();
            showDonatorInformation(donator, false);
        }
    }

    @FXML
    public void handleDonatorNou(MouseEvent mouseEvent){
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            Donator donator = null;
            showDonatorInformation(donator, true);
        }
    }

    public void listaBoli(Donator donator){
        try{
            DateSange dateSange = service.getDateSangeDonator(donator.getIdDonator());
            if(dateSange != null)
                listViewBoli.setItems(FXCollections.observableArrayList(service.listaObservatii(dateSange.getIdSange())));
        } catch (DonatorException e){
            System.out.println(e);
        } catch (RemoteException e){
            e.printStackTrace();
        }
    }

    public void listaIstoric(Donator donator){
        List<DateSange> dateSanges=service.getByDonator(donator.getIdDonator());
        List<String> strings = dateSanges.stream()
                .map(object -> Objects.toString(object))
                .collect(Collectors.toList());
        listViewIstoric.setItems(FXCollections.observableArrayList(strings));

    }

    public void showDonatorInformation(Donator donator, Boolean bool){
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane anchorPane;
            loader.setLocation(getClass().getResource("/asistentaInformatiiView.fxml"));
            anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Donator informatii");
            //doctorController.setService(stage);
            this.asistentaInformatiiController =loader.getController();
            this.asistentaInformatiiController.setService(service, asistentaController, stage);

            if(checkBoxDoneazaPentru.selectedProperty().getValue() && (textFieldDoneazaPentru.getText() != null || !"".equals(textFieldDoneazaPentru.getText())))
                this.asistentaInformatiiController.initialize(donator, bool, datePicker.getValue(), textFieldDoneazaPentru.getText());
            else
                this.asistentaInformatiiController.initialize(donator, bool, datePicker.getValue(), "notSelected");
            stage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }

    // Date Now  ### "To Date Picker"
    public static final LocalDate NOW_LOCAL_DATE (){
        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date , formatter);
        return localDate;
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


    @FXML
    public void handleGenerarePDF(){
        Donator donator = tableDonator.getSelectionModel().getSelectedItem();
        if(donator != null){
            try{
                service.exportPDF(donator.getEmail());
            } catch (DonatorException e){
                System.out.println(e);
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }

    }


}
