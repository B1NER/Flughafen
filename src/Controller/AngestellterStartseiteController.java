package Controller;

import Model.Enums.Views;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AngestellterStartseiteController {

    @FXML
    private Label NachnameText;

    @FXML
    private TableColumn<?, ?> SpalteVorname;

    @FXML
    private TableColumn<?, ?> SpalteEmail;

    @FXML
    private Button KundenanlegenButton;

    @FXML
    private TableColumn<?, ?> SpalteGeburtsdatum;

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
    private TableView<?> Tabelle;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TextField VornameFeld;

    @FXML
    private Button BuchungdurchfurenButten;

    @FXML
    private TableColumn<?, ?> SpalteNachname;


    public void initialize() {
        try {
            Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()); //TODO Tabelle ausfüllen
        } catch (final NutzerDoesNotExistException e) {
            System.out.println("Angestellter hat keine Anwender! Angemeldeter: " + Verwaltung.getAngemeldeter());   //Angestellter hat keine Anwender
        }
    }


    @FXML
    void KundenSuchenAction(ActionEvent event) {
        ArrayList<Anwender> zutreffendeAnwender = new ArrayList<>();
        try {
            if (!VornameFeld.getText().equals("")) {
                if (!VornameFeld.getText().equals("")) {  //Vor- und Nachname
                    for (int i = 0; i < Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).size(); i++) {
                        if (Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).get(i).getVorname().equals(VornameFeld.getText()) && Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).get(i).getNachname().equals(NachnameText.getText())) {
                            zutreffendeAnwender.add(Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).get(i));
                        }
                    }
                } else {  //Vorname
                    for (int i = 0; i < Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).size(); i++) {
                        if (Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).get(i).getVorname().equals(VornameFeld.getText())) {
                            zutreffendeAnwender.add(Verwaltung.getAnwenderByAngestellten(Verwaltung.getAngestelltenByAngemeldeten()).get(i));
                        }
                    }
                }
            } else {
                VornameFeld.setText("Pflichtfeld!");
            }
        } catch (final NutzerDoesNotExistException e) {
            System.out.println("Es gibt keinen Anwender mit diesen Eigenschaften");
            VornameFeld.setText("Keine Übereinstimmung");
            //TODO Tabelle leeren
        }
        //TODO Tabelle mit zutreffendeAnwender füllen
    }

    @FXML
    void AbmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

    @FXML
    void KundebearbeitenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.ZuBearbeitendenNutzerFinden);
    }

    @FXML
    void BuchungdurchfurenAction(ActionEvent event) {

    }

    @FXML
    void KundenanlegenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }

}
