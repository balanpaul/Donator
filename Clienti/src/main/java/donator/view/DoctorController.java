package donator.view;

import donator.entities.Centru;
import donator.service.IServer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private IServer service;
    public DoctorController(){

    }

    public void setService(IServer service){
        this.service=service;
    }

    @FXML
    public void initialize(){
        numeColumn.setCellValueFactory(new PropertyValueFactory<Centru, String>("nume"));
        adresaColumn.setCellValueFactory(new PropertyValueFactory<Centru, String>("adresa"));
        telefonColumn.setCellValueFactory(new PropertyValueFactory<Centru, String>("telefon"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Centru, String>("email"));
    }
}
