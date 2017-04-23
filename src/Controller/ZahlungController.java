package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
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
import sun.applet.Main;

public class ZahlungController {

    @FXML
    private TextField GewichtFeld;

    @FXML
    private TextField KreditkartennummerFeld;

    @FXML
    private TextField NameFeld;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Label GewichtText;

    @FXML
    private TextField CSVFeld;

    @FXML
    private Label RueckFlugPreisProPersonText;

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
    private ChoiceBox<Gepaecktypen> GepackFeld;

    private static Flug hinflug;
    private static Flug rueckflug;
    private static int anzahlPersonen;

    public void initialize() {

        NameFeld.setText(Verwaltung.getAngemeldeter().getVorname());
        NachnameFeld.setText(Verwaltung.getAngemeldeter().getNachname());
        GepackFeld.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Sportgepaeck, Gepaecktypen.Tasche);
        GepackFeld.setValue(Gepaecktypen.Handgepaeck);
        HinflugPreisLabel.setText("" + hinflug.getPreisProPerson() + "€");
        if (rueckflug != null) {
            RueckflugPreisLabel.setText("" + rueckflug.getPreisProPerson() + "€");
        } else {
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
    void berechneGesamtPreisAction(ActionEvent event) {
        try {
            if (rueckflug != null) {
                GesamtpreisLabel.setText("" + (hinflug.getPreisProPerson() * anzahlPersonen + rueckflug.getPreisProPerson() * anzahlPersonen + Double.parseDouble(GewichtText.getText()) * anzahlPersonen * 40));
            } else {
                double gesamtpreis = hinflug.getPreisProPerson() * anzahlPersonen + Double.parseDouble(GewichtFeld.getText()) * anzahlPersonen * 40;
                GesamtpreisLabel.setText("" + gesamtpreis);
            }
        } catch (IllegalArgumentException e) {
            GewichtFeld.setPromptText("Gewicht in kg");
        } catch (ClassCastException e) {
            GewichtFeld.setPromptText("Gewicht in kg");
        }

    }

    @FXML
    void BestatigenAction(ActionEvent event) {
        if (KreditkartennummerFeld.getText().equals("")) {
            KreditkartennummerFeld.setPromptText("Pflichtfeld!");
        }
        if (CSVFeld.getText().equals("")) {
            CSVFeld.setPromptText("Pflichtfeld!");
        }
        if(GewichtFeld.getText().equals("")){
            GewichtFeld.setPromptText("Gewicht in kg");
        }
        if(NameFeld.getText().equals("")){
            NameFeld.setPromptText("Pflichtfeld!");
        }
        if(NachnameFeld.getText().equals("")){
            NachnameFeld.setPromptText("Pflichtfeld!");
        }else {
            MAIN.fensterOeffnen(Views.Buchungszusammenfassung);
        }
    }

}
