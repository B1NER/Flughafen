package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AngestellterStartseiteController {

    @FXML
    private Label NachnameText;

    @FXML
    private TableColumn<Anwender, String> SpalteVorname;

    @FXML
    private TableColumn<Anwender, String> SpalteEmail;

    @FXML
    private Button KundenanlegenButton;

    @FXML
    private TableColumn<Anwender, String> SpalteGeburtsdatum;

    @FXML
    private Button KundenSuchenButton;

    @FXML
    private Label AngestellterText;

    @FXML
    private Label VornameText;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Button KundebearbeitenButton;

    @FXML
    private TableView<Anwender> Tabelle;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TextField VornameFeld;

    @FXML
    private Button BuchungdurchfurenButten;

    @FXML
    private TableColumn<Anwender, String> SpalteNachname;


    private static ObservableList<Anwender> observableList = FXCollections.observableList(new ArrayList<>());
    ArrayList<Anwender> myAnwenders = Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter());

    public void initialize() {
        SpalteVorname.setCellValueFactory(new PropertyValueFactory<Anwender, String>("vorname"));
        SpalteNachname.setCellValueFactory(new PropertyValueFactory<Anwender, String>("nachname"));
        SpalteGeburtsdatum.setCellValueFactory(new PropertyValueFactory<Anwender, String>("geburtsdatum"));
        SpalteEmail.setCellValueFactory(new PropertyValueFactory<Anwender, String>("email"));

        observableList = FXCollections.observableList(myAnwenders);
        Tabelle.setItems(observableList);

        if (observableList.size() < 1) {
            observableList.clear();
            Tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnisse mit diesen Eigenschaften");

            Label keinErgebnis = new Label("Kein Ergebnis gefunden!");
            keinErgebnis.setId("keinErgebnis");     //TODO LABEL wird nicht gesetzt
            Tabelle.setPlaceholder(keinErgebnis);
        }
    }


    @FXML
    void KundenSuchenAction(ActionEvent event) {

        //TODO suchen --> content in tabelle
        //TODO Datum

        ArrayList<Anwender> zutreffendeAnwender = new ArrayList<>();

        if (!VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            //Suche nach Vornamen
            for (int i = 0; i < myAnwenders.size(); i++) {
                if (myAnwenders.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(myAnwenders.get(i));
                }
            }
        } else if (VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")) {
            //Suche nach Nachnamen
            for (int i = 0; i < Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).size(); i++) {
                if (myAnwenders.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(myAnwenders.get(i));
                }
            }
        } else if (!VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")){
            //Suche nach Vornamen und Nachnamen
            for (int i = 0; i < Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).size(); i++) {
                if (myAnwenders.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase()) && myAnwenders.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(myAnwenders.get(i));
                }
            }
        }

        observableList = FXCollections.observableList(zutreffendeAnwender);
        Tabelle.setItems(observableList);

        if (observableList.size() < 1) {
            observableList.clear();
            Tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnisse mit diesen Eigenschaften");

            Label keinErgebnis = new Label("Kein Ergebnis gefunden!");
            keinErgebnis.setId("keinErgebnis");     //TODO LABEL wird nicht gesetzt
            Tabelle.setPlaceholder(keinErgebnis);
        }
    }


    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

    @FXML
    void KundebearbeitenAction(ActionEvent event) {
        if(Tabelle.getSelectionModel().getSelectedItem() != null) {
            ProfilBearbeitenController.setZuBearbeitenderMensch(Tabelle.getSelectionModel().getSelectedItem());
            MAIN.fensterOeffnen(Views.ProfilBearbeiten);
        }
    }

    @FXML
    void BuchungBearbeitenAction(ActionEvent event) {
        //TODO BuchungBearbeiten
    }

    @FXML
    void BuchungdurchfurenAction(ActionEvent event) {
        //TODO Buchung durchführen
    }

    @FXML
    void KundenanlegenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }

}
