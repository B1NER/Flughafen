package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

/**
 * Created by knoll on 14.04.2017.
 */
public class BuchenController {

    private final Media m = new Media(new File("src\\View\\Grafiken\\wolken.mp4").toURI().toString());
    private final MediaPlayer mp = new MediaPlayer(m);
    @FXML
    private Button RegistrierenButton;
    @FXML
    private DatePicker DatumRueckflug;
    @FXML
    private Button AnmeldenButton;
    @FXML
    private TextField FlugNachFeld2;
    @FXML
    private DatePicker DatumHinflug2;
    @FXML
    private DatePicker DatumHinflug;
    @FXML
    private TextField AnzahlFeld;
    @FXML
    private TextField FlugNachFeld;
    @FXML
    private TextField NurHinflugAnzahlFeld;
    @FXML
    private TextField FlugAbFeld2;
    @FXML
    private TextField FlugAbFeld;
    @FXML
    private Tab tabPaneMitRueckflug;
    @FXML
    private Tab tabPaneOhneRueckflug;
    @FXML
    private Button MeinProfilButton;
    @FXML
    private StackPane stackPane;

    public void initialize() {
        setMediaPlayer();

        AnzahlFeld.setDisable(true);
        NurHinflugAnzahlFeld.setDisable(true);

        if (Verwaltung.isAngemeldet()) {
            AnmeldenButton.setVisible(false);
            RegistrierenButton.setVisible(false);
            MeinProfilButton.setVisible(true);

        } else {
            AnmeldenButton.setVisible(true);
            RegistrierenButton.setVisible(true);
            MeinProfilButton.setVisible(false);
        }
        DatumHinflug.setEditable(false);
        DatumHinflug2.setEditable(false);

        stackPane.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (tabPaneMitRueckflug.isSelected()) {
                    FlugSuchenAction(new ActionEvent());
                } else if (tabPaneOhneRueckflug.isSelected()) {
                    FlugSuchenAction2(new ActionEvent());
                }
            }
        });
    }

    private void setMediaPlayer() {

        VBox vBox = (VBox) stackPane.getChildren().get(0);
        stackPane.getChildren().remove(0);

        final javafx.scene.media.MediaView mv = new MediaView(mp);
        mv.setPreserveRatio(false);
        mp.setCycleCount(MediaPlayer.INDEFINITE);

        stackPane.getChildren().add(mv);
        stackPane.getChildren().add(vBox);

        mp.play();
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {
        mp.stop();
        MAIN.fensterOeffnen(Views.Registrieren);
    }

    @FXML
    void AnmeldenAction(ActionEvent event) {
        mp.stop();
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
    void FlugSuchenAction(ActionEvent event) {
        if (FlugAbFeld.getText().equals("")) {
            FlugAbFeld.setPromptText("Stadt eingeben");
        }
        if (FlugNachFeld.getText().equals("")) {
            FlugNachFeld.setPromptText("Stadt eingeben");
        } else {
            mp.stop();
            FluglisteController.setInfos(FlugAbFeld.getText(), FlugNachFeld.getText(), "", AnzahlFeld.getText(), DatumHinflug.getValue(), DatumRueckflug.getValue());
            MAIN.fensterOeffnen(Views.Flugliste);
        }
    }

    @FXML
    void FlugSuchenAction2(ActionEvent event) {
        if (FlugAbFeld2.getText().equals("")) {
            FlugAbFeld2.setPromptText("Stadt eingeben");
        }
        if (FlugNachFeld2.getText().equals("")) {
            FlugNachFeld2.setPromptText("Stadt eingeben");
        } else {
            mp.stop();
            FluglisteController.setInfos(FlugAbFeld2.getText(), FlugNachFeld2.getText(), "", NurHinflugAnzahlFeld.getText(), DatumHinflug2.getValue());
            MAIN.fensterOeffnen(Views.Flugliste);
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
    void MeinProfilAction(ActionEvent event) {
        mp.stop();
        if (Verwaltung.getAngemeldeter() instanceof Anwender) {
            MAIN.fensterOeffnen(Views.KundenProfil);
        } else if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        } else {
            MAIN.fensterOeffnen(Views.AdminStartseite);
        }
    }
}
