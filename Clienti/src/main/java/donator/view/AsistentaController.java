package donator.view;

import donator.service.IClient;
import donator.service.IServer;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class AsistentaController extends UnicastRemoteObject implements IClient{

    @FXML
    ListView<String> listViewDonatori, listViewBoli, listViewIstoric;

    @FXML
    TextField textFieldNumePrenume, textFieldDoneazaPentru;

    @FXML
    CheckBox checkBoxDatePicker, checkBoxDoneazaPentru;

    @FXML
    DatePicker datePicker;

    private IServer service;
    private Stage dialogStage;
    private AsistentaInformatiiController asistentaInformatiiController;

    public AsistentaController() throws RemoteException{ ;
    }

    public void setService(IServer service) {
        this.service = service;

        ArrayList<String> lista = new ArrayList<>();
        lista.add(0, "mere");
        lista.add(1, "pere");
        System.out.println("am ajuns in set service");
        //listViewDonatori.setItems(FXCollections.observableArrayList(service.ge));

        listViewDonatori.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    System.out.println("Afisam boli si istoric pentru:" + listViewDonatori.getSelectionModel().getSelectedItem());
                } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    System.out.println("Afisam informatii pentru:" + listViewDonatori.getSelectionModel().getSelectedItem());
                }
            }
        });

    }



}
