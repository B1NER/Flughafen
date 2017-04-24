package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Exceptions.FlugNotFoundException;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Fluege;
import Model.Klassen.Verwaltung.Gepaecke;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BuchungBearbeitenController {

    @FXML
    private TextField RuckflugFeld;

    @FXML
    private Button BestatigenButton;

    @FXML
    private TextField PreisFeld;

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
    private TextField GewichtGepaeck;

    @FXML
    private ChoiceBox <Gepaecktypen> GepaeckTypChoiceBox;

    @FXML
    private Label HinflugText;

    private static Buchung buchung;

    public static void setBuchung(Buchung buchung) {
        BuchungBearbeitenController.buchung = buchung;
    }

    public void initialize() {
        HinflugFeld.setText(buchung.getHinflug().getFlugID());
        if (Buchungen.isRueckflug(buchung)) {
            RuckflugFeld.setText(buchung.getRueckflug().getFlugID());
        }
        AnzahlSitzplatzeFeld.setText(String.valueOf(buchung.getAnzahlSitzplaetze()));
        PreisFeld.setText(String.valueOf(buchung.getBuchungspreis()));
        GewichtGepaeck.setText(String.valueOf(buchung.getGepaeck().getGewicht()));
        GepaeckTypChoiceBox.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Tasche, Gepaecktypen.Sportgepaeck);
        GepaeckTypChoiceBox.setValue(buchung.getGepaeck().getGepaeckTyp());
    }


    @FXML
    void ZuruckAction(ActionEvent event) {
        MAIN.viewsChronik.pop();
        MAIN.fensterOeffnen(MAIN.viewsChronik.pop());
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        buchung.setBuchungspreis(Double.parseDouble(PreisFeld.getText()));
        buchung.setAnzahlSitzplaetze(Integer.parseInt(AnzahlSitzplatzeFeld.getText()));
        try {
            Gepaecke.gepeckBearbeiten(buchung.getGepaeck(), Double.parseDouble(GewichtGepaeck.getText()), GepaeckTypChoiceBox.getValue());
            GewichtGepaeck.setPromptText("");
        } catch (ToHighWeightException e) {
            GewichtGepaeck.setPromptText("Zu hohes Gewicht");
            GewichtGepaeck.setText("");
        }

        try {
            buchung.setHinflug(Fluege.getFlugByID(HinflugFeld.getText()));
            HinflugFeld.setPromptText("");
        } catch (FlugNotFoundException e) {
            HinflugFeld.setPromptText("Dieser Flug existiert nicht");
            HinflugFeld.setText("");
        }

        try {
            if (Buchungen.isRueckflug(buchung)) {
                buchung.setRueckflug(Fluege.getFlugByID(RuckflugFeld.getText()));
                RuckflugFeld.setPromptText("");
            }
        } catch (FlugNotFoundException e) {
            RuckflugFeld.setPromptText("Dieser Flug existiert nicht");
            RuckflugFeld.setText("");
        }
        if (!HinflugFeld.getPromptText().equals("Dieser Flug existiert nicht") && !RuckflugFeld.getPromptText().equals("Dieser Flug existiert nicht") && !GewichtGepaeck.getPromptText().equals("Zu hohes Gewicht")) {
            MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
        }
    }
}
