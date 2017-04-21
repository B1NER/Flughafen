package Controller;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.MAIN;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;

public class FluglisteController {  //TODO Felder auf die Suchkriterien setzen


    @FXML
    private Label IhreSucheText;

    @FXML
    private TextField FlugNachFeld;

    @FXML
    private TextField fluggesellschaft;

    @FXML
    private TableColumn<?, ?> SpalteFlug;

    @FXML
    private TableColumn<?, ?> SpaltePreis;

    @FXML
    private DatePicker DatumHinflug;

    @FXML
    private DatePicker DatumRueckflug;

    @FXML
    private TextField anzahlPersonen;

    @FXML
    private TextField anzahlPersonen2;

    @FXML
    private Label FlugauswahlText;

    @FXML
    private TextField DazumRuckflugFeld;

    @FXML
    private TextField FlugAbTextFeld;

    @FXML
    private Button FlugfindenButton;

    public MAIN main;

    public void setMain(MAIN main) {
        this.main = main;
    }

    @FXML
    public void FlugfindenAction(ActionEvent event) {
        /*try {
            if (FlugAbFeld.getText().equals("") && FlugNachFeld.getText().equals("")) {
                FlugAbFeld.setText("Pflichtfeld!");
                FlugNachFeld.setText("Pflichtfeld");

            } else {
                if (!FlugAbFeld.getText().equals("") && !FlugNachFeld.getText().equals("")) {
                    Verwaltung.flugFinden(FlugAbFeld.getText(), FlugNachFeld.getText(), Integer.parseInt(anzahlPersonen.getText()));
                } else if (!FlugAbFeld.getText().equals("") && !FlugNachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugAbFeld.getText().equals("") && !FlugNachFeld.getText().equals("")) {
                    //TODO wenn Date auf Date gesetzt wurde
                } else if (!FlugAbFeld.getText().equals("") && !FlugNachFeld.getText().equals("")) {
                    //TODO wenn Fluggesellschaft geadded wurde
                }
            }
        } catch (final FlugNotFoundException e) {
            System.out.println("Flug wurde nicht gefunden");
        }*/
    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, int personen){
        FlugAbTextFeld.setText(flugAb);
        FlugNachFeld.setText(flugNach);
        DatumHinflug.setValue(datumHinflug.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        anzahlPersonen.setText("" + personen);
    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, String fluggesellschaft){


    }

    public void setFluege(String flugAb, String flugNach, Date datumHinflug, Date datumRueckflug, String fluggesellschaft){


    }


}
