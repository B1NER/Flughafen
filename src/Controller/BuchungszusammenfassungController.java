package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BuchungszusammenfassungController {

    @FXML
    private Label ZusammenfassungText;

    @FXML
    void ZumKundenprofilAction(ActionEvent event) {
        MAIN.fensterOeffnen(Views.KundenProfil);
    }

}
