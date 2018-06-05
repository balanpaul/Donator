package donator.view;

import donator.entities.Personal;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginView extends UnicastRemoteObject implements IClient {
    private IServer service;
    private Stage dialogStage;
    private DoctorController doctorController;
    private AsistentaController asistentaController;
    //private LaborantViewController laborantViewController;
    private LoginView loginView;

    @FXML
    private PasswordField parola;

    public LoginView() throws RemoteException {
    }

    public void setService(IServer service) {
        this.service = service;

    }

    @FXML
    private ComboBox<String> comboBox;
    ObservableList<String> pozitie = FXCollections.observableArrayList("Medic", "Asistenta", "Laborant");


    @FXML
    public void handleLogin(ActionEvent actionEvent) {
        Personal p=null;
        try {
            p = service.cautarePersonal(parola.getText().toString());
        } catch (DonatorException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Eroare");
            message.setContentText(e.getMessage());
            message.showAndWait();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (comboBox.getValue().toString().equals("Medic") && p.getFunctie().equals("Medic")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/medicView.fxml"));

                anchorPane = (AnchorPane) loader.load();
                DoctorController doctorController = loader.getController();
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Medic");
                doctorController.setService(service, stage);
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (comboBox.getValue().toString().equals("Asistent")&&p.getFunctie().equals("Asistent")) {

            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/asistentaView.fxml"));
                anchorPane = (AnchorPane) loader.load();
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Asistenta");
                //doctorController.setService(stage);
                AsistentaController asistentaController =loader.getController();
                asistentaController.setService(service, asistentaController, stage);
                stage.show();

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (comboBox.getValue().toString().equals("Laborant")&&p.getFunctie().equals("Laborant")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/laborantView.fxml"));
                anchorPane = (AnchorPane) loader.load();

                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Laborant");
                LaborantViewController laborantViewController = loader.getController();
                laborantViewController.setService(service,p,stage);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

