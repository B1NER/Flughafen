package Controller;

import Model.Klassen.Elemente.Buchung;
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

public class BuchungBearbeitenController {

    @FXML
    private TextField RuckflugFeld;

    @FXML
    private Button BestatigenButton;

    @FXML
    private PasswordField PreisFeld;

    @FXML
    private Label AnzahlSitzplatzeText;

    @FXML
    private Label BuchungbearbeitenText;

    @FXML
    private TextField AnzahlSitzplatzeFeld;

    @FXML
    private Label RuckflugText;

    @FXML
    private Button ZuruckButton;

    @FXML
    private TextField HinflugFeld;

    @FXML
    private Label PreisText;

    @FXML
    private Label GepackText;

    @FXML
    private PasswordField GepackFeld;

    @FXML
    private Label HinflugText;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }


    @FXML
    void ZuruckAction(ActionEvent event) {
        if(Verwaltung.getAngemeldeter() instanceof Administrator){
            main.profilBearbeiten();
        }
        else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            main.profilBearbeiten();
        }
        else{
            main.kundenProfil();
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {

    }

}
