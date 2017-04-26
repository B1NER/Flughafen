package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

/**
 * Created by knoll on 14.04.2017.
 */

//Verwaltung getestet
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
    private ComboBox<String> FlugNachFeld;
    @FXML
    private TextField NurHinflugAnzahlFeld;
    @FXML
    private TextField FlugAbFeld2;
    @FXML
    private ComboBox<String> FlugAbFeld;
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
                if (tabPaneMitRueckflug.isSelected() && !FlugAbFeld.isFocused() && !FlugAbFeld.isFocused()) {
                    FlugSuchenAction(new ActionEvent());
                } else if (tabPaneOhneRueckflug.isSelected()) {
                    FlugSuchenAction2(new ActionEvent());
                }
            }
        });

        FlugAbFeld.setEditable(true);
        FlugNachFeld.setEditable(true);

        FlugAbFeld.setItems(FXCollections.observableList(Verwaltung.getStaedte()));
        FlugNachFeld.setItems(FXCollections.observableList(Verwaltung.getStaedte()));

        new AutoCompleteComboBoxListener<String>(FlugAbFeld);
        new AutoCompleteComboBoxListener<String>(FlugNachFeld);

        FlugAbFeld.setPromptText("Von");
        FlugNachFeld.setPromptText("Nach");

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
        if (FlugAbFeld.getValue() == null) {
            FlugAbFeld.setPromptText("Von");
        }
        if (FlugNachFeld.getValue() == null) {
            FlugNachFeld.setPromptText("Nach");
        } else {
            mp.stop();
            FluglisteController.setInfos(FlugAbFeld.getValue(), FlugNachFeld.getValue(), "", AnzahlFeld.getText(), DatumHinflug.getValue(), DatumRueckflug.getValue());
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


    public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

        private ComboBox<T> comboBox;
        private ObservableList<T> data;
        private boolean moveCaretToPos = false;
        private int caretPos;

        public AutoCompleteComboBoxListener(final ComboBox<T> comboBox) {
            this.comboBox = comboBox;
            data = comboBox.getItems();

            this.comboBox.setEditable(true);
            this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        }

        @Override
        public void handle(KeyEvent event) {
            if(event.getCode() == KeyCode.UP) {
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                return;
            } else if(event.getCode() == KeyCode.DOWN) {
                if(!comboBox.isShowing())
                    comboBox.show();

                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                return;
            }

            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                    || event.isControlDown() || event.getCode() == KeyCode.HOME
                    || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                return;
            }

            comboBox.hide();

            if(event.getCode() == KeyCode.BACK_SPACE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
            } else if(event.getCode() == KeyCode.DELETE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
            }

            ObservableList<T> list = FXCollections.observableArrayList();
            for (int i=0; i<data.size(); i++) {
                if(data.get(i).toString().toLowerCase().startsWith(
                        AutoCompleteComboBoxListener.this.comboBox
                                .getEditor().getText().toLowerCase())) {
                    list.add(data.get(i));
                }
            }
            String t = comboBox.getEditor().getText();

            comboBox.setItems(list);

            comboBox.getEditor().setText(t);

            if(!moveCaretToPos) {
                caretPos = -1;
            }
            moveCaret(t.length());
            if(!list.isEmpty()) {
                comboBox.show();
            }
        }

        private void moveCaret(int textLength) {
            if(caretPos == -1)
                comboBox.getEditor().positionCaret(textLength);
            else
                comboBox.getEditor().positionCaret(caretPos);

            moveCaretToPos = false;
        }

    }



}
