package donator.view;

import donator.service.DonatorException;
import donator.service.IServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IstoricDonatieViewController extends UnicastRemoteObject{

    @FXML
    private TextField email;

    @FXML private Text action;

    IServer service;

    public IstoricDonatieViewController() throws RemoteException {
    }

    public void setService( IServer stage) {
        this.service=stage;
    }

    @FXML
    public void handleTrimitere (ActionEvent event) {

        try {
            service.trimitereMail(email.getText().toString());
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
