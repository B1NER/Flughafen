package Controller;

import Model.Enums.Views;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BuchungszusammenfassungController {

    @FXML
    private Label ZusammenfassungText;

    private static Buchung buchung;

    public void initialize(){
        ZusammenfassungText.setWrapText(true);
        if(buchung.getRueckflug() == null) {
            ZusammenfassungText.setText("Sie fliegen mit: " + buchung.getHinflug().getFlugGesellschaft() + " Airlines\nHinflug: " + buchung.getHinflug().getAbflugort() + " - " + buchung.getHinflug().getAnkunftsort() + "\nPreis: " + buchung.getBuchungspreis() + "€");
        }else {
            ZusammenfassungText.setText("Sie fliegen mit: " + buchung.getHinflug().getFlugGesellschaft()+ " Airlines\nHinflug: " + buchung.getHinflug().getAbflugort() + " - " + buchung.getHinflug().getAnkunftsort() + "\nSie fliegen mit: " + buchung.getRueckflug().getFlugGesellschaft() + " Airlines\nRückflug: " + buchung.getRueckflug().getAbflugort() + " - " + buchung.getRueckflug().getAnkunftsort() + "\nPreis: " + buchung.getBuchungspreis() + "€");
        }
    }

    public static void setBuchung(Buchung buchung) {
        BuchungszusammenfassungController.buchung = buchung;
    }

    @FXML
    void ZumKundenprofilAction(ActionEvent event) {
        if(Verwaltung.getAngemeldeter() instanceof Anwender) {
            KundenProfilControler.setAnwender((Anwender) Verwaltung.getAngemeldeter());
            MAIN.fensterOeffnen(Views.KundenProfil);
        }else if(Verwaltung.getAngemeldeter() instanceof Angestellter){
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        }else if(Verwaltung.getAngemeldeter() instanceof Administrator){
            MAIN.fensterOeffnen(Views.AdminStartseite);
        }

    }

}
