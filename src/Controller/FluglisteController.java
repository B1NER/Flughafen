package Controller;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class FluglisteController {  //TODO Felder auf die Suchkriterien setzen

    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField FlugnachFeld;

    @FXML
    private TableColumn<?, ?> SpalteFlug;

    @FXML
    private TextField DazumHinflugFeld; //TODO Date und nicht Textfield

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
        try {
            if (FlugabFeld.getText().equals("") && FlugnachFeld.getText().equals("")) {
                FlugabFeld.setText("Pflichtfeld!");
                FlugnachFeld.setText("Pflichtfeld");

            } else {
                if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    Verwaltung.flugFinden(FlugabFeld.getText(), FlugnachFeld.getText());
                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugabFeld.getText().equals("") && !FlugnachFeld.getText().equals("")) {
                    //TODO wenn Fluggesellschaft geadded wurde
                }
            }
        } catch (final FlugNotFoundException e) {
            System.out.println("Flug wurde nicht gefunden");
        }
    }

}
