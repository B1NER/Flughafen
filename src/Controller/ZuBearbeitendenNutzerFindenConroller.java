package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ZuBearbeitendenNutzerFindenConroller {

    @FXML
    private Label NachnameText;

    @FXML
    private TableColumn<?, ?> SpalteVorname;

    @FXML
    private TableColumn<?, ?> SpalteGeburtsdatum;

    @FXML
    private Button BearbeitenButton;

    @FXML
    private Label VornameText;

    @FXML
    private TextField VornameFeld;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Button ZuruckButton;

    @FXML
    private TableColumn<?, ?> SpaltEmail;

    @FXML
    private Button LoschenButton;

    @FXML
    private Label NuterbearbeitenText;

    @FXML
    private TableColumn<?, ?> SpalteNachname;

    @FXML
    private Label SuchenText;

    @FXML
    private Button SuchenButton;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }


    @FXML
    void SuchenAction(ActionEvent event) {

    }

    @FXML
    void LoschenAction(ActionEvent event) {

    }

    @FXML
    void BearbeitenAction(ActionEvent event) {

    }

    @FXML
    void ZuruckAction(ActionEvent event) {

    }
}