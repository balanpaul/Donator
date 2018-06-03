package donator.view;

import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginView extends UnicastRemoteObject implements IClient {
    private IServer service;
    private Stage dialogStage;
    private DoctorController doctorController;
    private AsistentaController asistentaController;
    private LaborantController laborantController;
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
        if (comboBox.getValue().toString().equals( "Medic")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/medicView.fxml"));
                DoctorController doctorController =loader.getController();
                anchorPane = (AnchorPane) loader.load();
                service.cautarePersonal(parola.getText().toString());
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Donator Vechi");
                //doctorController.setService(stage);
                stage.show();
            } catch (RemoteException e) {
                System.err.println("Initialization  exception:" + e);
                e.printStackTrace();
            } catch (DonatorException e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Eroare");
                message.setContentText(e.getMessage());
                message.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (comboBox.getValue().toString().equals( "Asistent")) {

            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/asistenta.fxml"));
                AsistentaController asistentaController =loader.getController();
                anchorPane = (AnchorPane) loader.load();
                service.cautarePersonal(parola.getText().toString());
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Donator Vechi");
                //doctorController.setService(stage);
                stage.show();
            } catch (RemoteException e) {
                System.err.println("Initialization  exception:" + e);
                e.printStackTrace();
            } catch (DonatorException e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Eroare");
                message.setContentText(e.getMessage());
                message.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (comboBox.getValue().toString().equals( "Laborant")) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                loader.setLocation(getClass().getResource("/laborant.fxml"));
                 LaborantController laborantController =loader.getController();
                anchorPane = (AnchorPane) loader.load();
                service.cautarePersonal(parola.getText().toString());
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Donator Vechi");
                //doctorController.setService(stage);
                stage.show();
            } catch (RemoteException e) {
                System.err.println("Initialization  exception:" + e);
                e.printStackTrace();
            } catch (DonatorException e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setTitle("Eroare");
                message.setContentText(e.getMessage());
                message.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

