package Controller;

import Model.Enums.Views;
import Model.Exceptions.EmailIsAlreadyUsedException;
import Model.Exceptions.InvalidEmailException;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;

public class RegistrierenController {

    @FXML
    private PasswordField PasswortFeld;

    @FXML
    private PasswordField PasswortBestaetigen;

    @FXML
    private RadioButton AngestellterRButton;

    @FXML
    private TextField VornameFeld;

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
    private TextField EmailFeld;

    @FXML
    private TextField PassnummerFeld;

    @FXML
    private Button RegistrierenButton;


    public void initialize() {
        if (Verwaltung.isAngemeldet()) {
            if (Verwaltung.getAngemeldeter() instanceof Administrator) { //admin kann alles erstellen
                RegistrierenText.setText("Nutzer anlegen");
                RegistrierenButton.setText("Anlegen");
                AnwenderRbutton.setVisible(true);
                AdminRButton.setVisible(true);
                AngestellterRButton.setVisible(true);
            }
            if (Verwaltung.getAngemeldeter() instanceof Angestellter) { //angestellter kann nur Anwender erstellen
                RegistrierenText.setText("Nutzer anlegen");
                RegistrierenButton.setText("Anlegen");
                AnwenderRbutton.setVisible(false);
                AdminRButton.setVisible(false);
                AngestellterRButton.setVisible(false);
            }
        } else { //falls keiner angemeldet ist, meldet sich ein Anwender an
            RegistrierenText.setText("Registrieren");
            RegistrierenButton.setText("Registrieren");
            AnwenderRbutton.setVisible(false);
            AdminRButton.setVisible(false);
            AngestellterRButton.setVisible(false);
        }
        GeburtsdatumFeld.setEditable(false);
    }


    @FXML
    void AbbrechenAction(ActionEvent event) {
        if (Verwaltung.getAngemeldeter() instanceof Administrator) {
            MAIN.fensterOeffnen(Views.AdminStartseite);
        } else if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        } else {
            MAIN.viewsChronik.pop();
            MAIN.fensterOeffnen(MAIN.viewsChronik.pop());
        }
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {

        if (VornameFeld.getText().equals("")) {
            VornameFeld.setPromptText("Geben Sie einen Vornamen ein!");
            return;
        }
        if (NachnameFeld.getText().equals("")) {
            NachnameFeld.setPromptText("Geben Sie einen Nachnamen ein!");
            return;
        }
        if (PassnummerFeld.getText().equals("")) {
            PassnummerFeld.setPromptText("Geben Sie eine Passnummer ein!");
            return;
        }

        if (PassnummerFeld.getText().equals("") || PasswortBestaetigen.getText().equals("")) {
            PasswortFeld.setPromptText("Geben Sie ein gültiges Passwort ein!");
            return;
        }
        if (!(PasswortFeld.getText().equals(PasswortBestaetigen.getText()))) {
            PasswortFeld.setText("");
            PasswortBestaetigen.setText("");
            PasswortFeld.setPromptText("Die Passwörter stimmen nicht überein!");
            PasswortBestaetigen.setPromptText("Die Passwörter stimmen nicht überein!");
            return;
        }

        try {
            if (Verwaltung.getAngemeldeter() instanceof Administrator) {      //Admin
                if (AnwenderRbutton.isSelected() && !AngestellterRButton.isSelected() && !AdminRButton.isSelected()) {
                    Verwaltung.anwenderErstellen(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), Integer.parseInt(PassnummerFeld.getText()), EmailFeld.getText(), PasswortFeld.getText());
                    MAIN.fensterOeffnen(Views.AdminStartseite);
                } else if (!AnwenderRbutton.isSelected() && AngestellterRButton.isSelected() && !AdminRButton.isSelected()) {
                    Verwaltung.angestellterErstellen(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), Integer.parseInt(PassnummerFeld.getText()), EmailFeld.getText(), PasswortFeld.getText());
                    MAIN.fensterOeffnen(Views.AdminStartseite);
                } else if (!AnwenderRbutton.isSelected() && !AngestellterRButton.isSelected() && AdminRButton.isSelected()) {
                    Verwaltung.adminErstellten(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), Integer.parseInt(PassnummerFeld.getText()), EmailFeld.getText(), PasswortFeld.getText());
                    MAIN.fensterOeffnen(Views.AdminStartseite);
                }
            } else if (Verwaltung.getAngemeldeter() instanceof Angestellter) {    //Angestellter
                Verwaltung.anwenderErstellen(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), Integer.parseInt(PassnummerFeld.getText()), EmailFeld.getText(), PasswortFeld.getText());
                MAIN.fensterOeffnen(Views.AngestellterStartseite);
            } else {
                Verwaltung.anwenderErstellen(VornameFeld.getText(), NachnameFeld.getText(), Date.from(GeburtsdatumFeld.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()).toString(), Integer.parseInt(PassnummerFeld.getText()), EmailFeld.getText(), PasswortFeld.getText());
                Verwaltung.anmelden(Verwaltung.getAnwenderByID(Verwaltung.getNutzerIDByEmail(EmailFeld.getText())));
                KundenProfilControler.setAnwender((Anwender) Verwaltung.getAngemeldeter());

                if (MAIN.viewsChronik.get(MAIN.viewsChronik.size() - 3).equals(Views.Flugliste)) {
                    MAIN.fensterOeffnen(Views.Zahlung);
                } else {
                    MAIN.fensterOeffnen(Views.KundenProfil);
                }

            }
        } catch (final EmailIsAlreadyUsedException e) {
            EmailFeld.setPromptText("Diese Emai l wird bereits verwendet!");
            EmailFeld.setText("");
        } catch (final InvalidEmailException e) {
            EmailFeld.setPromptText("Ungültige Email!");
            EmailFeld.setText("");
        } catch (final NumberFormatException e) {
            PassnummerFeld.setPromptText("Nur Zahlen erlaubt");
            EmailFeld.setText("");
        } catch (final NutzerDoesNotExistException e) {
            System.out.println("Kunde wurde nicht gefunden!");
            //Nie der Fall da der Kunde gerade erstellt wurde
        }
    }
}
