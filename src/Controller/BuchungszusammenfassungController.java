package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BuchungszusammenfassungController {

    @FXML
    private Label ZusammenfassungText;

    private static Buchung buchung;

    public void initialize(){
        if(buchung.getRueckflug() == null) {
            ZusammenfassungText.setText("B1NER- Airlines:\nHinflug: " + buchung.getHinflug().getAbflugort() + " - " + buchung.getHinflug().getAnkunftsort());
        }else {
            //TODO mehrere zeilen schreiben geht nicht
            ZusammenfassungText.setText("B1NER- Airlines:\nHinflug: " + buchung.getHinflug().getAbflugort() + " - " + buchung.getHinflug().getAnkunftsort());
            ZusammenfassungText.setText("\nRückflug: " + buchung.getRueckflug().getAbflugort() + " - " + buchung.getRueckflug().getAnkunftsort());
        }
    }

    public static void setBuchung(Buchung buchung) {
        BuchungszusammenfassungController.buchung = buchung;
    }

    @FXML
    void ZumKundenprofilAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.KundenProfil);
    }

}
