package donator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class IstoricDonatieViewController {

    @FXML
    private TextField email;

    @FXML private Text action;

    Stage dialogStage;

    public void setService(/*StudentService studentService,*/ Stage stage) {
        //this.studentService = studentService;
        this.dialogStage=stage;
    }

    @FXML protected void handleTrimitere (ActionEvent event) {
        action.setText("Ai apasat pe Trimitere!");
    }

}
