package Controller;

import Model.Enums.Views;
import Model.Exceptions.NutzerDoesNotExistException;
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

public class AngestellterStartseiteController {     //TODO Vorname, Nachname Größen

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


    public void initialize() {
        SpalteVorname.setCellValueFactory(new PropertyValueFactory<Anwender, String>("vorname"));
        SpalteNachname.setCellValueFactory(new PropertyValueFactory<Anwender, String>("nachname"));
        SpalteGeburtsdatum.setCellValueFactory(new PropertyValueFactory<Anwender, String>("geburtsdatum"));
        SpalteEmail.setCellValueFactory(new PropertyValueFactory<Anwender, String>("eMail"));


        try {
            observableList = FXCollections.observableList(Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()));
            if (observableList.size() == 0) {
                System.out.println("Der Anwender hat keine Kunden!");
                Label keineFluege = new Label("Keine Anwender gefunden!");
                keineFluege.setId("keineFluegeGefunden");
                Tabelle.setPlaceholder(keineFluege);
            }
            Tabelle.setItems(observableList);
        } catch (final NutzerDoesNotExistException e) {
            System.out.println("Nutzer does not exist!");
        }
    }


    @FXML
    void KundenSuchenAction(ActionEvent event) {

        ArrayList<Anwender> zutreffendeAnwender = new ArrayList<>();

        if (!VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            //Suche nach Vornamen
            for (int i = 0; i < Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).size(); i++) {
                if (Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i));
                }
            }
        } else if (VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")) {
            //Suche nach Nachnamen
            for (int i = 0; i < Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).size(); i++) {
                if (Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i));
                }
            }
        } else {
            //Suche nach Vornamen und Nachnamen
            for (int i = 0; i < Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).size(); i++) {
                if (Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase()) && zutreffendeAnwender.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeAnwender.add(Verwaltung.getAnwenderByAngestellten((Angestellter)Verwaltung.getAngemeldeter()).get(i));
                }
            }
        }

        if (observableList.size() < 1) {
            observableList.clear();
            Tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnise mit diesen Eigenschaften");
            Label keinErgebniss = new Label("Kein Ergebniss gefunden!");
            keinErgebniss.setId("keinErgebniss");
            Tabelle.setPlaceholder(keinErgebniss);
        } else {
            observableList = FXCollections.observableList(zutreffendeAnwender);
            Tabelle.setItems(observableList);
        }
    }


    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

    @FXML
    void KundebearbeitenAction(ActionEvent event) {
        ProfilBearbeitenController.setZuBearbeitenderMensch(Tabelle.getSelectionModel().getSelectedItem());
        MAIN.fensterOeffnen(Views.ProfilBearbeiten);
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
