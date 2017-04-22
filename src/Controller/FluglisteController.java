package Controller;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
    private TableColumn <Flug, String> spalteAbflugsOrt;

    @FXML
    private TableColumn <Flug, String> spalteAnkunftsOrt;

    @FXML
    private TableColumn <Flug, String> spalteDatum;

    @FXML
    private TableColumn <Flug, String> spalteFluggesellschaft;

    @FXML
    private TableColumn <Flug, String> spaltePreis;

    @FXML
    private TableView <Flug> flugTabelle;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private TextField fluggesellschaftFeld;

    @FXML
    private DatePicker DatumRuckflug;

    @FXML
    private TextField PersonenanzahlFeld;

    @FXML
    private TextField anzahlPersonen2;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField DazumRuckflugFeld;

    @FXML
    private TextField FlugabFeld;

    @FXML
    private Button FlugfindenButton;

    private static String flugAb;
    private static String flugNach;
    private static String fluggesellschaft;
    private static String anzahlPersonen;
    private static LocalDate abflugDatum;
    private static LocalDate ankunftsDatum;



    public static void setInfos(String flugAb, String flugNach,String fluggesellschaft, String anzahlPersonen, LocalDate abflugDatum){
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.abflugDatum = abflugDatum;
    }

    public static void setInfos(String flugAb, String flugNach,String fluggesellschaft, String anzahlPersonen, LocalDate abflugDatum, LocalDate ankunftsDatum){
        FluglisteController.flugAb = flugAb;
        FluglisteController.flugNach = flugNach;
        FluglisteController.fluggesellschaft = fluggesellschaft;
        FluglisteController.anzahlPersonen = anzahlPersonen;
        FluglisteController.abflugDatum = abflugDatum;
        FluglisteController.ankunftsDatum = ankunftsDatum;
    }

    public void initialize() {
        FlugabFeld.setText(flugAb);
        FlugnachFeld.setText(flugNach);
        fluggesellschaftFeld.setText(fluggesellschaft);
        PersonenanzahlFeld.setText(anzahlPersonen);
        DatumHinflug.setValue(abflugDatum);

        spalteAbflugsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
        spalteAnkunftsOrt.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
        spalteDatum.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugzeit"));
        spalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
        spaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preis"));

        FlugfindenAction(new ActionEvent());
    }

    @FXML
    public void FlugfindenAction(ActionEvent event) {

        try {
            if (FlugabFeld.getText().equals("") && FlugnachFeld.getText().equals("")) {
                FlugabFeld.setText("Pflichtfeld!");
                FlugnachFeld.setText("Pflichtfeld");
            } else {
                if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {

                    ArrayList<Flug> gefundeneFluege = Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText(), Integer.parseInt(PersonenanzahlFeld.getText()));
                    ObservableList<Flug> observableList = FXCollections.observableList(gefundeneFluege);

                    flugTabelle.setItems(observableList);

                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Fluggesellschaft geadded wurde
                }
            }
        } catch (FlugNotFoundException e) {
            System.out.println("Flug wurde nicht gefunden");
        }
    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, int personen){
        FlugabFeld.setText(flugAb);
        FlugnachFeld.setText(flugNach);
        DatumHinflug.setValue(datumHinflug.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        PersonenanzahlFeld.setText("" + personen);
    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, String fluggesellschaft){


    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, Date datumRueckflug, String fluggesellschaft){


    }
}
