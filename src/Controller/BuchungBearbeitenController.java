package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Fluege;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        MAIN.fensterOeffnen(MAIN.viewsChronik.get(MAIN.viewsChronik.size() - 2));
    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        // todo ParseInt -> wirft exception
        buchung.setBuchungspreis(Integer.parseInt(PreisFeld.getText()));
        buchung.setAnzahlSitzplaetze(Integer.parseInt(PreisFeld.getText()));
        Gepaeck g = buchung.getGepaeck();
        g.setGewicht(Integer.parseInt(GewichtGepaeck.getText()));
        g.setGepaeckTyp(GepaeckTypChoiceBox.getValue());
        buchung.setGepaeck(g);

        try {
            buchung.setHinflug(Fluege.getFlugByID(HinflugFeld.getText()));
        } catch (FlugNotFoundException e) {
            HinflugFeld.setPromptText("Dieser Flug existiert nicht");
        }

        try {
            if (Buchungen.isRueckflug(buchung)) {
                buchung.setRueckflug(Fluege.getFlugByID(RuckflugFeld.getText()));
            }
        } catch (FlugNotFoundException e) {
            RuckflugFeld.setPromptText("Dieser Flug existiert nicht");
        }


    }

}
