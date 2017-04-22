package Controller;

import Model.Enums.Views;
import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import com.sun.rowset.internal.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class FluglisteController {  //TODO Felder auf die Suchkriterien setzen


    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField FlugnachFeld;

    @FXML
    private TableColumn<Flug, String> spalteAbflugsOrt;

    @FXML
    private TableColumn<Flug, String> spalteAnkunftsOrt;

    @FXML
    private TableColumn<Flug, String> spalteDatum;

    @FXML
    private TableColumn<Flug, String> spalteFluggesellschaft;

    @FXML
    private TableColumn<Flug, String> spaltePreis;

    @FXML
    private TableView<Flug> flugTabelle;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private TextField fluggesellschaftFeld;

    @FXML
    private DatePicker DatumRuckflug;

    @FXML
    private TextField PersonenanzahlFeld;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField FlugabFeld;

    @FXML
    private Button FlugfindenButton;

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

        spalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
        spalteAnkunftsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
        spalteDatum.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
        spalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
        spaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));

        flugTabelle.setRowFactory( tv -> {
            TableRow<Flug> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    Flug klickedFlug = row.getItem();
                    ZahlungController.setHinflug(klickedFlug);
                    ZahlungController.setAnzahlPersonen(Integer.parseInt(PersonenanzahlFeld.getText()));
                    MAIN.fensterOeffnen(Views.Zahlung);
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
                flugTabelle.setItems(observableList);
            }
        } catch (FlugNotFoundException e) {
            observableList.clear();
            flugTabelle.setItems(observableList);

            Label keineFluege = new Label("Keine Flüge gefunden!");
            keineFluege.setId("keineFluegeGefunden");
            flugTabelle.setPlaceholder(keineFluege);

            System.out.println("Flug wurde nicht gefunden");
        }


    }


}
