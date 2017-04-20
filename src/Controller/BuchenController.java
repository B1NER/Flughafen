package Controller;

import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaView;

/**
 * Created by knoll on 14.04.2017.
 */
public class BuchenController {

    @FXML
    private Button RegistrierenButton;

    @FXML
    private TextField FlugAbFeld;

    @FXML
    private DatePicker DatumRueckflug;

    @FXML
    private Button AnmeldenButton;

    @FXML
    private Label FlugsuchenText;

    @FXML
    private TextField FlugNachFeld2;

    @FXML
    private Button minusButton;

    @FXML
    private DatePicker DatumHinflug2;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private Button NurHinflugminusButton;

    @FXML
    private TextField AnzahlFeld;

    @FXML
    private TextField FlugNachFeld;

    @FXML
    private TextField NurHinflugAnzahlFeld;

    @FXML
    private javafx.scene.media.MediaView MediaView;

    @FXML
    private Button plusButton;

    @FXML
    private Button FlugSuchenButton2;

    @FXML
    private Button NurHinflugplusButton;

    @FXML
    private TextField FlugAbFeld2;

    @FXML
    private Button FlugSuchenButton;


    public MAIN main;

    public void setMain(MAIN main) {
        if(Verwaltung.isAngemeldet()){
            AnmeldenButton.setVisible(false);
            RegistrierenButton.setVisible(false);
        }
        this.main = main;
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {
        main.registrieren();
    }

    @FXML
    void AnmeldenAction(ActionEvent event) {
        main.anmelden();
    }

    @FXML
    void minusAction(ActionEvent event) {
        AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) - 1));
    }

    @FXML
    void plusAction(ActionEvent event) {
        AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) + 1));
    }

    @FXML
    void FlugSuchenAction(ActionEvent event) {

    }

    @FXML
    void NurHinflugminusAction(ActionEvent event) {

    }

    @FXML
    void NurHinflugplusAction(ActionEvent event) {

    }

    @FXML
    void FlugSuchenAction2(ActionEvent event) {

    }
}
