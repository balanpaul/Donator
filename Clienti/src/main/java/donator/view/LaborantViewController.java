package donator.view;

import donator.entities.DateSange;
import donator.service.DonatorException;
import donator.service.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class LaborantViewController {
    @FXML
    TableView sangeVerificat;

    @FXML
    ListView observatii;

    @FXML
    TextField nume, prenume, grupaSange, adaugaObservatii;

    Stage stage;

    IServer service;
    public LaborantViewController(){
    }

    public void setService(IServer service, Stage stage){
        this.service=service;
        this.stage = stage;
    }

    @FXML
    public void onClickSangeCeTrebuieVerificat(ActionEvent actionEvent) {
        try {
            ArrayList<DateSange> dateSanges = (ArrayList)service.getSange();
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}
