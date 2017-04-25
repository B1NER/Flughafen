package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class ZuBearbeitendeBuchungFindenConroller {

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
    private TableColumn<Buchung, String> SpaltebyAngestellter;

    @FXML
    private TextField VornameFeld;

    @FXML
    private TextField NachnameFeld;

    private ObservableList<Buchung> observableList;
    private ArrayList<Anwender> anwenders = Verwaltung.getAnwender();

    public void initialize() {
        for (int i = 0; i < Verwaltung.getBuchungen().size(); i++) {
            if (!Verwaltung.getBuchungen().get(i).isCreatedByAnwender()) {
                for (HashMap.Entry<Anwender, Angestellter> h : Verwaltung.getAnwenderAnestellten().entrySet()) {
                    if (h.getKey().equals(Verwaltung.getBuchungen().get(i).getAnwender())) {
                        Verwaltung.getBuchungen().get(i).setCreatedBy(h.getValue().getVorname() + " " + h.getValue().getNachname());
                    }
                }

            }
        }
        SpalteHinflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("hinflug"));
        SpalteRueckflug.setCellValueFactory(new PropertyValueFactory<Buchung, String>("rueckflug"));
        SpalteAnwender.setCellValueFactory(new PropertyValueFactory<Buchung, Anwender>("anwender"));
        SpalteSitzplaetze.setCellValueFactory(new PropertyValueFactory<Buchung, String>("anzahlSitzplaetze"));
        SpalteGepaeck.setCellValueFactory(new PropertyValueFactory<Buchung, String>("gepaeck"));
        SpaltePreis.setCellValueFactory(new PropertyValueFactory<Buchung, Double>("buchungspreis"));
        SpaltebyAngestellter.setCellValueFactory(new PropertyValueFactory<Buchung, String>("createdBy"));

        observableList = FXCollections.observableList(Verwaltung.getBuchungen());
        if (observableList.size() == 0) {
            System.out.println("Keine Buchungen vorhanden");
            Label keinErgebnis = new Label("Keine Buchungen vorhanden!");
            keinErgebnis.setId("keinErgebnis");
            tabelle.setPlaceholder(keinErgebnis);
        }
        tabelle.setItems(observableList);
    }


    @FXML
    void SuchenAction(ActionEvent event) {

        ArrayList<Buchung> zutreffendeBuchungen = new ArrayList<>();


        if (!VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            //Suche nach Vornamenx
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
        } else if (!VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")) {
            //Suche nach Vornamen und Nachnamen
            for (int i = 0; i < anwenders.size(); i++) {
                if (anwenders.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase()) && anwenders.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeBuchungen.addAll(Verwaltung.getBuchungenByAnwender(anwenders.get(i)));
                }
            }
        }

        observableList = FXCollections.observableList(zutreffendeBuchungen);
        tabelle.setItems(observableList);

        if (VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            observableList = FXCollections.observableList(Verwaltung.getBuchungen());
            tabelle.setItems(observableList);
        }

        if (observableList.size() < 1) {
            observableList.clear();
            tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnisse mit diesen Eigenschaften");
            Label keinErgebnis = new Label("Kein Ergebnis gefunden!");
            keinErgebnis.setId("keinErgebnis");
            tabelle.setPlaceholder(keinErgebnis);
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
        if (tabelle.getSelectionModel().getSelectedItem() != null) {
            BuchungBearbeitenController.setBuchung(tabelle.getSelectionModel().getSelectedItem());
            MAIN.fensterOeffnen(Views.BuchungBearbeiten);
        }
    }

    @FXML
    void ZuruckAction(ActionEvent event) {
        MAIN.viewsChronik.pop();
        MAIN.fensterOeffnen(MAIN.viewsChronik.pop());
    }

}
