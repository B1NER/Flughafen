package Controller;

import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;

public class AnmeldenController {

    @FXML
    private Label PasswordText;

    @FXML
    private Label AnmeldenText;

    @FXML
    private PasswordField PasswordFeld;

    @FXML
    private Button AnmeldenButton;

    @FXML
    private Label EmaliText;

    @FXML
    private TextField EmailFeld;

    @FXML
    private MediaView MediaView;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }

    @FXML
    void EmailFeldAction(ActionEvent event) {}


    @FXML
    void PasswordFeldAction(ActionEvent event) {}

    @FXML
    void AnmeldenAction(ActionEvent event) {
        try {
            Verwaltung.anmelden(EmailFeld.getText(), PasswordFeld.getText());
            main.kundenProfil();
        }
        catch(NutzerDoesNotExistException e){
            EmailFeld.setText("");
            EmailFeld.setPromptText("Email oder Passwort falsch!");
            PasswordFeld.setText("");
        }
    }

    //todo abbrechenbutton
}
