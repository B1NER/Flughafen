package Controller;

import Model.Enums.Views;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;


public class AnmeldenController {

    @FXML
    private PasswordField PasswordFeld;

    @FXML
    private TextField EmailFeld;

    @FXML
    private StackPane stackPane;

    public void initialize() {
        PasswordFeld.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AnmeldenAction(new ActionEvent());
            }
        });
        setMediaPlayer();
    }

    private void setMediaPlayer() {
        final File f = new File("src\\View\\Grafiken\\wolken.mp4");

        StackPane st = (StackPane) stackPane.getChildren().get(0);
        stackPane.getChildren().remove(0);

        final Media m = new Media(f.toURI().toString());
        final MediaPlayer mp = new MediaPlayer(m);
        final javafx.scene.media.MediaView mv = new MediaView(mp);

        final DoubleProperty width = new SimpleDoubleProperty(1280);    //mv.fitWidthProperty();
        final DoubleProperty height = new SimpleDoubleProperty(1024);   //mv.fitHeightProperty();

        width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));

        mv.setPreserveRatio(false);
        mp.setCycleCount(MediaPlayer.INDEFINITE);

        stackPane.getChildren().add(mv);
        stackPane.getChildren().add(st);
        mp.play();

    }

    @FXML
    void AnmeldenAction(ActionEvent event) {
        try {

            Verwaltung.anmelden(EmailFeld.getText(), PasswordFeld.getText());

            if (Verwaltung.getAngemeldeter() instanceof Administrator) {
                MAIN.fensterOeffnen(Views.AdminStartseite);
            } else if (Verwaltung.getAngemeldeter() instanceof Angestellter) {
                MAIN.fensterOeffnen(Views.AngestellterStartseite);
                //Wenn zuletzt die Flugliste offen war, dann gehe zur Zahlung
            } else if (MAIN.viewsChronik.get(MAIN.viewsChronik.size() - 2).equals(Views.Flugliste)) {
                ZahlungController.setAnwender((Anwender) Verwaltung.getAngemeldeter());
                MAIN.fensterOeffnen(Views.Zahlung);
            } else {
                KundenProfilControler.setAnwender((Anwender) Verwaltung.getAngemeldeter());
                MAIN.fensterOeffnen(Views.KundenProfil);
            }

        } catch (NutzerDoesNotExistException e) {
            PasswordFeld.setPromptText("Email oder Passwort falsch!");
            PasswordFeld.setText("");
            EmailFeld.requestFocus();
        }
    }

    @FXML
    void AbbrechenAction(ActionEvent event) {
        MAIN.viewsChronik.pop();
        MAIN.fensterOeffnen(MAIN.viewsChronik.pop());
    }

    @FXML
    void RegistrierenAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.Registrieren);
    }
}
