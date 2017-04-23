package Controller;

import Model.Enums.Views;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.MediaView;
import sun.applet.Main;

import javax.swing.text.View;
import java.util.ArrayList;

public class ZuBearbeitendeBuchungFindenConroller {

    @FXML
    private Button RegistrierenButton;

    @FXML
    private TextField FlugAbFeld;

    @FXML
    private DatePicker DatumRuckflug;

    @FXML
    private Button AnmeldenButton;

    @FXML
    private Label FlugsuchenText;

    @FXML
    private TextField FlugNachFeld2;

    @FXML
    private Button minusButton;

    @FXML
    private DatePicker DatumHinflug2;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private TextField AnzahlFeld;

    @FXML
    private TextField FlugNachFeld;

    @FXML
    private MediaView MediaView;

    @FXML
    private Button plusButton;

    @FXML
    private Button FlugSuchenButton2;

    @FXML
    private TextField FlugAbFeld2;

    @FXML
    private Button FlugSuchenButton;

    @FXML
    private TableView<Buchung> tabelle;

    @FXML
    private TableColumn<Buchung, String> SpalteHinflug;

    @FXML
    private TableColumn<Buchung, String> SpalteRueckflug;

    @FXML
    private TableColumn<Buchung, Anwender> SpalteAnwender;

    @FXML
    private TableColumn<Buchung, String> SpalteSitzplaetze;

    @FXML
    private TableColumn<Buchung, String> SpalteGepaeck;

    @FXML
    private TableColumn<Buchung, Double> SpaltePreis;

    @FXML
    private TableColumn<Buchung, Boolean> SpaltebyAngestellter;

    @FXML
    private TextField VornameFeld;

    @FXML
    private TextField NachnameFeld;

    private ObservableList<Buchung> observableList;
    private ArrayList<Anwender> anwenders = Verwaltung.getAnwender();

    public void initialize() {
        SpalteHinflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("hinflug"));
        SpalteRueckflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("rueckflug"));
        SpalteAnwender.setCellValueFactory(new PropertyValueFactory<Buchung, Anwender>("anwender"));
        SpalteSitzplaetze.setCellValueFactory(new PropertyValueFactory<Buchung, String>("anzahlSitzplaetze"));
        SpalteGepaeck.setCellValueFactory(new PropertyValueFactory<Buchung, String>("gepaeck"));
        SpaltePreis.setCellValueFactory(new PropertyValueFactory<Buchung, Double>("buchungspreis"));
        SpaltebyAngestellter.setCellValueFactory(new PropertyValueFactory<Buchung, Boolean>("createdByAnwender"));

        observableList = FXCollections.observableList(Verwaltung.getBuchungen());
        if (observableList.size() == 0) {
            System.out.println("Der Anwender hat keine Kunden!");
            Label keineFluege = new Label("Keine Anwender gefunden!");
            keineFluege.setId("keineFluegeGefunden");
            tabelle.setPlaceholder(keineFluege);
        }
        tabelle.setItems(observableList);
    }


    @FXML
    void SuchenAction(ActionEvent event) {

        ArrayList<Buchung> zutreffendeBuchungen = new ArrayList<>();


        if (!VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            //Suche nach Vornamen
            for (int i = 0; i < anwenders.size(); i++) {
                if (anwenders.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(anwenders.get(i)));
                }
            }
        } else if (VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")) {
            //Suche nach Nachnamen
            for (int i = 0; i < anwenders.size(); i++) {
                if (anwenders.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase())) {
                    zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(anwenders.get(i)));
                }
            }
        } else {
            //Suche nach Vornamen und Nachnamen
            for (int i = 0; i < anwenders.size(); i++) {
                if (anwenders.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase()) && anwenders.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(anwenders.get(i)));
                }
            }
        }

        observableList = FXCollections.observableList(zutreffendeBuchungen);
        if (observableList.size() < 1) {
            observableList.clear();
            tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnise mit diesen Eigenschaften");
            Label keinErgebniss = new Label("Kein Ergebniss gefunden!");
            keinErgebniss.setId("keinErgebniss");
            tabelle.setPlaceholder(keinErgebniss);
        } else {
            tabelle.setItems(observableList);
        }

    }

    @FXML
    void LoschenAction(ActionEvent event) {
        Buchungen.removeBuchung(tabelle.getSelectionModel().getSelectedItem());
        observableList.remove(tabelle.getSelectionModel().getSelectedItem());
        initialize();
    }

    @FXML
    void BearbeitenAction(ActionEvent event) {
        BuchungBearbeitenController.setBuchung(tabelle.getSelectionModel().getSelectedItem());
        MAIN.fensterOeffnen(Views.BuchungBearbeiten);
    }

    @FXML
    void ZuruckAction(ActionEvent event) {
        MAIN.fensterOeffnen(MAIN.viewsChronik.get(MAIN.viewsChronik.size() - 2));
    }

}
