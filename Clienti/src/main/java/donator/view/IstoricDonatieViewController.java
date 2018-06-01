package donator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class IstoricDonatieViewController {

    @FXML
    private TextField email;

    @FXML private Text action;

    @FXML protected void handleTrimitere (ActionEvent event) {
        action.setText("Ai apasat pe Trimitere!");
    }

}
