package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TextField VornahmeFeld;

    @FXML
    private Button BuchungdurchfurenButten;

    @FXML
    private TableColumn<?, ?> SpalteNachname;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }


    @FXML
    void KundenSuchenAction(ActionEvent event) {

    }

    @FXML
    void AbmeldenAction(ActionEvent event) {

    }

    @FXML
    void KundebearbeitenAction(ActionEvent event) {

    }

    @FXML
    void BuchungdurchfurenAction(ActionEvent event) {

    }

    @FXML
    void KundenanlegenAction(ActionEvent event) {

    }

}
