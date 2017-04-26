package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

//Verwaltung getestet
public class GepaeckBearbeitenController {

    private static Gepaeck gepaeck;
    private static int gewicht;
    @FXML
    private TextField GewichtFeld;
    @FXML
    private ComboBox<Gepaecktypen> GepaeckTyp;

    public static void setGepaeck(Gepaeck gepaeck, int gewicht) {
        GepaeckBearbeitenController.gepaeck = gepaeck;
        GepaeckBearbeitenController.gewicht = gewicht;
    }

    public void initialize() {
        GepaeckTyp.getItems().addAll(
                Gepaecktypen.Handgepäck,
                Gepaecktypen.Koffer,
                Gepaecktypen.Tasche,
                Gepaecktypen.Sportgepäck);
        GepaeckTyp.setValue(gepaeck.getGepaeckTyp());
        GewichtFeld.setText(String.valueOf(gewicht));

        GewichtFeld.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                gewicht = Integer.parseInt(newValue);
                if (!GewichtFeld.equals("")) {
                    GewichtFeld.setText(GewichtFeld.getText().replace("-", ""));
                }
            } catch (NumberFormatException e) {
                GewichtFeld.setText("");
            }
        });

    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        if (Verwaltung.getAngemeldeter() instanceof Administrator) {
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        } else {
            MAIN.fensterOeffnen(Views.KundenProfil);
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        try {
            Verwaltung.gepaeckBearbeiten(gepaeck, Integer.parseInt(GewichtFeld.getText()), GepaeckTyp.getValue());
            if (Verwaltung.getAngemeldeter() instanceof Administrator) {
                MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
            } else {
                MAIN.fensterOeffnen(Views.KundenProfil);
            }
        } catch (final ToHighWeightException e) {
            GewichtFeld.setPromptText("Zu hohes Gewicht!");
            GewichtFeld.setText("");
        }
    }
}
