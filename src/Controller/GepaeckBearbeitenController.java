package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GepaeckBearbeitenController {

    @FXML
    private Button BestatigenButton;

    @FXML
    private TextField GewichtFeld;

    @FXML
    private Label GepackbearbeitenText;

    @FXML
    private Button AbbrechenButton;

    public MAIN main;

    private static Gepaeck gepaeck;

    public static void setGepaeck(Gepaeck gepaeck) {
        GepaeckBearbeitenController.gepaeck = gepaeck;
    }

    public void setMain(MAIN main) {
        this.main = main;
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        }else{
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        //Verwaltung.gepaeckBearbeiten(gepaeck,GewichtFeld.getText(),); todo enum gepaecktyp ??
        if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        }else{
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

}
