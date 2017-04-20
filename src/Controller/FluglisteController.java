package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class FluglisteController {

    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField FlugnachFeld;

    @FXML
    private TableColumn<?, ?> SpalteFlug;

    @FXML
    private TextField DazumHinflugFeld;

    @FXML
    private TextField PersonenanzahlFeld;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField DazumRuckflugFeld;

    @FXML
    private TableColumn<?, ?> SpaltePreis;

    @FXML
    private TextField FlugabFeld;

    @FXML
    private Button FlugfindenButton;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }

    @FXML
    void FlugfindenAction(ActionEvent event) {

    }

}
