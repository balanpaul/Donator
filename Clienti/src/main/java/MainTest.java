import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.net.www.ApplicationLaunchException;

import static javafx.application.Application.launch;

public class MainTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {


            /*FXMLLoader loader = new FXMLLoader(
                    getClass().getClassLoader().getResource("Login.fxml"));
            Parent root=loader.load();
            FXMLLoader loader =new FXMLLoader(getClass().getClassLoader().getResource("loginpage.fxml"));

            //loader.setLocation(ClassLoader.getSystemResource("loginpage.fxml"));
            //BorderPane root = new BorderPane();
            AnchorPane root = (AnchorPane) FXMLLoader.load(StartClient.class.getResource("/packagename/LoginGUI.fxml"));

            LoginViewController loginViewController = loader.getController();
            loginViewController.setService(server);
            */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("donatorVechiView.fxml"));
            BorderPane root;
            root = loader.load();


            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Autentificare");
            primaryStage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
