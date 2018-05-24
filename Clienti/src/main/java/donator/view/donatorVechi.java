package donator.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class donatorVechi {
    @FXML
    private TextField Nume,Email,Prenume,incOra,sfOra;

    @FXML
    private DatePicker datePicker;

    public donatorVechi(){
        init();
    }

    private void init() {

        Donator donator=new Donator();
        for(int i=1;i<=2;++i){

        }
    }

    @FXML
    public void onClickTrimitere(ActionEvent actionEvent) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText(pattern.toLowerCase());
            }

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        LocalDate data = datePicker.getValue();
        String s = data.toString();
        int inceput = Integer.valueOf( incOra.getText());
        int sfarsit = Integer.valueOf( sfOra.getText());
        find(Email.getText(),Nume.getText(),Prenume.getText());
    }

    private void find(String text, String text1, String text2) {
    }


}
