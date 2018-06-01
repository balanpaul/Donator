package donator.view;

import donator.service.IServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DonatorMainViewController {
    private IServer service;
    private Stage dialogStage;
    private DonatorNouViewController donatorNouViewController;
    public void setService(IServer service) {
        this.service = service;
        this.dialogStage=new Stage();
    }

    @FXML
    public void onClickDonatorNou(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/donatorNouView.fxml"));
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
            stage.show();
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
