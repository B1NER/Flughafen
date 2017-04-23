package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private ComboBox<Gepaecktypen> GepaeckTyp;

    @FXML
    private Button AbbrechenButton;

    private static Gepaeck gepaeck;
    private static int gewicht;

    public static void setGepaeck(Gepaeck gepaeck, int gewicht) {
        GepaeckBearbeitenController.gepaeck = gepaeck;
        GepaeckBearbeitenController.gewicht = gewicht;
    }

    public void initialize() {
        GepaeckTyp.getItems().addAll(
                Gepaecktypen.Handgepaeck,
                Gepaecktypen.Koffer,
                Gepaecktypen.Tasche,
                Gepaecktypen.Sportgepaeck);
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        } else {
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        boolean x = false;
        while (!x) {
            try {
                Verwaltung.gepaeckBearbeiten(gepaeck, Integer.parseInt(GewichtFeld.getText()), GepaeckTyp.getValue());
                x = true;
            } catch (final ToHighWeightException e) {
                GewichtFeld.setPromptText("Zu hohes Gewicht!");
            }
        }

        if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        } else {
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

}
