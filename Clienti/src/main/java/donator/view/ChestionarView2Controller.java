package donator.view;

import donator.entities.Chestionar;
import donator.entities.Donator;
import donator.service.DonatorException;
import donator.service.IServer;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChestionarView2Controller {
    @FXML
    RadioButton rb1aDa, rb1aNu, rb1bDa, rb1bNu, rb1cDa, rb1cNu, rb1dDa, rb1dNu;
    @FXML
    RadioButton rb2Da, rb2Nu, rb3aDa, rb3aNu, rb3bDa, rb3bNu, rb3cDa, rb3cNu, rb3dDa, rb3dNu, rb3eDa, rb3eNu, rb3fDa, rb3fNu, rb3gDa, rb3gNu, rb3hDa, rb3hNu;
    @FXML
    RadioButton rb4aDa, rb4aNu, rb4bDa, rb4bNu, rb4cDa, rb4cNu, rb4dDa, rb4dNu, rb5Da, rb5Nu, rb6Da, rb6Nu, rb7Da, rb7Nu;
    @FXML
    RadioButton  rb8aDa, rb8aNu, rb8bDa, rb8bNu, rb8cDa, rb8cNu, rb8dDa, rb8dNu, rb8eDa, rb8eNu, rb8fDa, rb8fNu, rb8gDa, rb8gNu, rb8hDa, rb8hNu;
    @FXML
    RadioButton  rb9Da, rb9Nu, rb10Da, rb10Nu;
    @FXML
    TextField nume, prenume, email;

    Stage dialogStage;

    IServer service;
    public ChestionarView2Controller(){

    }

    public void setService(IServer service){
        this.service=service;
    }

    @FXML
    public void onClickAdaugaChestionar(ActionEvent actionEvent){
        Chestionar chestionar = new Chestionar();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        chestionar.setDataCompletarii(timeStamp);
        Donator donator = null;
        try {
            donator = service.cautareDonator(email.getText());
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        chestionar.setIdDonator(donator);
        if(rb1aDa.isSelected()){
            chestionar.setIntrebarea1a("Da");
        }
        else{
            chestionar.setIntrebarea1a("Nu");
        }
        if(rb1bDa.isSelected()){
            chestionar.setIntrebarea1b("Da");
        }
        else{
            chestionar.setIntrebarea1b("Nu");
        }
        if(rb1cDa.isSelected()){
            chestionar.setIntrebarea1c("Da");
        }
        else{
            chestionar.setIntrebarea1c("Nu");
        }
        if(rb1dDa.isSelected()){
            chestionar.setIntrebarea1d("Da");
        }
        else{
            chestionar.setIntrebarea1d("Nu");
        }
        if(rb2Da.isSelected()){
            chestionar.setIntrebarea2("Da");
        }
        else{
            chestionar.setIntrebarea2("Nu");
        }
        if(rb3aDa.isSelected()){
            chestionar.setIntrebarea3a("Da");
        }
        else{
            chestionar.setIntrebarea3a("Nu");
        }
        if(rb3bDa.isSelected()){
            chestionar.setIntrebarea3b("Da");
        }
        else{
            chestionar.setIntrebarea3b("Nu");
        }
        if(rb3cDa.isSelected()){
            chestionar.setIntrebarea3c("Da");
        }
        else{
            chestionar.setIntrebarea3c("Nu");
        }
        if(rb3dDa.isSelected()){
            chestionar.setIntrebarea3d("Da");
        }
        else{
            chestionar.setIntrebarea3d("Nu");
        }
        if(rb3eDa.isSelected()){
            chestionar.setIntrebarea3e("Da");
        }
        else{
            chestionar.setIntrebarea3e("Nu");
        }
        if(rb3fDa.isSelected()){
            chestionar.setIntrebarea3f("Da");
        }
        else{
            chestionar.setIntrebarea3f("Nu");
        }
        if(rb3gDa.isSelected()){
            chestionar.setIntrebarea3g("Da");
        }
        else{
            chestionar.setIntrebarea3g("Nu");
        }
        if(rb3hDa.isSelected()){
            chestionar.setIntrebarea3h("Da");
        }
        else{
            chestionar.setIntrebarea3h("Nu");
        }
        if(rb4aDa.isSelected()){
            chestionar.setIntrebarea4a("Da");
        }
        else{
            chestionar.setIntrebarea4a("Nu");
        }
        if(rb4bDa.isSelected()){
            chestionar.setIntrebarea4b("Da");
        }
        else{
            chestionar.setIntrebarea4b("Nu");
        }
        if(rb4cDa.isSelected()){
            chestionar.setIntrebarea4c("Da");
        }
        else{
            chestionar.setIntrebarea4c("Nu");
        }
        if(rb4dDa.isSelected()){
            chestionar.setIntrebarea4d("Da");
        }
        else{
            chestionar.setIntrebarea4d("Nu");
        }
        if(rb5Da.isSelected()){
            chestionar.setIntrebarea5("Da");
        }
        else{
            chestionar.setIntrebarea5("Nu");
        }
        if(rb6Da.isSelected()){
            chestionar.setIntrebarea6("Da");
        }
        else{
            chestionar.setIntrebarea6("Nu");
        }
        if(rb7Da.isSelected()){
            chestionar.setIntrebarea7("Da");
        }
        else{
            chestionar.setIntrebarea7("Nu");
        }
        if(rb8aDa.isSelected()){
            chestionar.setIntrebarea8a("Da");
        }
        else{
            chestionar.setIntrebarea8a("Nu");
        }
        if(rb8bDa.isSelected()){
            chestionar.setIntrebarea8b("Da");
        }
        else{
            chestionar.setIntrebarea8b("Nu");
        }
        if(rb8cDa.isSelected()){
            chestionar.setIntrebarea8c("Da");
        }
        else{
            chestionar.setIntrebarea8c("Nu");
        }
        if(rb8dDa.isSelected()){
            chestionar.setIntrebarea8d("Da");
        }
        else{
            chestionar.setIntrebarea8d("Nu");
        }
        if(rb8eDa.isSelected()){
            chestionar.setIntrebarea8e("Da");
        }
        else{
            chestionar.setIntrebarea8e("Nu");
        }
        if(rb8fDa.isSelected()){
            chestionar.setIntrebarea8f("Da");
        }
        else{
            chestionar.setIntrebarea8f("Nu");
        }
        if(rb8gDa.isSelected()){
            chestionar.setIntrebarea8g("Da");
        }
        else{
            chestionar.setIntrebarea8g("Nu");
        }
        if(rb8hDa.isSelected()){
            chestionar.setIntrebarea8h("Da");
        }
        else{
            chestionar.setIntrebarea8h("Nu");
        }
        if(rb9Da.isSelected()){
            chestionar.setIntrebarea9("Da");
        }
        else{
            chestionar.setIntrebarea9("Nu");
        }
        if(rb10Da.isSelected()){
            chestionar.setIntrebarea10("Da");
        }
        else{
            chestionar.setIntrebarea10("Nu");
        }

        try {
            service.adaugaChestionar(chestionar);
            clearChoices();
            MessageAlert.showMessage(dialogStage, Alert.AlertType.INFORMATION, "STATUS", "Chestionar trimis!");
        } catch (DonatorException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void clearChoices(){
        nume.setText("");
        prenume.setText("");
        email.setText("");
        if(rb1aDa.isSelected()){
            rb1aDa.setSelected(false);
        }
        else{
            rb1aNu.setSelected(false);
        }
        if(rb1bDa.isSelected()){
            rb1bDa.setSelected(false);
        }
        else{
            rb1bNu.setSelected(false);
        }
        if(rb1cDa.isSelected()){
            rb1cDa.setSelected(false);
        }
        else{
            rb1cNu.setSelected(false);
        }
        if(rb1dDa.isSelected()){
            rb1dDa.setSelected(false);
        }
        else{
            rb1dNu.setSelected(false);
        }
        if(rb2Da.isSelected()){
            rb2Da.setSelected(false);
        }
        else{
            rb2Nu.setSelected(false);
        }
        if(rb3aDa.isSelected()){
            rb3aDa.setSelected(false);
        }
        else{
            rb3aNu.setSelected(false);
        }

        if(rb3bDa.isSelected()){
            rb3bDa.setSelected(false);
        }
        else{
            rb3bNu.setSelected(false);
        }
        rb3cDa.setSelected(false);
        rb3dDa.setSelected(false);
        rb3eDa.setSelected(false);
        rb3fDa.setSelected(false);
        rb3gDa.setSelected(false);
        rb3hDa.setSelected(false);
        rb3cNu.setSelected(false);
        rb3dNu.setSelected(false);
        rb3eNu.setSelected(false);
        rb3fNu.setSelected(false);
        rb3gNu.setSelected(false);
        rb3hNu.setSelected(false);

        rb4aDa.setSelected(false);
        rb4aNu.setSelected(false);
        rb4bDa.setSelected(false);
        rb4bNu.setSelected(false);
        rb4cDa.setSelected(false);
        rb4cNu.setSelected(false);
        rb4dDa.setSelected(false);
        rb4dNu.setSelected(false);

        rb5Da.setSelected(false);
        rb5Nu.setSelected(false);

        rb6Da.setSelected(false);
        rb6Nu.setSelected(false);

        rb7Da.setSelected(false);
        rb7Nu.setSelected(false);

        rb8aDa.setSelected(false);
        rb8aNu.setSelected(false);
        rb8bDa.setSelected(false);
        rb8bNu.setSelected(false);
        rb8cDa.setSelected(false);
        rb8cNu.setSelected(false);
        rb8dDa.setSelected(false);
        rb8dNu.setSelected(false);
        rb8eDa.setSelected(false);
        rb8eNu.setSelected(false);
        rb8fDa.setSelected(false);
        rb8fNu.setSelected(false);
        rb8gDa.setSelected(false);
        rb8gNu.setSelected(false);
        rb8hDa.setSelected(false);
        rb8hNu.setSelected(false);

        rb9Da.setSelected(false);
        rb9Nu.setSelected(false);

        rb10Da.setSelected(false);
        rb10Nu.setSelected(false);
    }
}
