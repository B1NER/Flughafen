package Controller;

import Model.Enums.Views;
import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class FluglisteController {


    @FXML
    private TableColumn<Flug, String> RueckSpaltePreis;

    @FXML
    private TableColumn<Flug, String> HinSpalteAnkunftsOrt;

    @FXML
    private TextField PersonenanzahlFeld;

    @FXML
    private TableColumn<Flug, String> HinSpalteDatum;

    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField fluggesellschaftFeld;

    @FXML
    private TextField FlugnachFeld;

    @FXML
    private TableColumn<Flug, String> HinSpalteAbflugsOrt;

    @FXML
    private TableColumn<Flug, String> HinSpaltePreis;

    @FXML
    private TableColumn<Flug, String> HinSpalteFluggesellschaft;

    @FXML
    private TableColumn<Flug, String> RueckSpalteAnkunftsOrt;

    @FXML
    private TableView<Flug> hinflugTabelle;

    @FXML
    private Button FlugfindenButton;

    @FXML
    private TableColumn<Flug, String> RueckSpalteAbflugsOrt;

    @FXML
    private TableColumn<Flug, String> RueckSpalteFluggesellschaft;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private Label keineBuchungAusgewaehltLabel;

    @FXML
    private Button zurueckButton1;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab rueckflugTab;

    @FXML
    private Tab hinflugTab;

    @FXML
    private Button buchenButton;

    @FXML
    private TableColumn<Flug, String> RueckSpalteDatum;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField FlugabFeld;

    @FXML
    private TableView<Flug> rueckflugTabelle;


    private static String flugAb;
    private static String flugNach;
    private static String fluggesellschaft;
    private static String anzahlPersonen;
    private static LocalDate datumHinflug;
    private static LocalDate datumRueckflug;
    private static boolean isWithRueckflug;
    private static Flug hinflug = null;
    private static Flug rueckflug = null;

    public static void setInfos(String flugAb, String flugNach, String fluggesellschaft, String anzahlPersonen, LocalDate datumHinflug) {
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.datumHinflug = datumHinflug;
        FluglisteController.isWithRueckflug = false;
    }

    public static void setInfos(String flugAb, String flugNach, String fluggesellschaft, String anzahlPersonen, LocalDate datumHinflug, LocalDate datumRueckflug) {
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.datumHinflug = datumHinflug;
        FluglisteController.datumRueckflug = datumRueckflug;
        FluglisteController.isWithRueckflug = true;
    }

    public void initialize() {

        hinflug = null;
        rueckflug = null;

        if(!isWithRueckflug){
            rueckflugTab.setDisable(true);
        }else {
            tabPane.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<Tab>() {
                        @Override
                        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab aktuellerTab) {
                            tabHasChanged(aktuellerTab);
                        }
                    }
            );
            FlugauswahlText.setText("Hinflug auswählen");
        }
        tabHasChanged(hinflugTab);
        FlugfindenAction(new ActionEvent());

    }

    private void tabHasChanged(Tab tab) {

        if (!isWithRueckflug) { //nur Hinflug
            FlugabFeld.setText(flugAb);
            FlugnachFeld.setText(flugNach);
            fluggesellschaftFeld.setText(fluggesellschaft);
            PersonenanzahlFeld.setText(anzahlPersonen);
            DatumHinflug.setValue(datumHinflug);

            HinSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
            HinSpalteAnkunftsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
            HinSpalteDatum.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
            HinSpalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
            HinSpaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));
            rueckflugTab.setDisable(true);

            if (hinflugTabelle.getSelectionModel().getSelectedItem() != null) {
                hinflug = hinflugTabelle.getSelectionModel().getSelectedItem();
            }

            FlugfindenAction(new ActionEvent());

        } else {

            if (tab.getId().equals("hinflugTab")) {
                FlugabFeld.setText(flugAb);
                FlugnachFeld.setText(flugNach);
                fluggesellschaftFeld.setText(fluggesellschaft);
                PersonenanzahlFeld.setText(anzahlPersonen);
                DatumHinflug.setValue(datumHinflug);

                HinSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
                HinSpalteAnkunftsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
                HinSpalteDatum.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
                HinSpalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
                HinSpaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));

                if (hinflugTabelle.getSelectionModel().getSelectedItem() != null) {
                    hinflug = hinflugTabelle.getSelectionModel().getSelectedItem();
                }

                FlugfindenAction(new ActionEvent());

            } else if (tab.getId().equals("rueckflugTab")) {

                FlugabFeld.setText(flugNach);
                FlugnachFeld.setText(flugAb);
                fluggesellschaftFeld.setText(fluggesellschaft);
                PersonenanzahlFeld.setText(anzahlPersonen);
                DatumHinflug.setValue(datumRueckflug);

                RueckSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
                RueckSpalteAnkunftsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
                RueckSpalteDatum.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
                RueckSpalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
                RueckSpaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));

                if (rueckflugTabelle.getSelectionModel().getSelectedItem() != null) {
                    rueckflug = rueckflugTabelle.getSelectionModel().getSelectedItem();
                }

                FlugfindenAction(new ActionEvent());
            }

        }
    }

    @FXML
    public void FlugfindenAction(ActionEvent event) {
        ObservableList<Flug> observableList = FXCollections.observableList(new ArrayList<>());

        try {
            if (FlugabFeld.getText().equals("") && FlugnachFeld.getText().equals("")) {
                FlugabFeld.setText("Pflichtfeld!");
                FlugnachFeld.setText("Pflichtfeld");
            } else {
                ArrayList<Flug> gefundeneFluege;
                if (!fluggesellschaftFeld.getText().equals("") && DatumHinflug.getValue() == null) {
                    //mit Fluggesellschaft, ohne Datum
                    gefundeneFluege = Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText(), fluggesellschaftFeld.getText(), Integer.parseInt(PersonenanzahlFeld.getText()));
                } else if (DatumHinflug.getValue() != null && fluggesellschaftFeld.getText().equals("")) {
                    //ohne Fluggesellschaft, mit Datum
                    gefundeneFluege = Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText(), Date.from(DatumHinflug.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Integer.parseInt(PersonenanzahlFeld.getText()));
                } else if (DatumHinflug.getValue() != null && !fluggesellschaftFeld.getText().equals("")) {
                    //mit Fluggesellschaft, mit Datum
                    gefundeneFluege = Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText(), Date.from(DatumHinflug.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), fluggesellschaftFeld.getText(), Integer.parseInt(PersonenanzahlFeld.getText()));
                } else {
                    //nur Flugab, Flugnach
                    gefundeneFluege = Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText(), Integer.parseInt(PersonenanzahlFeld.getText()));
                }
                observableList = FXCollections.observableList(gefundeneFluege);
                hinflugTabelle.setItems(observableList);
                if(isWithRueckflug){
                    rueckflugTabelle.setItems(observableList);
                }
            }
        } catch (FlugNotFoundException e) {
            observableList.clear();
            hinflugTabelle.setItems(observableList);

            Label keineFluege = new Label("Keine Flüge gefunden!");
            keineFluege.setId("keinErgebnis");
            hinflugTabelle.setPlaceholder(keineFluege);

            System.out.println("Flug wurde nicht gefunden");
        }


    }

    @FXML
    void zurueckAction(ActionEvent event) {
        MAIN.viewsChronik.pop();
        MAIN.fensterOeffnen(MAIN.viewsChronik.pop());
    }

    @FXML
    void buchenAction(ActionEvent event) {

        if(!isWithRueckflug) {
            if (hinflugTabelle.getSelectionModel().getSelectedItem() != null) {
                hinflug = hinflugTabelle.getSelectionModel().getSelectedItem();
                ZahlungController.setHinflug(hinflug);
                ZahlungController.setAnzahlPersonen(Integer.parseInt(PersonenanzahlFeld.getText()));
                if (!Verwaltung.isAngemeldet()) {
                    MAIN.fensterOeffnen(Views.Anmelden);
                } else {
                    MAIN.fensterOeffnen(Views.Zahlung);
                }
            } else {
                keineBuchungAusgewaehltLabel.setTextFill(Color.RED);
                keineBuchungAusgewaehltLabel.setText("Flug auswählen!");
            }
        }else {

            if(hinflugTab.isSelected()) {
                if (hinflugTabelle.getSelectionModel().getSelectedItem() != null) {
                    hinflug = hinflugTabelle.getSelectionModel().getSelectedItem();
                    ZahlungController.setHinflug(hinflug);
                    ZahlungController.setAnzahlPersonen(Integer.parseInt(PersonenanzahlFeld.getText()));

                    if (rueckflug == null) {
                        keineBuchungAusgewaehltLabel.setTextFill(Color.RED);
                        keineBuchungAusgewaehltLabel.setText("Rückflug auswählen!");
                    } else if (!Verwaltung.isAngemeldet()) {
                        MAIN.fensterOeffnen(Views.Anmelden);
                    } else {
                        MAIN.fensterOeffnen(Views.Zahlung);
                    }

                } else {
                    keineBuchungAusgewaehltLabel.setTextFill(Color.RED);
                    keineBuchungAusgewaehltLabel.setText("Hinflug auswählen!");
                }
            }else if(rueckflugTab.isSelected()) {
                if (rueckflugTabelle.getSelectionModel().getSelectedItem() != null) {
                    rueckflug = rueckflugTabelle.getSelectionModel().getSelectedItem();
                    ZahlungController.setRueckflug(rueckflug);
                    ZahlungController.setAnzahlPersonen(Integer.parseInt(PersonenanzahlFeld.getText()));
                    if (hinflug == null) {
                        keineBuchungAusgewaehltLabel.setTextFill(Color.RED);
                        keineBuchungAusgewaehltLabel.setText("Hinflug auswählen!");
                    } else if (!Verwaltung.isAngemeldet()) {
                        MAIN.fensterOeffnen(Views.Anmelden);
                    } else {
                        MAIN.fensterOeffnen(Views.Zahlung);
                    }

                } else {
                    keineBuchungAusgewaehltLabel.setTextFill(Color.RED);
                    keineBuchungAusgewaehltLabel.setText("Rückflug auswählen!");
                }
            }

        }

    }


}
