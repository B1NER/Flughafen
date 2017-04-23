package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Date;

public class AdminStartseiteController {

    @FXML
    private TableColumn<Flug, String> SpalteStartort;

    @FXML
    private TableColumn<Flug, String> SpalteZielort;

    @FXML
    private Button AngestelltehinzufugenButton;

    @FXML
    private Label FlugListeText;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TableColumn<Flug, Date> SpalteStartzeit;

    @FXML
    private TableColumn<Flug, Date> SpalteAnkunftszeit;

    @FXML
    private Button BenutzerBearbeitenButton;

    @FXML
    private VBox AdministartorText;

    @FXML
    private TableView<Flug> tabelle;

    @FXML
    private TableColumn<Flug, String> SpalteFluggesellschaft;

    @FXML
    private TableColumn<Flug, Double> SpalteGepaeckskapazitaet;

    @FXML
    private TableColumn<Flug, Double> SpalteSitzplaetze;

    @FXML
    private TableColumn<Flug, String> SpaltePreis;

    @FXML
    private TableColumn<Flug, String> SpalteStatus;

    @FXML
    private TableColumn<Flug, String> SpalteID;



    @FXML
    private Button BuchungBearbeitenButton;

    @FXML
    private TableColumn<?, ?> SpalteFlugID;

    public void initialize() {
        SpalteID.setCellValueFactory(new PropertyValueFactory<Flug, String>("FlugID"));
        SpalteStartort.setCellValueFactory(new PropertyValueFactory<Flug, String>("abflugort"));
        SpalteZielort.setCellValueFactory(new PropertyValueFactory<Flug, String>("ankunftsort"));
        SpalteStartzeit.setCellValueFactory(new PropertyValueFactory<Flug, Date>("abflugzeit"));
        SpalteAnkunftszeit.setCellValueFactory(new PropertyValueFactory<Flug, Date>("ankunftszeit"));
        SpalteFluggesellschaft.setCellValueFactory(new PropertyValueFactory<Flug, String>("flugGesellschaft"));
        SpalteGepaeckskapazitaet.setCellValueFactory(new PropertyValueFactory<Flug, Double>("gepaeckskapazitaet"));
        SpalteSitzplaetze.setCellValueFactory(new PropertyValueFactory<Flug, Double>("anzahlPlaetze"));
        SpaltePreis.setCellValueFactory(new PropertyValueFactory<Flug, String>("preisProPerson"));
        SpalteStatus.setCellValueFactory(new PropertyValueFactory<Flug, String>("status"));

        ArrayList<Flug> alleFluege = Verwaltung.getFluege();
        ObservableList<Flug> observableList = FXCollections.observableList(alleFluege);

        tabelle.setItems(observableList);
    }


    @FXML
    void NutzerHinzufugenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }

    @FXML
    void BenutzerBearbeitenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
    }

    @FXML
    void BuchungBearbeitenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.ZuBearbeitendeBuchungFinden);
    }

    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

    //TODO Buchung durchfüren (Zu Bearbeitenden Nutzer finden --> Button Buchen)

}
