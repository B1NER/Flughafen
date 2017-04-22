package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import javax.swing.text.View;

public class AdminStartseiteController {

    @FXML
    private TableColumn<?, ?> SpalteStart;

    @FXML
    private TableColumn<?, ?> SpalteStatus;

    @FXML
    private Button AngestelltehinzufugenButton;

    @FXML
    private Label FlugListeText;

    @FXML
    private Button AnmeldenButton;

    @FXML
    private TableColumn<?, ?> SpalteStartOrt;

    @FXML
    private TableColumn<?, ?> SpalteZielOrt;

    @FXML
    private Button BenutzerBearbeitenButton;

    @FXML
    private VBox AdministartorText;

    @FXML
    private TableView<?> tabelle;

    @FXML
    private TableColumn<?, ?> SpalteAnkunft;

    @FXML
    private Button BuchungBearbeitenButton;

    @FXML
    private TableColumn<?, ?> SpalteFlugID;

    public void initialize() {
    }


    @FXML
    void AngestelltehinzufugenAction(ActionEvent event) {
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
    void AnmeldenAction(ActionEvent event) {
        Verwaltung.setAngemeldeter(null);
        MAIN.fensterOeffnen(Views.Buchen);
    }

}
