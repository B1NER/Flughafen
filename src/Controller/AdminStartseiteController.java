package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

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

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }


    @FXML
    void AngestelltehinzufugenAction(ActionEvent event) {

    }

    @FXML
    void BenutzerBearbeitenAction(ActionEvent event) {

    }

    @FXML
    void BuchungBearbeitenAction(ActionEvent event) {

    }

    @FXML
    void AnmeldenAction(ActionEvent event) {

    }
}
