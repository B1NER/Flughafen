package Controller;

import Model.Enums.Views;
import Model.Exceptions.BuchungDoesNotExistException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KundenProfilControler {

    @FXML
    private Label RuckflugFeld;

    @FXML
    private Label GeburtsdatumText;

    @FXML
    private Label StartortText;

    @FXML
    private Label SitzplatzeFeld;

    @FXML
    private Label VornameText;

    @FXML
    private Label StartDatumText;

    @FXML
    private Label GepackText;

    @FXML
    private Label VornameFeld;

    @FXML
    private Label StartDatumFeld;

    @FXML
    private Label AbgelaufenText;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TableColumn<Buchung, Flug> SpalteHinflug;

    @FXML
    private TableColumn<Buchung, Flug> SpalteRueckflug;

    @FXML
    private Label AnkunftsDatumFeld;

    @FXML
    private Label AnkunftsDatumText;

    @FXML
    private Label PreisText;

    @FXML
    private Button GepackbearbeitenButton;

    @FXML
    private Label NachnameText;

    @FXML
    private Label AbgelaufenFeld;

    @FXML
    private Label ZielortText;

    @FXML
    private Label PreisFeld;

    @FXML
    private Label SitzplatzeText;

    @FXML
    private Button NeueBuchungButton;

    @FXML
    private Label StartortFeld;

    @FXML
    private Label NachnameFeld;

    @FXML
    private TableView<Buchung> tableBuchungen;


    @FXML
    private Label RuckflugText;

    @FXML
    private Label BuchungenText;

    @FXML
    private Button ProfilBearbeitenButton;

    @FXML
    private Label GeburtsdatumFeld;

    @FXML
    private Label ZielortFeld;

    @FXML
    private Label KundenProfilText;

    @FXML
    private Label HinflugFeld;

    @FXML
    private Label GepackFeld;

    @FXML
    private Label HinflugText;

    private static Buchung buchung;

    public static void setBuchung(Buchung buchung) {
        KundenProfilControler.buchung = buchung;
    }

    @FXML
    public void initialize() {
        try {
            buchung = Buchungen.getBuchungByID(1);      //todo Buchung setzten indem man von liste auswählt
        }catch(BuchungDoesNotExistException e){         // in dem fall nur zum TEst
            e.printStackTrace();
        }

        VornameFeld.setText(Verwaltung.getAngemeldeter().getVorname());
        NachnameFeld.setText(Verwaltung.getAngemeldeter().getNachname());
        GeburtsdatumFeld.setText(Verwaltung.getAngemeldeter().getGeburtsdatum());

        SpalteHinflug.setCellValueFactory(new PropertyValueFactory<Buchung, Flug>("hinflug"));
        SpalteRueckflug.setCellValueFactory(new PropertyValueFactory<Buchung, Flug>("rueckflug"));

        ArrayList<Buchung> meineBuchungen = Verwaltung.getBuchungenByAnwender((Anwender)Verwaltung.getAngemeldeter());
        ObservableList<Buchung> observableList = FXCollections.observableList(meineBuchungen);

        tableBuchungen.setItems(observableList);

        tableBuchungen.setRowFactory( tv -> {
            TableRow<Buchung> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    HinflugFeld.setText("");
                    GepackFeld.setText("");
                    PreisFeld.setText("");
                    RuckflugFeld.setText("");
                    SitzplatzeFeld.setText("");


                    Buchung rowData = row.getItem();
                    HinflugFeld.setText(rowData.getHinflug().getAbflugort() + " - " + rowData.getHinflug().getAnkunftsort());
                    if(Buchungen.isRueckflug(rowData)){
                        RuckflugFeld.setText(rowData.getRueckflug().getAbflugort() + " - " + rowData.getRueckflug().getAnkunftsort());
                    }
                    //StartDatumFeld.setText(rowData.get);
                    GepackFeld.setText(rowData.getGepaeck().getGewicht() + " - " + rowData.getGepaeck().getGepaeckTyp().toString());
                    PreisFeld.setText(String.valueOf(rowData.getBuchungspreis()));
                    SitzplatzeFeld.setText(String.valueOf(rowData.getAnzahlSitzplaetze()));
                }
            });
            return row ;
        });
    }

    @FXML
    void NeueBuchungAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Flugliste);
    }

    @FXML
    void GepackbearbeitenAction(ActionEvent event) {
        //GepaeckBearbeitenController.setGepaeck(buchung.getGepaeck());
        MAIN.fensterOeffnen(Views.GepaeckBearbeiten);
    }

    @FXML
    void ProfilBearbeitenAction(ActionEvent event) {
        ProfilBearbeitenController.setZuBearbeitenderMensch(Verwaltung.getAngemeldeter());
        MAIN.fensterOeffnen(Views.ProfilBearbeiten);
    }

    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

}
