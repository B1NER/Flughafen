package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ZahlungController {

    @FXML
    private Button BestatigenButton;

    @FXML
    private Label Gesamtpreis1;

    @FXML
    private Label HinflugGesamtPreis;

    @FXML
    private Label RueckflugGesamtPreis;

    @FXML
    private TextField CSVFeld1;

    @FXML
    private TextField CSVFeld2;

    @FXML
    private Label NachnameText1;

    @FXML
    private Label GesamtPreis2;

    @FXML
    private ChoiceBox<Gepaecktypen> GepackFeld1;

    @FXML
    private Label AnzahlPersonenLabel11;

    @FXML
    private Label GepackText1;

    @FXML
    private Label NachnameText11;

    @FXML
    private Label GewichtText1;

    @FXML
    private ChoiceBox<Gepaecktypen> GepackFeld2;

    @FXML
    private Label PreisproGepackText;

    @FXML
    private Label NameText1;

    @FXML
    private Button berechneGesamtPreisButton2;

    @FXML
    private Label PreisproGepackText11;

    @FXML
    private TextField NameFeld2;

    @FXML
    private Label AnzahlPersonenLabel1;

    @FXML
    private Label AnzahlPersonen2;

    @FXML
    private Label PreisProPerson2;

    @FXML
    private Label PreisProPerson1;

    @FXML
    private Button berechneGesamtPreisButton1;

    @FXML
    private Label AnzahlPersonen1;

    @FXML
    private TextField NachnameFeld1;

    @FXML
    private Tab RueckflugTab;

    @FXML
    private TextField GewichtFeld2;

    @FXML
    private Label PreisproGepackText2;

    @FXML
    private Tab HinflugTab;

    @FXML
    private Label PreisproGepackText1;

    @FXML
    private Label CSVText11;

    @FXML
    private Label GewichtText11;

    @FXML
    private Label KreditkartennummerText11;

    @FXML
    private Label KreditkartennummerText1;

    @FXML
    private TextField GewichtFeld1;

    @FXML
    private TextField KreditkartennummerFeld2;

    @FXML
    private Label GesamtpreisText2;

    @FXML
    private TextField KreditkartennummerFeld1;

    @FXML
    private Label GesamtpreisText1;

    @FXML
    private Label GepackText11;

    @FXML
    private Label GepaeckPreisProKilo2;

    @FXML
    private TextField NameFeld1;

    @FXML
    private Label GepaeckPreisProKilo1;

    @FXML
    private Label CSVText1;

    @FXML
    private Label NameText11;

    @FXML
    private Label GesamtpreisLabel1;

    @FXML
    private Label GesamtpreisLabel2;

    @FXML
    private Button zurueckButton;

    @FXML
    private TextField NachnameFeld2;

    private static Flug hinflug;
    private static Flug rueckflug;
    private static int anzahlPersonen;
    private static int gepaecksPreisProKilo = 10;

    public void initialize() {

        NameFeld1.setText(Verwaltung.getAngemeldeter().getVorname());
        NachnameFeld1.setText(Verwaltung.getAngemeldeter().getNachname());
        GepackFeld1.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Sportgepaeck, Gepaecktypen.Tasche);
        GepackFeld1.setValue(Gepaecktypen.Handgepaeck);
        PreisProPerson1.setText("" + hinflug.getPreisProPerson() + "€");
        GepaeckPreisProKilo1.setText("" + gepaecksPreisProKilo + "€");
        AnzahlPersonen1.setText("" + anzahlPersonen);

        if (rueckflug == null) {
            RueckflugTab.setDisable(true);
        } else {
            NameFeld2.setText(Verwaltung.getAngemeldeter().getVorname());
            NachnameFeld2.setText(Verwaltung.getAngemeldeter().getNachname());
            GepackFeld2.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Sportgepaeck, Gepaecktypen.Tasche);
            GepackFeld2.setValue(Gepaecktypen.Handgepaeck);
            PreisProPerson2.setText("" + rueckflug.getPreisProPerson() + "€");
            GepaeckPreisProKilo2.setText("" + gepaecksPreisProKilo + "€");
            AnzahlPersonen2.setText("" + anzahlPersonen);
        }


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

        if (rueckflug == null) {
            try {
                HinflugGesamtPreis.setText("" + (hinflug.getPreisProPerson() * anzahlPersonen + gepaecksPreisProKilo * Double.parseDouble(GewichtFeld1.getText())));
                GesamtpreisLabel1.setText("" + Double.parseDouble(HinflugGesamtPreis.getText()));
            } catch (IllegalArgumentException e) {
                GewichtFeld1.setPromptText("Gewicht in kg");
            } catch (ClassCastException e) {
                GewichtFeld1.setPromptText("Gewicht in kg");
            }
        }else {
            try {
                HinflugGesamtPreis.setText("" + (hinflug.getPreisProPerson() * anzahlPersonen + gepaecksPreisProKilo * Double.parseDouble(GewichtFeld1.getText())));
                RueckflugGesamtPreis.setText("" + (rueckflug.getPreisProPerson() * anzahlPersonen + gepaecksPreisProKilo * Double.parseDouble(GewichtFeld2.getText())));
                GesamtpreisLabel1.setText("" + (Double.parseDouble(HinflugGesamtPreis.getText()) + Double.parseDouble(RueckflugGesamtPreis.getText())));
                GesamtpreisLabel2.setText("" + (Double.parseDouble(HinflugGesamtPreis.getText()) + Double.parseDouble(RueckflugGesamtPreis.getText())));
            } catch (IllegalArgumentException e) {
                GewichtFeld2.setPromptText("Gewicht in kg");
            } catch (ClassCastException e) {
                GewichtFeld2.setPromptText("Gewicht in kg");
            }
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {

        if (rueckflug == null) {
            if (KreditkartennummerFeld1.getText().equals("")) {
                KreditkartennummerFeld1.setPromptText("Pflichtfeld!");
            }
            if (CSVFeld1.getText().equals("")) {
                CSVFeld1.setPromptText("Pflichtfeld!");
            }
            if (GewichtFeld1.getText().equals("")) {
                GewichtFeld1.setPromptText("Gewicht in kg");
            }
            if (NameFeld1.getText().equals("")) {
                NameFeld1.setPromptText("Pflichtfeld!");
            }
            if (NachnameFeld1.getText().equals("")) {
                NachnameFeld1.setPromptText("Pflichtfeld!");
            } else {
                MAIN.fensterOeffnen(Views.Buchungszusammenfassung);
            }
        } else {
            if (KreditkartennummerFeld1.getText().equals("")) {
                KreditkartennummerFeld1.setPromptText("Pflichtfeld!");
            }
            if (CSVFeld1.getText().equals("")) {
                CSVFeld1.setPromptText("Pflichtfeld!");
            }
            if (GewichtFeld1.getText().equals("")) {
                GewichtFeld1.setPromptText("Gewicht in kg");
            }
            if (NameFeld1.getText().equals("")) {
                NameFeld1.setPromptText("Pflichtfeld!");
            }
            if (NachnameFeld1.getText().equals("")) {
                NachnameFeld1.setPromptText("Pflichtfeld!");
            }
            if (KreditkartennummerFeld2.getText().equals("")) {
                KreditkartennummerFeld2.setPromptText("Pflichtfeld!");
            }
            if (CSVFeld2.getText().equals("")) {
                CSVFeld2.setPromptText("Pflichtfeld!");
            }
            if (GewichtFeld2.getText().equals("")) {
                GewichtFeld2.setPromptText("Gewicht in kg");
            }
            if (NameFeld2.getText().equals("")) {
                NameFeld2.setPromptText("Pflichtfeld!");
            }
            if (NachnameFeld2.getText().equals("")) {
                NachnameFeld2.setPromptText("Pflichtfeld!");
            } else {
                MAIN.fensterOeffnen(Views.Buchungszusammenfassung);
            }
        }
    }

    @FXML
    void zurueckButtonAction(ActionEvent event) {

    }


}
