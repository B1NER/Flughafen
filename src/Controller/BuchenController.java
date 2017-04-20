package Controller;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.omg.CORBA.DATA_CONVERSION;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
        if (Verwaltung.isAngemeldet()) {
            AnmeldenButton.setVisible(false);
            RegistrierenButton.setVisible(false);
        } else {
            AnmeldenButton.setVisible(true);
            RegistrierenButton.setVisible(true);
        }

        DatumHinflug.setEditable(false);
        DatumHinflug2.setEditable(false);

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
        if (Integer.parseInt(AnzahlFeld.getText()) > 1)
            AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) - 1));
    }

    @FXML
    void plusAction(ActionEvent event) {
        AnzahlFeld.setText("" + (Integer.parseInt(AnzahlFeld.getText()) + 1));
    }

    @FXML
    void FlugSuchenAction(ActionEvent event) {

        if(FlugAbFeld.getText().equals("")){
            FlugAbFeld.setPromptText("Stadt eingeben");
        }
        if(FlugAbFeld2.getText().equals("")){
            FlugAbFeld.setPromptText("Stadt eingeben");
        }
        if(FlugNachFeld.getText().equals("")){
            FlugAbFeld.setPromptText("Stadt eingeben");
        }
        if(FlugNachFeld2.getText().equals("")) {
            FlugAbFeld.setPromptText("Stadt eingeben");
        }


        if(DatumRueckflug == null || DatumHinflug == null){
            try {
                ArrayList <Flug> gefundeneFluege = Verwaltung.flugFinden(FlugAbFeld.getText(), FlugNachFeld.getText(), Date.from(DatumHinflug.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));

            }catch (FlugNotFoundException e){

            }

        }else{
            try {
                Verwaltung.flugFinden(FlugAbFeld.getText(), FlugNachFeld.getText(), Date.from(DatumHinflug.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }catch (FlugNotFoundException e){
                e.printStackTrace();
            }
        }



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
