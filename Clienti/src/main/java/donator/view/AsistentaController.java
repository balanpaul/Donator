package donator.view;

import donator.entities.Donator;
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
import java.util.ArrayList;

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
    private ListView<String> listViewBoli, listViewIstoric;

    @FXML
    private TextField textFieldNumePrenume, textFieldDoneazaPentru;

    @FXML
    private CheckBox checkBoxDatePicker, checkBoxDoneazaPentru;

    @FXML
    private DatePicker datePicker;

    private IServer service;
    private Stage dialogStage;
    private AsistentaInformatiiController asistentaInformatiiController;
    private ObservableList<Donator> model;

    public AsistentaController() throws RemoteException{
    }

    public void setService(IServer service) {
        this.service = service;

        ArrayList<String> lista = new ArrayList<>();
        lista.add(0, "mere");
        lista.add(1, "pere");
        System.out.println("am ajuns in set service");
        try{
            model = FXCollections.observableArrayList(service.getAll());
            tableDonator.setItems(model);
        }catch(DonatorException | RemoteException ex){}


    }

    @FXML
    public void initialize() {

        // Initialize the student table with the three columns.
        numeColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Nume"));
        prenumeColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Prenume"));
        cnpColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("Cnp"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<String, Donator>("NrTelefon"));
        tableDonator.setEditable(false);

    }

    @FXML
    public void handleFilterAfterName(){
        textFieldNumePrenume.selectionProperty().addListener(new ChangeListener<IndexRange>() {
            @Override
            public void changed(ObservableValue<? extends IndexRange> observable, IndexRange oldValue, IndexRange newValue) {
                if (newValue.getStart() != 0 && newValue.getEnd() != 0) {
                    Boolean bool = checkBoxDatePicker.selectedProperty().getValue();
                    if (bool) {
                        //trebe folosit si data picker

                    } else {
                        //fara date picker
                        String str = "";
                        String[] aux = textFieldNumePrenume.getText().split(" ");
                        if(aux.length == 2) {
                            for (int i = 0; i < aux.length; i++)
                                str = str + " " + aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1);
                            str = str.substring(1);
                            aux = str.split(" ");
                            try {
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNume(aux[0], aux[1])));
                            } catch (DonatorException | RemoteException ex) {
                            }
                        } else {
                            try {
                                tableDonator.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNume(" "," ")));
                            } catch (DonatorException | RemoteException ex) { }
                        }
                    }
                } else {
                    try {
                        model = FXCollections.observableArrayList(service.getAll());
                        tableDonator.setItems(model);
                    } catch (DonatorException | RemoteException ex) { }
                }
            }
        });
    }

    @FXML
    public void handleSelection(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            Donator donator = tableDonator.getSelectionModel().getSelectedItem();
            listaBoli(donator);
        } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            Donator donator = tableDonator.getSelectionModel().getSelectedItem();
            showDonatorInformation(donator);
        }
    }

    public void listaBoli(Donator donator){

    }

    public void showDonatorInformation(Donator donator){
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
            this.asistentaInformatiiController.setService(service);
            this.asistentaInformatiiController.initialize(donator);
            stage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }




    @FXML
    public void handleGenerarePDF(){
        //to do
    }


}
