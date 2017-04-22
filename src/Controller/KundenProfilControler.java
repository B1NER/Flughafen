package Controller;

import Model.Enums.Views;
import Model.Exceptions.BuchungDoesNotExistException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class KundenProfilControler {

    @FXML
    private Label RuckflugFeld;

    @FXML
    private Label GeburtsdatumText;

    @FXML
    private Label StartortText;

    @FXML
    private Label SitzplatzeFeld;

    @FXML
    private Label VornameText;

    @FXML
    private Label StartDatumText;

    @FXML
    private Label GepackText;

    @FXML
    private Label VornameFeld;

    @FXML
    private Label AbgelaufenText;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TableColumn<?, ?> SpalteRuckflug;

    @FXML
    private TableColumn<?, ?> SpalteHinflug;

    @FXML
    private Label AnkunftsDatumFeld;

    @FXML
    private Label AnkunftsDatumText;

    @FXML
    private Label PreisText;

    @FXML
    private Button GepackbearbeitenButton;

    @FXML
    private Label NachnameText;

    @FXML
    private Label AbgelaufenFeld;

    @FXML
    private Label ZielortText;

    @FXML
    private Label PreisFeld;

    @FXML
    private Label SitzplatzeText;

    @FXML
    private Button NeueBuchungButton;

    @FXML
    private Label StartortFeld;

    @FXML
    private Label NachnameFeld;

    @FXML
    private Label RuckflugText;

    @FXML
    private Label BuchungenText;

    @FXML
    private Button ProfilBearbeitenButton;

    @FXML
    private Label GeburtsdatumFeld;

    @FXML
    private Label ZielortFeld;

    @FXML
    private Label KundenProfilText;

    @FXML
    private Label HinflugFeld;

    @FXML
    private Label GepackFeld;

    @FXML
    private Label HinflugText;

    private static Buchung buchung;

    public static void setBuchung(Buchung buchung) {
        KundenProfilControler.buchung = buchung;
    }

    @FXML
    public void initialize() {
        try {
            buchung = Buchungen.getBuchungByID(1);      //todo Buchung setzten indem man von liste auswählt
        }catch(BuchungDoesNotExistException e){         // in dem fall nur zum TEst
            e.printStackTrace();
        }

        VornameFeld.setText(Verwaltung.getAngemeldeter().getVorname());
        NachnameFeld.setText(Verwaltung.getAngemeldeter().getNachname());
        GeburtsdatumFeld.setText(Verwaltung.getAngemeldeter().getGeburtsdatum());
    }

    @FXML
    void NeueBuchungAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Buchen); //todo dieses fenster oder nächstes??
    }

    @FXML
    void GepackbearbeitenAction(ActionEvent event) {
        GepaeckBearbeitenController.setGepaeck(buchung.getGepaeck());
        MAIN.fensterOeffnen(Views.GepaeckBearbeiten);
    }

    @FXML
    void ProfilBearbeitenAction(ActionEvent event) {
        ProfilBearbeitenController.setZuBearbeitenderMensch(Verwaltung.getAngemeldeter());
        MAIN.fensterOeffnen(Views.ProfilBearbeiten);
    }

    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

}
