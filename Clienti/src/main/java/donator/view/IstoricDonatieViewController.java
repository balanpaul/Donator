package donator.view;

import donator.service.DonatorException;
import donator.service.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IstoricDonatieViewController extends UnicastRemoteObject{

    @FXML
    private TextField email;

    @FXML private Text action;

    private IServer service;
    private Stage dialogStage;

    public IstoricDonatieViewController() throws RemoteException {
    }

    public void setService(IServer stage, Stage dialogStage) {
        this.service=stage;
        this.dialogStage = dialogStage;
    }

    @FXML
    public void handleTrimitere (ActionEvent event) {

        try {
           // service.getCordinates("sa");
            service.trimitereMail(email.getText().toString());
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setTitle("Email");
            message.setContentText("S-a trimis email cu istoricul donatiilor.");
            message.showAndWait();
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        /*} catch (SAXException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        */} catch (IOException e) {
            e.printStackTrace();
        }

        dialogStage.hide();
    }

}
