
import donator.service.IServer;
import donator.view.DonatorMainViewController;
import donator.view.IstoricDonatieViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ClientStart extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
            IServer server;
            server = (IServer) factory.getBean("service");

            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane anchorPane;
                DonatorMainViewController donatorMainViewController = new DonatorMainViewController();
                loader.setLocation(getClass().getResource("donatorMainView.fxml"));
                anchorPane = (AnchorPane)loader.load();
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Donator Now");
                donatorMainViewController.setService(server);
                stage.show();
            } catch (Exception e){
                System.err.println("Initialization  exception:"+e);
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Initialization  exception:" + e);
            e.printStackTrace();
        }
    }
}
