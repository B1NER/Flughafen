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
    private ArrayList<Buchung> zutreffendeBuchungen = new ArrayList<>();

    public void initialize() {
        SpalteHinflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("hinflug"));
        SpalteRueckflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("rueckflug"));
        SpalteAnwender.setCellValueFactory(new PropertyValueFactory<Buchung, Anwender>("anwender"));
        SpalteSitzplaetze.setCellValueFactory(new PropertyValueFactory<Buchung, String>("anzahlSitzplaetze"));
        SpalteGepaeck.setCellValueFactory(new PropertyValueFactory<Buchung, String>("gepaeck"));
        SpaltePreis.setCellValueFactory(new PropertyValueFactory<Buchung, Double>("buchungspreis"));
        SpaltebyAngestellter.setCellValueFactory(new PropertyValueFactory<Buchung, Boolean>("createdByAnwender"));
    }


    @FXML
    void SuchenAction(ActionEvent event) {

            if (!VornameFeld.getText().equals("")) {
                if (!NachnameFeld.getText().equals("")) {  //Vor- und Nachname
                    for (int i = 0; i < Verwaltung.getAnwender().size(); i++) {
                        if (Verwaltung.getAnwender().get(i).getVorname().equalsIgnoreCase(VornameFeld.getText()) && Verwaltung.getAnwender().get(i).getNachname().equalsIgnoreCase(NachnameFeld.getText())) {
                            zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(Verwaltung.getAnwender().get(i)));
                        }
                    }
                } else {  //Vorname
                    for (int i = 0; i < Verwaltung.getAnwender().size(); i++) {
                        if (Verwaltung.getAnwender().get(i).getVorname().equalsIgnoreCase(VornameFeld.getText())) {
                            zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(Verwaltung.getAnwender().get(i)));
                        }
                    }
                }
            } else {
                VornameFeld.setPromptText("Pflichtfeld!");
            }

            observableList = FXCollections.observableList(zutreffendeBuchungen);
            tabelle.setItems(observableList);


            if (zutreffendeBuchungen.size() < 1) {
                System.out.println("Es gibt keinen Buchungen mit diesen Eigenschaften");
                Label keineBuchungen = new Label("Keine Buchungen gefunden!");
                tabelle.setPlaceholder(keineBuchungen);
            } else {
                observableList = FXCollections.observableList(zutreffendeBuchungen);
            }

    }

    @FXML
    void LoschenAction(ActionEvent event) {
        Buchungen.removeBuchung(tabelle.getSelectionModel().getSelectedItem());
        observableList.removeAll(observableList);
        SuchenAction(new ActionEvent());
        tabelle.setItems(observableList);
    }

    @FXML
    void BearbeitenAction(ActionEvent event) {
        BuchungBearbeitenController.setBuchung(tabelle.getSelectionModel().getSelectedItem());
        MAIN.fensterOeffnen(Views.BuchungBearbeiten);
    }

    @FXML
    void ZuruckAction(ActionEvent event) {
        MAIN.fensterOeffnen(MAIN.viewsChronik.get(MAIN.viewsChronik.size()-2));
    }

}
