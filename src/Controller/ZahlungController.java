package Controller;

import Model.Enums.Gepaecktypen;
import Model.Enums.Views;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Anwender;
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
    private Tab HinflugTab;

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

    @FXML
    private TabPane tabPane;

    private static Flug hinflug;
    private static Flug rueckflug;
    private static int anzahlPersonen;
    private static int gepaecksPreisProKilo = 10;
    private static Anwender anwender;

    SingleSelectionModel<Tab> selectionModel;

    public void initialize() {
        //tabPane.getTabs().addAll(HinflugTab, RueckflugTab);
        selectionModel = tabPane.getSelectionModel();

        if (Verwaltung.getAngemeldeter() instanceof Anwender) {
            NameFeld1.setText(Verwaltung.getAngemeldeter().getVorname());
            NachnameFeld1.setText(Verwaltung.getAngemeldeter().getNachname());

            if (rueckflug == null) {
                RueckflugTab.setDisable(true);
            } else {
                NameFeld2.setText(Verwaltung.getAngemeldeter().getVorname());
                NachnameFeld2.setText(Verwaltung.getAngemeldeter().getNachname());
                PreisProPerson2.setText("" + rueckflug.getPreisProPerson() + "€");
                GepaeckPreisProKilo2.setText("" + gepaecksPreisProKilo + "€");
                AnzahlPersonen2.setText("" + anzahlPersonen);
            }

        } else {
            NameFeld1.setText(anwender.getVorname());
            NachnameFeld1.setText(anwender.getNachname());

            if (rueckflug == null) {
                RueckflugTab.setDisable(true);
            } else {
                NameFeld2.setText(anwender.getVorname());
                NachnameFeld2.setText(anwender.getNachname());
                PreisProPerson2.setText("" + rueckflug.getPreisProPerson() + "€");
                GepaeckPreisProKilo2.setText("" + gepaecksPreisProKilo + "€");
                AnzahlPersonen2.setText("" + anzahlPersonen);
            }
        }

        GepackFeld1.getItems().addAll(Gepaecktypen.Handgepaeck, Gepaecktypen.Koffer, Gepaecktypen.Sportgepaeck, Gepaecktypen.Tasche);
        GepackFeld1.setValue(Gepaecktypen.Handgepaeck);
        PreisProPerson1.setText("" + hinflug.getPreisProPerson() + "€");
        GepaeckPreisProKilo1.setText("" + gepaecksPreisProKilo + "€");
        AnzahlPersonen1.setText("" + anzahlPersonen);

    }

    public static void setAnwender(Anwender anwender) {
        ZahlungController.anwender = anwender;
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
                GewichtFeld1.setText("");
                GewichtFeld1.setPromptText("Gewicht in kg");
            } catch (ClassCastException e) {
                GewichtFeld1.setText("");
                GewichtFeld1.setPromptText("Gewicht in kg");
            }
        } else {
            try {
                HinflugGesamtPreis.setText("" + (hinflug.getPreisProPerson() * anzahlPersonen + gepaecksPreisProKilo * Double.parseDouble(GewichtFeld1.getText())));
                RueckflugGesamtPreis.setText("" + (rueckflug.getPreisProPerson() * anzahlPersonen));
                GesamtpreisLabel1.setText("" + (Double.parseDouble(HinflugGesamtPreis.getText()) + Double.parseDouble(RueckflugGesamtPreis.getText())));
                GesamtpreisLabel2.setText("" + (Double.parseDouble(HinflugGesamtPreis.getText()) + Double.parseDouble(RueckflugGesamtPreis.getText())));
            } catch (IllegalArgumentException e) {
                GewichtFeld1.setText("");
                GewichtFeld1.setPromptText("Gewicht in kg");
            } catch (ClassCastException e) {
                GewichtFeld1.setText("");
                GewichtFeld1.setPromptText("Gewicht in kg");
            }
        }
    }

    @FXML
    void BestatigenAction(ActionEvent event) {

        berechneGesamtPreisAction(new ActionEvent());

        if (rueckflug == null) {
            if (KreditkartennummerFeld1.getText().equals("")) {
                KreditkartennummerFeld1.setPromptText("Pflichtfeld!");
            } else if (CSVFeld1.getText().equals("")) {
                CSVFeld1.setPromptText("Pflichtfeld!");
            } else if (NameFeld1.getText().equals("")) {
                NameFeld1.setPromptText("Pflichtfeld!");
            } else if (NachnameFeld1.getText().equals("")) {
                NachnameFeld1.setPromptText("Pflichtfeld!");
            } else if (GewichtFeld1.getText().equals("")) {
                GewichtFeld1.setPromptText("Gewicht in kg");
            } else {

                try {
                    if (Integer.parseInt(GewichtFeld1.getText()) > anzahlPersonen * 20) {
                        throw new ToHighWeightException();
                    }
                    Verwaltung.gepaeckErstellen(Double.parseDouble(GewichtFeld1.getText()), GepackFeld1.getValue());
                    if (Verwaltung.getAngemeldeter() instanceof Anwender) {
                        Verwaltung.buchungErstellen(hinflug, (Anwender) Verwaltung.getAngemeldeter(), anzahlPersonen, Verwaltung.getGepaeck().get(Verwaltung.getGepaeck().size() - 1), Double.parseDouble(GesamtpreisLabel1.getText()), true);
                    } else {
                        Verwaltung.buchungErstellen(hinflug, anwender, anzahlPersonen, Verwaltung.getGepaeck().get(Verwaltung.getGepaeck().size() - 1), Double.parseDouble(GesamtpreisLabel1.getText()), false);
                    }
                    BuchungszusammenfassungController.setBuchung(Verwaltung.getBuchungen().get(Verwaltung.getBuchungen().size() - 1));
                    MAIN.fensterOeffnen(Views.Buchungszusammenfassung);
                } catch (ToHighWeightException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Zu hohes Gewicht");
                    System.out.println("zu hohes gewicht");
                } catch (NumberFormatException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                } catch (IllegalArgumentException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                } catch (ClassCastException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                }

            }
        } else {
            if (KreditkartennummerFeld1.getText().equals("")) {
                KreditkartennummerFeld1.setPromptText("Pflichtfeld!");
                selectionModel.select(HinflugTab);
            } else if (CSVFeld1.getText().equals("")) {
                CSVFeld1.setPromptText("Pflichtfeld!");
                selectionModel.select(HinflugTab);
            } else if (NameFeld1.getText().equals("")) {
                NameFeld1.setPromptText("Pflichtfeld!");
                selectionModel.select(HinflugTab);
            } else if (NachnameFeld1.getText().equals("")) {
                NachnameFeld1.setPromptText("Pflichtfeld!");
                selectionModel.select(HinflugTab);
            } else if (KreditkartennummerFeld2.getText().equals("")) {
                KreditkartennummerFeld2.setPromptText("Pflichtfeld!");
                selectionModel.select(RueckflugTab);
            } else if (CSVFeld2.getText().equals("")) {
                CSVFeld2.setPromptText("Pflichtfeld!");
                selectionModel.select(RueckflugTab);
            } else if (NameFeld2.getText().equals("")) {
                NameFeld2.setPromptText("Pflichtfeld!");
                selectionModel.select(RueckflugTab);
            } else if (NachnameFeld2.getText().equals("")) {
                NachnameFeld2.setPromptText("Pflichtfeld!");
                selectionModel.select(RueckflugTab);
            } else if (GewichtFeld1.getText().equals("")) {
                GewichtFeld1.setPromptText("Gewicht in kg");
                selectionModel.select(HinflugTab);
            } else {
                try {
                    if (Integer.parseInt(GewichtFeld1.getText()) > anzahlPersonen * 20) {
                        throw new ToHighWeightException();
                    }
                    Verwaltung.gepaeckErstellen(Double.parseDouble(GewichtFeld1.getText()), GepackFeld1.getValue());
                    if (Verwaltung.getAngemeldeter() instanceof Anwender) {
                        Verwaltung.buchungErstellen(hinflug, rueckflug, (Anwender) Verwaltung.getAngemeldeter(), anzahlPersonen, Verwaltung.getGepaeck().get(Verwaltung.getGepaeck().size() - 1), Double.parseDouble(GesamtpreisLabel1.getText()), true);
                    } else {
                        Verwaltung.buchungErstellen(hinflug, rueckflug, anwender, anzahlPersonen, Verwaltung.getGepaeck().get(Verwaltung.getGepaeck().size() - 1), Double.parseDouble(GesamtpreisLabel1.getText()), false);
                    }

                    BuchungszusammenfassungController.setBuchung(Verwaltung.getBuchungen().get(Verwaltung.getBuchungen().size() - 1));
                    MAIN.fensterOeffnen(Views.Buchungszusammenfassung);
                } catch (ToHighWeightException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Zu hohes Gewicht");
                } catch (NumberFormatException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                } catch (IllegalArgumentException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                } catch (ClassCastException e) {
                    GewichtFeld1.setText("");
                    GewichtFeld1.setPromptText("Gewicht in kg");
                }

            }
        }
    }

    @FXML
    void zurueckButtonAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Flugliste);
    }


}
