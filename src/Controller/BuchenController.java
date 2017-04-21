package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;

/**
 * Created by knoll on 14.04.2017.
 */
public class BuchenController {

    @FXML
    private Button RegistrierenButton;

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
    private Button MitRuecksflugSuchenButton;

    @FXML
    private Button NurHinflugSuchenButton;

    @FXML
    private TabPane tabPaneMitRueckflug;

    @FXML
    private TabPane tabPaneOhneRueckflug;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
        if (Verwaltung.isAngemeldet()) {
            AnmeldenButton.setVisible(false);
            RegistrierenButton.setVisible(false);
        } else {
            AnmeldenButton.setVisible(true);
            RegistrierenButton.setVisible(true);
        }

        DatumHinflug.setEditable(false);
        DatumHinflug2.setEditable(false);
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }

    @FXML
    void AnmeldenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Anmelden);
    }

    @FXML
    void minusAction(ActionEvent event) {
        if (Integer.parseInt(AnzahlFeld.getText()) > 1)
            AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) - 1));
    }

    @FXML
    void plusAction(ActionEvent event) {
        AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) + 1));
    }

    @FXML
    void FlugSuchenAction(ActionEvent event){

    }

    @FXML
    void NurHinflugFlugSuchenAction(ActionEvent event) {

        //Nur Hinflug
        if (FlugAbFeld2.getText().equals("")) {
            FlugAbFeld2.setPromptText("Stadt eingeben");
        }

        if (FlugNachFeld2.getText().equals("")) {
            FlugAbFeld2.setPromptText("Stadt eingeben");
        }


        MAIN.fensterOeffnen(Views.Flugliste);

        //FluglisteController fc = new FluglisteController();
        //fc.setFluege(FlugAbFeld2.getText(), FlugNachFeld2.getText(), Date.from(DatumHinflug2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Integer.valueOf(NurHinflugAnzahlFeld.getText()));

    }

    @FXML
    void NurHinflugminusAction(ActionEvent event) {
        if (Integer.parseInt(NurHinflugAnzahlFeld.getText()) > 1)
            NurHinflugAnzahlFeld.setText("" + (Integer.parseInt(NurHinflugAnzahlFeld.getText()) - 1));
    }

    @FXML
    void NurHinflugplusAction(ActionEvent event) {
        NurHinflugAnzahlFeld.setText("" + (Integer.parseInt(NurHinflugAnzahlFeld.getText()) + 1));
    }

    @FXML
    void FlugSuchenAction2(ActionEvent event) {

    }
}
