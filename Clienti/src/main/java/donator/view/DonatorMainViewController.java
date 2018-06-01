package donator.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DonatorMainViewController {


    @FXML
    public void onClickDonatorNou(){
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane anchorPane = new AnchorPane();
            DonatorNouViewController donatorNouViewController = new DonatorNouViewController();
            loader.setLocation(getClass().getResource("donatorVechiView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage(scene);
            stage.setTitle("Donator Now");
            donatorNouViewController.setService(stage);
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
            AnchorPane anchorPane = new AnchorPane();
            DonatorVechiController donatorVechiController = new DonatorVechiController();
            loader.setLocation(getClass().getResource("donatorNouView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage(scene);
            stage.setTitle("Donator Now");
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
            AnchorPane anchorPane = new AnchorPane();
            IstoricDonatieViewController istoricDonatieViewController = new IstoricDonatieViewController();
            loader.setLocation(getClass().getResource("istoricDonatieView.fxml"));
            anchorPane = (AnchorPane)loader.load();
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage(scene);
            stage.setTitle("Donator Now");
            istoricDonatieViewController.setService(stage);
            stage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
