package donator.view;

import donator.service.IClient;
import donator.service.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DonatorMainViewController extends UnicastRemoteObject implements IClient{
    private IServer service;
    private Stage dialogStage;
    private DonatorNouViewController donatorNouViewController;

    public DonatorMainViewController() throws RemoteException{
    }

    public void setService(IServer service) {
        this.service = service;

    }

    //deschiderea unei noi ferestre, loader = fisierul fxml, title = titlul ferestrei
    private void openMainPage(ActionEvent e, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/donatorNouView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.sizeToScene();

        donatorNouViewController = fxmlLoader.getController();
        donatorNouViewController.setService(service);

        stage.show();
        ((Node) (e.getSource())).getScene().getWindow().hide();
    }
    @FXML
    public void onClickDonatorNou(ActionEvent actionEvent){
        try {
           /* FXMLLoader loader = new FXMLLoader(getClass().getResource("/donatorNouView.fxml"));
            AnchorPane anchorPane;
            //DonatorNouViewController donatorNouViewController = new DonatorNouViewController();
           // loader.setLocation(getClass().getResource("/donatorNouView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Donator Now");
            donatorNouViewController=loader.getController();
            donatorNouViewController.setService(service, stage);
            stage.show();*/
           openMainPage(actionEvent,"Donator nou");
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickDonatorVechi(){

        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane anchorPane;
            DonatorVechiController donatorVechiController = new DonatorVechiController();
            loader.setLocation(getClass().getResource("/donatorVechiView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Donator Vechi");
            donatorVechiController.setService(stage);
            stage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
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
