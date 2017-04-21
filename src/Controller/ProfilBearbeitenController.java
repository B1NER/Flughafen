package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Nutzer.Mensch;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ProfilBearbeitenController {

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Label PasswordText;

    @FXML
    private Button BestatigenButton;

    @FXML
    private Label GeburtsdatumText;

    @FXML
    private PasswordField Password2Feld;

    @FXML
    private Label Password2Text;

    @FXML
    private DatePicker GeburtsdatumFeld;

    @FXML
    private Label VornameText;

    @FXML
    private Button AbbrechenButton;

    @FXML
    private Label PersonbearbeitenText;

    @FXML
    private TextField VornameFeld;

    @FXML
    private PasswordField Password;

    public MAIN main;

    private static Mensch zuBearbeitenderMensch;

    public static void setZuBearbeitenderMensch(Mensch zuBearbeitenderMensch) {
        ProfilBearbeitenController.zuBearbeitenderMensch = zuBearbeitenderMensch;
    }

    public void setMain(MAIN main) {
        VornameFeld.setText(zuBearbeitenderMensch.getVorname());
        NachnameFeld.setText(zuBearbeitenderMensch.getNachname());
        String date[] = zuBearbeitenderMensch.getGeburtsdatum().split("\\.");
        GeburtsdatumFeld.setValue(LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0])));
        Password.setText(zuBearbeitenderMensch.getPasswort());
        Password2Feld.setText(zuBearbeitenderMensch.getPasswort());
        this.main = main;
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        if(Verwaltung.getAngemeldeter() instanceof Administrator){
            MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
        }else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
        }else{
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        if(Password.getText().equals(Password2Feld.getText())) {
            zuBearbeitenderMensch.setVorname(VornameFeld.getText());
            zuBearbeitenderMensch.setNachname(NachnameFeld.getText());
            //todo keine ahnung mit geb datum wia tian --> zuBearbeitenderMensch.setGeburtsdatum(GeburtsdatumFeld.);
            zuBearbeitenderMensch.setPasswort(Password.getText());
            if(Verwaltung.getAngemeldeter() instanceof Administrator){
                MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
            }else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
                MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
            }else{
                MAIN.fensterOeffnen(Views.KundenProfil);
            }
        }else{
            Password.setText("");
            Password2Feld.setText("");
            Password.setPromptText("Die Passwörter müssen überein stimmen");//todo Rechtschreibung ??
        }
    }

}
