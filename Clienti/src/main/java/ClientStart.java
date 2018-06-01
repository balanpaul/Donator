import donator.service.IServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

        } catch (Exception e) {
            System.err.println("Initialization  exception:" + e);
            e.printStackTrace();
        }
    }
}
