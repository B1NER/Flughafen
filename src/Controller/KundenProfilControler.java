package Controller;

import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

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
    private Label AbgelaufenText;

    @FXML
    private Button AbmeldenButton;

    @FXML
    private TableColumn<?, ?> SpalteRuckflug;

    @FXML
    private TableColumn<?, ?> SpalteHinflug;

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

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }


    @FXML
    void NeueBuchungAction(ActionEvent event) {

    }

    @FXML
    void GepackbearbeitenAction(ActionEvent event) {

    }

    @FXML
    void ProfilBearbeitenAction(ActionEvent event) {

    }

    @FXML
    void AbmeldenAction(ActionEvent event) {

    }

}
