package Controller;

import Model.Enums.Views;
import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class FluglisteController {  //TODO Felder auf die Suchkriterien setzen


    @FXML
    private TableColumn<Flug,String> RueckSpaltePreis;

    @FXML
    private TableColumn<Flug,String> HinSpalteAnkunftsOrt;

    @FXML
    private TextField PersonenanzahlFeld;

    @FXML
    private TableColumn<Flug,String> HinSpalteDatum;

    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField fluggesellschaftFeld;

    @FXML
    private TextField FlugnachFeld;

    @FXML
    private TableColumn<Flug,String> HinSpalteAbflugsOrt;

    @FXML
    private TableColumn<Flug,String> HinSpaltePreis;

    @FXML
    private TableColumn<Flug,String> HinSpalteFluggesellschaft;

    @FXML
    private TableColumn<Flug,String> RueckSpalteAnkunftsOrt;

    @FXML
    private TableView<Flug> hinflugTabelle;

    @FXML
    private Button FlugfindenButton;

    @FXML
    private TableColumn<Flug,String> RueckSpalteAbflugsOrt;

    @FXML
    private TableColumn<Flug,String> RueckSpalteFluggesellschaft;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private Label keineBuchungAusgewaehltLabel;

    @FXML
    private Button zurueckButton1;

    @FXML
    private Tab RueckflugTab;

    @FXML
    private Tab HinflugTab;

    @FXML
    private Button buchenButton;

    @FXML
    private TableColumn<Flug,String> RueckSpalteDatum;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField FlugabFeld;

    @FXML
    private TableView<Flug> RueckflugTabelle;


    private static String flugAb;
    private static String flugNach;
    private static String fluggesellschaft;
    private static String anzahlPersonen;
    private static LocalDate datumHinflug;
    private static LocalDate datumRueckflug;

    public static void setInfos(String flugAb, String flugNach, String fluggesellschaft, String anzahlPersonen, LocalDate datumHinflug) {
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.datumHinflug = datumHinflug;
    }

    public static void setInfos(String flugAb, String flugNach, String fluggesellschaft, String anzahlPersonen, LocalDate datumHinflug, LocalDate datumRueckflug) {
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.datumHinflug = datumHinflug;
        FluglisteController.datumRueckflug = datumRueckflug;
    }

    public void initialize() {
        FlugabFeld.setText(flugAb);
        FlugnachFeld.setText(flugNach);
        fluggesellschaftFeld.setText(fluggesellschaft);
        PersonenanzahlFeld.setText(anzahlPersonen);
        DatumHinflug.setValue(datumHinflug);

        HinSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
        HinSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
        HinSpalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
        HinSpalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
        HinSpaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));

        hinflugTabelle.setRowFactory( tv -> {
            TableRow<Flug> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Flug klickedFlug = row.getItem();
                    ZahlungController.setHinflug(klickedFlug);
                    ZahlungController.setAnzahlPersonen(Integer.parseInt(PersonenanzahlFeld.getText()));

                    if(!Verwaltung.isAngemeldet()){
                        MAIN.fensterOeffnen(Views.Anmelden);
                    }else {
                        MAIN.fensterOeffnen(Views.Zahlung);
                    }

                }
            });
            return row ;
        });

        FlugfindenAction(new ActionEvent());
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
            }
        } catch (FlugNotFoundException e) {
            observableList.clear();
            hinflugTabelle.setItems(observableList);

            Label keineFluege = new Label("Keine Flüge gefunden!");
            keineFluege.setId("keineFluegeGefunden");
            hinflugTabelle.setPlaceholder(keineFluege);

            System.out.println("Flug wurde nicht gefunden");
        }


    }


    @FXML
    void zurueckAction(ActionEvent event) {
        MAIN.fensterOeffnen(MAIN.viewsChronik.get(MAIN.viewsChronik.size() - 2));
    }

    @FXML
    void buchenAction(ActionEvent event){

        if (hinflugTabelle.getSelectionModel().getSelectedItem() != null) {
            Flug klickedFlug = hinflugTabelle.getSelectionModel().getSelectedItem();
            ZahlungController.setHinflug(klickedFlug);
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

    }

}
