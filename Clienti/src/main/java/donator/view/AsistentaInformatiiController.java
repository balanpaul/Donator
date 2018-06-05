package donator.view;

import donator.entities.DateSange;
import donator.entities.Donator;
import donator.entities.Programari;
import donator.service.DonatorException;
import donator.service.IClient;
import donator.service.IServer;
import donator.validatori.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class AsistentaInformatiiController extends UnicastRemoteObject implements IClient {
    @FXML
    private TextField nume, prenume, strada, numar, bloc, scara, apartament, oras, judet, telefon, email, cnp, grupaSange;
    @FXML
    private CheckBox respectaNormEU;

    private AsistentaController asistentaController;

    private Donator donator; //donator primit ca parametru de la dclick din view
    private IServer service;
    private Boolean bool;
    private LocalDate localDate;
    private String doneazaPentru;
    private Stage dialogStage;

    public AsistentaInformatiiController() throws RemoteException {
    }


    public void setService(IServer service, AsistentaController asistentaController, Stage dialogStage){
        this.service = service;
        this.asistentaController = asistentaController;
        this.dialogStage = dialogStage;
    }

    @FXML
    public void initialize(Donator donator, Boolean bool, LocalDate localDate, String doneazaPentru) {
        grupaSange.setDisable(true);
        respectaNormEU.setDisable(true);

        this.bool = bool;
        this.localDate = localDate;
        this.doneazaPentru = doneazaPentru;

        //bool  = true inseamna ca e donator nouw
        if(!this.bool){

            this.donator = donator;

            nume.setText(donator.getNume());
            prenume.setText(donator.getPrenume());
            strada.setText(donator.getStrada());
            numar.setText(donator.getNumar());
            bloc.setText(donator.getBloc());
            scara.setText(donator.getScara());
            apartament.setText(donator.getApartament());
            oras.setText(donator.getOras());
            judet.setText(donator.getJudet());
            telefon.setText(donator.getNrTelefon());
            email.setText(donator.getEmail());
            cnp.setText(donator.getCnp());


            DateSange dateSange = null;
            try {
                dateSange = service.getDateSangeDonator(donator.getIdDonator());
            } catch (DonatorException e){
                System.out.println(e);
            } catch (RemoteException e){
                e.printStackTrace();
            }

            if(dateSange != null){
                grupaSange.setText(dateSange.getGrupaSanguina());
                if (dateSange.getSanatos() == 1) {
                    respectaNormEU.setSelected(true);
                }
            }
        } else {
            this.donator = new Donator();
        }
    }

    @FXML
    public void onClickTrimite(){
        if(this.doneazaPentru.equals("notSelected")) {
            setter();

            java.sql.Date p = java.sql.Date.valueOf(localDate);
            Programari programari = new Programari(localDate.getDayOfMonth(), p);

            //bool  = true inseamna ca e donator nouw
            if (!this.bool) {

                try {
                    service.adaugaDonator(donator, programari);
                    donator = service.cautareDonator(donator.getEmail());
                    DateSange dateSange = new DateSange("", 0, "0",0,0,0);
                    service.recoltare(donator, dateSange);

                    System.out.println("Donator adaugat!!");
                } catch (DonatorException e) {
                    System.out.println(e);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ValidationException e) {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setTitle("Whoops");
                    message.setContentText(e.getMessage());
                    message.showAndWait();
                }
            } else {
                try {
                    service.planificare(donator, programari);
                    DateSange dateSange = new DateSange("", 0, "0",0,0,0);
                    service.recoltare(donator, dateSange);


                    System.out.println("Donator adaugat!!");
                } catch (DonatorException e) {
                    System.out.println(e);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        asistentaController.refresh();
        handleClose();
    }

    public void setter(){
        donator.setNume(nume.getText());
        donator.setPrenume(prenume.getText());
        donator.setStrada(strada.getText());
        donator.setNumar(numar.getText());
        donator.setBloc(bloc.getText());
        donator.setScara(bloc.getText());
        donator.setApartament(bloc.getText());
        donator.setOras(oras.getText());
        donator.setJudet(judet.getText());
        donator.setNrTelefon(telefon.getText());
        donator.setEmail(email.getText());
        donator.setCnp(cnp.getText());
    }

    public void handleClose(){
        dialogStage.hide();
    }


}
