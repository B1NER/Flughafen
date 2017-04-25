package Model.Klassen;

import Model.Enums.Views;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by knoll on 21.03.2017.
 */

//TODO Größen anpassen
//TODO preis in zahlungscontroller bugt
//TODO Großklein schreibung

public class MAIN extends Application {

    static private Stage primaryStage;
    static private HashMap<Views, String> viewPfad = new HashMap<>();

    //bei jedem neuem Fensteröffnen muss das fenster in diese Chronik eingetragen werden!
    static public Stack<Views> viewsChronik = new Stack<>();


    public void start(Stage primaryStage) {
        MAIN.primaryStage = primaryStage;
        //primaryStage.setResizable(false);
        hashmapsFuellen();
        /*try {
            //Verwaltung.anmelden(Administratoren.getAdministratorByID(1));
            Verwaltung.anmelden(Angestellte.getAngestelltenByID(1));
        }catch (final Exception e){

        }

        //MAIN.fensterOeffnen(Views.AdminStartseite);
        MAIN.fensterOeffnen(Views.AngestellterStartseite);*/

        MAIN.fensterOeffnen(Views.Buchen);
    }

    public static void main(String[] args) {
        Verwaltung.init();
        launch(args);
        Verwaltung.exit();
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

            viewsChronik.push(view);

            Pane pane = loader.load();
            Scene scene = new Scene(pane);

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            primaryStage.setFullScreen(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }
    }
}