package donator.view;

import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        try{
            listViewDonatori.setItems(FXCollections.observableArrayList(service.getDonatori()));
        }catch(DonatorException ex){

        }catch (RemoteException ex){

        }



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
                        String str = "";
                        String[] aux = textFieldNumePrenume.getText().split(" ");
                        for (int i = 0; i < aux.length; i++)
                            str = str + " " + aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1);
                        str = str.substring(1);
                        aux = str.split(" ");
                        try {
                            listViewDonatori.setItems(FXCollections.observableArrayList(service.filtrareDonatorDupaNume(aux[0], aux[1])));
                        } catch (DonatorException | RemoteException ex) { }
                    }
                } else {
                    try {
                        listViewDonatori.setItems(FXCollections.observableArrayList(service.getDonatori()));
                    } catch (DonatorException | RemoteException ex) { }
                }
            }
        });
    }

    @FXML
    public void handleSelection(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            System.out.println("Afisam boli si istoric pentru:" + listViewDonatori.getSelectionModel().getSelectedItem());
        } else if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            System.out.println("Afisam informatii pentru:" + listViewDonatori.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void handleGenerarePDF(){
        //to do
    }


}
