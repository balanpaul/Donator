package donator.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Alex on 22 mai 2018.
 */
public class DonatorNouViewController {

    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private TextField textFieldStrada;
    @FXML
    private TextField textFieldNumar;
    @FXML
    private TextField textFieldBloc;
    @FXML
    private TextField textFieldScara;
    @FXML
    private TextField textFieldApartament;
    @FXML
    private TextField textFieldOras;
    @FXML
    private TextField textFieldJudet;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField textFieldIntervalOrar1;
    @FXML
    private TextField textFieldIntervalOrar2;


    Stage dialogStage;

    @FXML
    private void initialize() {
    }


    public void setService(/*StudentService studentService,*/ Stage stage) {
        //this.studentService = studentService;
        this.dialogStage=stage;
    }

    public donatorNouViewController() {
    }

    @FXML
    public void handleSend(){
        String nume = textFieldNume.getText();
        String prenume = textFieldPrenume.getText();
        String strada = textFieldStrada.getText();
        String numar = textFieldNumar.getText();
        String bloc = textFieldBloc.getText();
        String scara = textFieldScara.getText();
        String apartament = textFieldApartament.getText();
        String oras = textFieldOras.getText();
        String judet = textFieldJudet.getText();
        String telefon = textFieldTelefon.getText();
        String email=textFieldEmail.getText();
        LocalDate localDate = datePicker.getValue();
        String intervalOrar1 = textFieldIntervalOrar1.getText();
        String intervalOrar2 = textFieldIntervalOrar2.getText();

        //Donator donator = new Donator(nume, prenume, strada, numar,
        //       bloc, scara, apartament, oras, judet, telefon, email,
        //     localDate, intervalOrar1, intervalOrar2);

        /**
        try {
            Student s=new Student(id,nume,Integer.parseInt(grupa),email,profesor);
            studentService.save(id,s);

            MessageAlert.showMessage(dialogStage, Alert.AlertType.INFORMATION, "Salvare cu succes", "Studentul a fost adaugat!");
            clearFields();
        } catch (RepositoryException e1) {
            MessageAlert.showMessage(dialogStage, Alert.AlertType.INFORMATION, "Datele nu sunt bune", "Studentul nu a fost adaugat!");
            clearFields();
        }catch(ValidationException | NumberFormatException  el){
            MessageAlert.showErrorMessage(dialogStage, "Exista deja un student cu acest id!");
            //el.printStackTrace();
        }**/

    }

    @FXML
    public void handleCancel(){
        dialogStage.close();
    }

}
