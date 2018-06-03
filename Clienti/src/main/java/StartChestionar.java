import donator.service.IServer;
import donator.view.ChestionarView2Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartChestionar extends Application {
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

                loader.setLocation(getClass().getResource("chestionarView2.fxml"));
                anchorPane = (AnchorPane)loader.load();
                ChestionarView2Controller chestionarView2Controller = loader.getController();
                chestionarView2Controller.setService(server);
                Scene scene = new Scene(anchorPane);
                Stage stage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Chestionar Now");

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
