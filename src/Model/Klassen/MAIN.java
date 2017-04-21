package Model.Klassen;

import Controller.*;
import Model.Enums.Views;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Angestellte;
import Model.Klassen.Verwaltung.Anwenders;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by knoll on 21.03.2017.
 */
public class MAIN extends Application {

    static private Stage primaryStage;
    static private HashMap<Views, String> viewPfad = new HashMap<>();


    public void start(Stage primaryStage) {
        MAIN.primaryStage = primaryStage;
        hashmapsFuellen();
        MAIN.fensterOeffnen(Views.Buchen);
    }

    public static void main(String[] args) {
        //Verwaltung.init();
        launch(args);
        //Verwaltung.exit();
    }


    public void hashmapsFuellen() {
        viewPfad.put(Views.AdminStartseite, "/View/AdminStartseite.fxml");
        viewPfad.put(Views.AngestellterStartseite, "/View/AngestellterStartseite.fxml");
        viewPfad.put(Views.Anmelden, "/View/Anmelden.fxml");
        viewPfad.put(Views.Buchen, "/View/Buchen.fxml");
        viewPfad.put(Views.BuchungBearbeiten, "/View/BuchungBearbeiten.fxml");
        viewPfad.put(Views.Buchungszusammenfassung, "/View/Buchungszusammenfassung.fxml");
        viewPfad.put(Views.Flugliste, "/View/Flugliste.fxml");
        viewPfad.put(Views.GepaeckBearbeiten, "/View/GepaeckBearbeiten.fxml");
        viewPfad.put(Views.KundenProfil, "/View/KundenProfil.fxml");
        viewPfad.put(Views.ProfilBearbeiten, "/View/ProfilBearbeiten.fxml");
        viewPfad.put(Views.Registrieren, "/View/Registrieren.fxml");
        viewPfad.put(Views.Zahlung, "/View/Zahlung.fxml");
        viewPfad.put(Views.ZuBearbeitendeBuchungFinden, "/View/ZuBearbeitendeBuchungFinden.fxml");
        viewPfad.put(Views.ZuBearbeitendenNutzerFinden, "/View/ZuBearbeitendenNutzerFinden.fxml");
    }


    public static void fensterOeffnen(Views view) {
        try {

            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource(viewPfad.get(view)));
            Pane pane = loader.load();

            if (view.toString().equals("AdminStartseiteController")) {
                AdminStartseiteController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("AngestellterStartseiteController")) {
                AngestellterStartseiteController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("AnmeldenController")) {
                AnmeldenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("BuchenController")) {
                BuchenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("BuchungBearbeitenController")) {
                BuchungBearbeitenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("BuchungszusammenfassungController")) {
                BuchungszusammenfassungController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("FluglisteController")) {
                FluglisteController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("GepaeckBearbeitenController")) {
                GepaeckBearbeitenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("KundenProfilControler")) {
                KundenProfilControler controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("ProfilBearbeitenController")) {
                ProfilBearbeitenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("RegistrierenController")) {
                RegistrierenController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("ZahlungController")) {
                ZahlungController controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("ZuBearbeitendeBuchungFindenConroller")) {
                ZuBearbeitendeBuchungFindenConroller controller = loader.getController();
                //controller.setMain(main);
            } else if (view.toString().equals("ZuBearbeitendenNutzerFindenConroller")) {
                ZuBearbeitendenNutzerFindenConroller controller = loader.getController();
                //controller.setMain(main);
            }

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }
    }

}