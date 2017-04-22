package Controller;

import Model.Enums.Gepaecktypen;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.control.ChoiceBox;

public class ZahlungController {

    @FXML
    private Button BestatigenButton;

    @FXML
    private Label ZalungsartText;

    @FXML
    private TextField GewichtFeld;

    @FXML
    private Button ZusammenfassungButton;

    @FXML
    private Label NachnameText;

    @FXML
    private Button FlugauswahlButton;

    @FXML
    private Label GesamtpreisText;

    @FXML
    private Button MeineDatenButton;

    @FXML
    private Label PreisproPersonText;

    @FXML
    private TextField KreditkartennummerFeld;

    @FXML
    private Label KreditkartennummerText;

    @FXML
    private Label PreisproGepackText;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Label GewichtText;

    @FXML
    private Label CSVText;

    @FXML
    private Label NameText;

    @FXML
    private TextField CSVFeld;

    @FXML
    private Label RueckFlugPreisProPersonText;

    @FXML
    private Label GepackText;

    @FXML
    private Label HinflugPreisLabel;

    @FXML
    private Label RueckflugPreisLabel;

    @FXML
    private Label AnzahlPersonenLabel;

    @FXML
    private Label GepaeckPreisProKiloLabel;

    @FXML
    private Label GesamtpreisLabel;

    @FXML
    private Button berechneGesamtPreisButton;

    @FXML
    private ChoiceBox<Gepaecktypen> GepackFeld;

    private static Flug hinflug;
    private static Flug rueckflug;
    private static int anzahlPersonen;

    public void initialize() {

       // NameFeld.setText(Verwaltung.getAngemeldeter().getVorname());
       // NachnameFeld.setText(Verwaltung.getAngemeldeter().getNachname());
        GepackFeld.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Sportgepaeck, Gepaecktypen.Tasche);
        HinflugPreisLabel.setText("" + hinflug.getPreisProPerson() + "€");
        if(rueckflug != null) {
            RueckflugPreisLabel.setText("" + rueckflug.getPreisProPerson() + "€");
        }
        else {
            RueckflugPreisLabel.setDisable(true);
            RueckFlugPreisProPersonText.setDisable(true);
        }
        GepaeckPreisProKiloLabel.setText("" + 40 + "€");
        AnzahlPersonenLabel.setText("" + anzahlPersonen);
        GewichtFeld.setText("0");
    }

    public static void setHinflug(Flug hinflug) {
        ZahlungController.hinflug = hinflug;
    }

    public static void setRueckflug(Flug rueckflug) {
        ZahlungController.rueckflug = rueckflug;
    }

    public static void setAnzahlPersonen(int anzahlPersonen) {
        ZahlungController.anzahlPersonen = anzahlPersonen;
    }

    @FXML
    void FlugauswahlAction(ActionEvent event) {

    }

    @FXML
    void MeineDatenAction(ActionEvent event) {

    }

    @FXML
    void berechneGesamtPreisAction(ActionEvent event) {
        try {
            if(rueckflug != null) {
                GesamtpreisLabel.setText("" +(hinflug.getPreisProPerson() * anzahlPersonen + rueckflug.getPreisProPerson() * anzahlPersonen + Integer.parseInt(GewichtText.getText())));
            }
            else {
                double gesamtpreis = hinflug.getPreisProPerson() * anzahlPersonen + Double.parseDouble(GewichtFeld.getText()) * anzahlPersonen;
                GesamtpreisLabel.setText("" + gesamtpreis);
            }
        }catch (IllegalArgumentException e){
            GewichtFeld.setText("Gewicht in kg");
        }
        catch (ClassCastException e){
            GewichtFeld.setText("Gewicht in kg");
        }

    }


    @FXML
    void BestatigenAction(ActionEvent event) {

    }

}
