package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GepaeckBearbeitenController {

    @FXML
    private Button BestatigenButton;

    @FXML
    private TextField GewichtFeld;

    @FXML
    private Label GepackbearbeitenText;

    @FXML
    private Button AbbrechenButton;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {

    }

    @FXML
    void BestatigenAction(ActionEvent event) {

    }

}
