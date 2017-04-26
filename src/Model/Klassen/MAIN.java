package Model.Klassen;

import Model.Enums.Views;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by knoll on 21.03.2017.
 */

//TODO Skalieren: BuchungBearbeiten, Anmelden, Kundenprofil

public class MAIN extends Application {

    //bei jedem neuem Fensteröffnen muss das fenster in diese Chronik eingetragen werden!
    static public Stack<Views> viewsChronik = new Stack<>();
    static private Stage primaryStage;
    static private HashMap<Views, String> viewPfad = new HashMap<>();

    public static void main(String[] args) {
        Verwaltung.init();
        launch(args);
        Verwaltung.exit();
    }

    public static void fensterOeffnen(Views view) {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource(viewPfad.get(view)));

            viewsChronik.push(view);

            Pane pane = loader.load();
            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void start(Stage primaryStage) {

        MAIN.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image("View/Grafiken/b1ner_icon.png"));
        hashmapsFuellen();
        MAIN.fensterOeffnen(Views.Buchen);
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
}