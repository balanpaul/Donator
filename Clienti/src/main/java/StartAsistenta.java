

import donator.service.IServer;
import donator.view.LoginView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartAsistenta extends Application {
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

                loader.setLocation(getClass().getResource("loginView.fxml"));
                anchorPane = (AnchorPane)loader.load();
                LoginView loginView = loader.getController();
                loginView.setService(server);
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Donator Now");

                primaryStage.show();
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