package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;
import Model.Klassen.Verwaltung.Fluege;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KundenProfilControler {

    private static Anwender anwender;
    @FXML
    private Label VornameFeld;
    @FXML
    private Label NachnameFeld;
    @FXML
    private TableView<Buchung> tableBuchungen;
    @FXML
    private TableColumn<Buchung, Flug> SpalteHinflug;
    @FXML
    private TableColumn<Buchung, Flug> SpalteRueckflug;
    @FXML
    private Label HinflugFeld;
    @FXML
    private Label StartDatumFeld;
    @FXML
    private Label AnkunftsDatumFeld;
    @FXML
    private Label RuckflugFeld;
    @FXML
    private Label StartDatumFeld1;
    @FXML
    private Label AnkunftsDatumFeld1;
    @FXML
    private Label GepackFeld;
    @FXML
    private Label PreisFeld;
    @FXML
    private Label SitzplaetzeFeld;
    @FXML
    private Label AbgelaufenFeld;
    @FXML
    private Button AbmeldenButton;

    public static void setAnwender(Anwender anwender) {
        KundenProfilControler.anwender = anwender;
    }

    @FXML
    public void initialize() {
        if (Verwaltung.getAngemeldeter() instanceof Administrator || Verwaltung.getAngemeldeter() instanceof Angestellter) {
            AbmeldenButton.setText("Zurück");
        } else {
            AbmeldenButton.setText("Abmelden");
        }
        VornameFeld.setText(anwender.getVorname());
        NachnameFeld.setText(anwender.getNachname());

        SpalteHinflug.setCellValueFactory(new PropertyValueFactory<Buchung, Flug>("hinflug"));
        SpalteRueckflug.setCellValueFactory(new PropertyValueFactory<Buchung, Flug>("rueckflug"));

        ArrayList<Buchung> meineBuchungen = Verwaltung.getBuchungenByAnwender(anwender);
        ObservableList<Buchung> observableList = FXCollections.observableList(meineBuchungen);

        tableBuchungen.setItems(observableList);

        tableBuchungen.setRowFactory(tv -> {
            TableRow<Buchung> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    HinflugFeld.setText("");
                    StartDatumFeld.setText("");
                    AnkunftsDatumFeld.setText("");
                    RuckflugFeld.setText("");
                    StartDatumFeld1.setText("");
                    AnkunftsDatumFeld1.setText("");
                    GepackFeld.setText("");
                    PreisFeld.setText("");
                    SitzplaetzeFeld.setText("");
                    AbgelaufenFeld.setText("");

                    Buchung rowData = row.getItem();
                    HinflugFeld.setText(rowData.getHinflug().toString());
                    StartDatumFeld.setText(rowData.getHinflug().toStringAbflugzeit());
                    AnkunftsDatumFeld.setText(rowData.getHinflug().toStringAnkunftszeit());
                    GepackFeld.setText(rowData.getGepaeck().toString());
                    PreisFeld.setText(String.valueOf(rowData.getBuchungspreis()));
                    SitzplaetzeFeld.setText(String.valueOf(rowData.getAnzahlSitzplaetze()));

                    if (Fluege.isVerfallen(rowData.getHinflug())) {
                        AbgelaufenFeld.setText("Abgelaufen");
                    } else {
                        AbgelaufenFeld.setText("Aktuell");
                    }

                    if (Buchungen.isRueckflug(rowData)) {
                        RuckflugFeld.setText(rowData.getRueckflug().toString());
                        StartDatumFeld1.setText(rowData.getHinflug().toStringAbflugzeit());
                        AnkunftsDatumFeld1.setText(rowData.getHinflug().toStringAnkunftszeit());
                        if (Fluege.isVerfallen(rowData.getHinflug())) {
                            AbgelaufenFeld.setText("Abgelaufen");
                        } else {
                            AbgelaufenFeld.setText("Aktuell");
                        }
                    }
                }
            });
            return row;
        });
        if (observableList.size() < 1) {
            observableList.clear();
            tableBuchungen.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnisse mit diesen Eigenschaften");

            Label keinErgebnis = new Label("Kein Ergebnis gefunden!");
            keinErgebnis.setId("keinErgebnis");
            tableBuchungen.setPlaceholder(keinErgebnis);
        }
    }

    @FXML
    void NeueBuchungAction(ActionEvent event) {
        FluglisteController.setAnwender(anwender);
        MAIN.fensterOeffnen(Views.Buchen);
    }

    @FXML
    void BuchungBearbeitenAction(ActionEvent event) {
        if (tableBuchungen.getSelectionModel().getSelectedItem() != null) {
            if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
                if (tableBuchungen.getSelectionModel().getSelectedItem().isCreatedByAnwender()) {
                    return;
                    //TODO Evtl LAbel das sagt, dass man nicht die berechtigung hat
                }
            }
            if (!(Verwaltung.getAngemeldeter() instanceof Administrator)) {
                GepaeckBearbeitenController.setGepaeck(tableBuchungen.getSelectionModel().getSelectedItem().getGepaeck(), (int) tableBuchungen.getSelectionModel().getSelectedItem().getGepaeck().getGewicht());
                MAIN.fensterOeffnen(Views.GepaeckBearbeiten);
            } else {
                BuchungBearbeitenController.setBuchung(tableBuchungen.getSelectionModel().getSelectedItem());
                MAIN.fensterOeffnen(Views.BuchungBearbeiten);
            }
        }
    }

    @FXML
    void ProfilBearbeitenAction(ActionEvent event) {
        ProfilBearbeitenController.setZuBearbeitenderMensch(anwender);
        MAIN.fensterOeffnen(Views.ProfilBearbeiten);
    }

    @FXML
    void AbmeldenAction(ActionEvent event) {
        if (Verwaltung.getAngemeldeter() instanceof Administrator) {
            MAIN.fensterOeffnen(Views.AdminStartseite);
        } else if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        } else {
            Verwaltung.setAngemeldeter(null);
            MAIN.fensterOeffnen(Views.Buchen);
        }
    }
}
