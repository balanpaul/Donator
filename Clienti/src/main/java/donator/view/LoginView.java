package donator.view;

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
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginView extends UnicastRemoteObject implements IClient{
        private IServer service;
        private Stage dialogStage;
        private DoctorController doctorController;
        private AsistentaController asistentaController;
        private LaborantController laborantController;
        private LoginView loginView;

        public LoginView() throws RemoteException{
        }

        public void setService(IServer service) {
                this.service = service;

        }
        @FXML
        private ComboBox comboBox;
        ObservableList<String> pozitie= FXCollections.observableArrayList("Medic", "Asistenta", "Laborant")
        @FXML
        private void intialize(){
                comboBox.setValue("Medic");
                comboBox.setItems(pozitie);

        }

        //deschiderea unei noi ferestre, loader = fisierul fxml, title = titlul ferestrei
        private void openMainPage(ActionEvent e, String title) throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/loginView.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle(title);
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.sizeToScene();

                loginView = fxmlLoader.getController();
                loginView.setService(service);

                stage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();
        }


        @FXML
        public void onClickComboBox(){
                if (comboBox.getValue()=="Medic") {
                        try {
                                FXMLLoader loader = new FXMLLoader();
                                AnchorPane anchorPane;
                                DoctorController doctorController = new DoctorController();
                                loader.setLocation(getClass().getResource("/medicView.fxml"));
                                anchorPane = (AnchorPane) loader.load();
                                Scene scene = new Scene(anchorPane);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle("Donator Vechi");
                                DoctorController.setService(stage);
                                stage.show();
                        } catch (Exception e) {
                                System.err.println("Initialization  exception:" + e);
                                e.printStackTrace();
                        }
                }else if (comboBox.getValue()=="Asistenta"){

                        try {
                                FXMLLoader loader = new FXMLLoader();
                                AnchorPane anchorPane;
                                AsistentaController asistentaController = new AsistentaController();
                                loader.setLocation(getClass().getResource("/asistentaView.fxml"));
                                anchorPane = (AnchorPane) loader.load();
                                Scene scene = new Scene(anchorPane);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle("Donator Vechi");
                                asistentaController.setService(stage);
                                stage.show();
                        } catch (Exception e) {
                                System.err.println("Initialization  exception:" + e);
                                e.printStackTrace();
                        }
                }else if (comboBox.getValue()=="Laborant") {
                        try {
                                FXMLLoader loader = new FXMLLoader();
                                AnchorPane anchorPane;
                                LaborantController LaborantController = new LaborantController();
                                loader.setLocation(getClass().getResource("/laborantView.fxml"));
                                anchorPane = (AnchorPane) loader.load();
                                Scene scene = new Scene(anchorPane);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.setTitle("Donator Vechi");
                                LaborantController.setService(stage);
                                stage.show();
                        } catch (Exception e) {
                                System.err.println("Initialization  exception:" + e);
                                e.printStackTrace();
                        }
                }
        }


        @FXML
        public void onClickIstoricDonatii(){
                try {
                        FXMLLoader loader = new FXMLLoader();
                        AnchorPane anchorPane;
                        IstoricDonatieViewController istoricDonatieViewController = new IstoricDonatieViewController();
                        loader.setLocation(getClass().getResource("/istoricDonatieView.fxml"));
                        anchorPane = (AnchorPane)loader.load();
                        Scene scene = new Scene(anchorPane);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Donator Istoric");
                        istoricDonatieViewController.setService(stage);
                        stage.show();
                } catch (Exception e){
                        System.err.println("Initialization  exception:"+e);
                        e.printStackTrace();
                }
        }
}

