package Controller;

import Model.Enums.Views;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
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
    private Button RegistrierenButton;

    @FXML
    private Button AnmeldenButton;

    @FXML
    private Button AbbrechenButton;

    @FXML
    private Label EmaliText;

    @FXML
    private TextField EmailFeld;

    @FXML
    private MediaView MediaView;

    public void initialize() {

    }

    @FXML
    void EmailFeldAction(ActionEvent event) {}


    @FXML
    void PasswordFeldAction(ActionEvent event) {}

    @FXML
    void AnmeldenAction(ActionEvent event) {
        try {
            Verwaltung.anmelden(EmailFeld.getText(), PasswordFeld.getText());

            if(Verwaltung.getAngemeldeter() instanceof Administrator){
                MAIN.fensterOeffnen(Views.AdminStartseite);
            }else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
                MAIN.fensterOeffnen(Views.AngestellterStartseite);
            }else{
                MAIN.fensterOeffnen(Views.KundenProfil);
            }
        }
        catch(NutzerDoesNotExistException e){
            PasswordFeld.setPromptText("Email oder Passwort falsch!");
            PasswordFeld.setText("");
        }
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Buchen);
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }

}
