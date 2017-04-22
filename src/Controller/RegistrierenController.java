package Controller;

import Model.Enums.Views;
import Model.Exceptions.EmailIsAlreadyUsedException;
import Model.Exceptions.InvalidEmailException;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Date;

public class RegistrierenController {

    @FXML
    private Label NachnameText;

    @FXML
    private Button RegistrierenButton;

    @FXML
    private PasswordField PasswordFeld;

    @FXML
    private PasswordField Password2Feld;

    @FXML
    private Label GeburtsdatumText;

    @FXML
    private RadioButton AngestellterRButton;

    @FXML
    private Label Password2Text;

    @FXML
    private Label VornameText;

    @FXML
    private Button AbbrechenButton;

    @FXML
    private TextField VornameFeld;

    @FXML
    private Label PasswordText;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Label RegistrierenText;

    @FXML
    private DatePicker GeburtsdatumFeld;

    @FXML
    private RadioButton AnwenderRbutton;

    @FXML
    private RadioButton AdminRButton;

    @FXML
    private Label EmailText;

    @FXML
    private TextField EmailFeld;

    public void initialize() {

        if (Verwaltung.isAngemeldet()) {
            if (Verwaltung.getAngemeldeter() instanceof Administrator) { //admin kann alles erstellen
                RegistrierenText.setText("Nutzer anlegen");
                AnwenderRbutton.setSelected(false);
                AnwenderRbutton.setVisible(true);
                AdminRButton.setVisible(true);
                AngestellterRButton.setVisible(true);
            }
            if (Verwaltung.getAngemeldeter() instanceof Angestellter) { //angestellter kann nur Anwender erstellen
                RegistrierenText.setText("Nutzer anlegen");
                AnwenderRbutton.setSelected(true);
                AnwenderRbutton.setVisible(false);
                AdminRButton.setVisible(false);
                AngestellterRButton.setVisible(false);
            }
        } else { //falls keiner angemeldet ist, meldet sich ein Anwender an
            RegistrierenText.setText("Registrieren");
            AnwenderRbutton.setSelected(true);
            AnwenderRbutton.setVisible(false);
            AdminRButton.setVisible(false);
            AngestellterRButton.setVisible(false);
        }
        GeburtsdatumFeld.setEditable(false);
    }


    @FXML
    void AbbrechenAction(ActionEvent event) {
        if(Verwaltung.getAngemeldeter() instanceof Administrator){
            MAIN.fensterOeffnen(Views.AdminStartseite);
        }else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        }else{
            MAIN.fensterOeffnen(Views.Buchen);
        }
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {

        if (Password2Feld.getText().equals("") || PasswordFeld.getText().equals("")) {
            PasswordFeld.setPromptText("Geben Sie ein gültiges Passwort ein!");
        }
        if (!(PasswordFeld.getText().equals(Password2Feld.getText()))) {
            PasswordFeld.setPromptText("Die Passwörter stimmen nicht überein!");
            PasswordFeld.setText("");
            Password2Feld.setText("");
            Password2Feld.setPromptText("Die Passwörter stimmen nicht überein!");
            return;
        }
        if (VornameFeld.getText().equals("")) {
            VornameFeld.setPromptText("Geben Sie einen Vornamen ein!");
        }
        if (NachnameFeld.getText().equals("")) {
            NachnameFeld.setPromptText("Geben Sie einen Nachnamen ein!");
        }

        if (AnwenderRbutton.isSelected()) {
            try {
                Verwaltung.anwenderErstellen(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), 777, EmailFeld.getText(), PasswordFeld.getText());
                //todo registrieren fehlt noch passnummer
                try {
                    if(!(Verwaltung.getAngemeldeter() instanceof Administrator) && !(Verwaltung.getAngemeldeter() instanceof Angestellter)) {
                        Verwaltung.anmelden(EmailFeld.getText(), PasswordFeld.getText());
                        MAIN.fensterOeffnen(Views.Buchen);
                    }
                    else if(Verwaltung.getAngemeldeter() instanceof Administrator){
                        MAIN.fensterOeffnen(Views.AdminStartseite);
                    }else{
                        MAIN.fensterOeffnen(Views.AngestellterStartseite);
                    }
                } catch (NutzerDoesNotExistException e) {
                    //wird nie der Fall sein, da er gerade erstellt wurde
                    e.printStackTrace();
                }

            } catch (EmailIsAlreadyUsedException e) {
                EmailFeld.setPromptText("Diese Email wird bereits verwendet!");
                EmailFeld.setText("");
            } catch (InvalidEmailException e) {
                EmailFeld.setText("");
                EmailFeld.setPromptText("Keine gültige Email!");
            }

        }
        if (AdminRButton.isSelected() || AngestellterRButton.isSelected()) {
            try {
                Verwaltung.adminErstellten(VornameFeld.getText(), NachnameFeld.getText(), GeburtsdatumFeld.getAccessibleText(), 777, EmailFeld.getText(), PasswordFeld.getText());
                MAIN.fensterOeffnen(Views.AdminStartseite);
            } catch (EmailIsAlreadyUsedException e) {
                EmailFeld.setPromptText("Diese Email wird bereits verwendet!");
                EmailFeld.setText("");
            } catch (InvalidEmailException e) {
                EmailFeld.setText("");
                EmailFeld.setPromptText("Keine gültige Email!");
            }

        }
    }

}
