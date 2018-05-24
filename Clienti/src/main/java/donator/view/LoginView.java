package donator.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import java.awt.event.ActionEvent;

public class LoginView {
    @FXML
    private ComboBox personal;
    @FXML
    private TextField passwordField;

    @FXML private Text actiontarget;

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }




}
