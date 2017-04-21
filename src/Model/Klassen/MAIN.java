package Model.Klassen;

import Controller.*;
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

/**
 * Created by knöll on 21.03.2017.
 */
public class MAIN extends Application {

    static private Stage primaryStage;


    public void start(Stage primaryStage) {
        MAIN.primaryStage = primaryStage;
        buchen();
    }

    public static void main(String[] args) {
        Verwaltung.init();
        launch(args);
        Verwaltung.exit();

    }


    public void adminStartseite() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/AdminStartseite.fxml"));
            Pane pane = loader.load();

            AdminStartseiteController adminStartseiteController = loader.getController();
            adminStartseiteController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void angestellterStartseite() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/AngestellterStartseite.fxml"));
            Pane pane = loader.load();

            AngestellterStartseiteController angestellterStartseiteController = loader.getController();
            angestellterStartseiteController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void anmelden() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Anmelden.fxml"));
            Pane pane = loader.load();

            AnmeldenController anmeldenController = loader.getController();
            anmeldenController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void buchen() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Buchen.fxml"));
            Pane pane = loader.load();

            BuchenController buchenController = loader.getController();
            buchenController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void buchungBearbeiten() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/BuchungBearbeiten.fxml"));
            Pane pane = loader.load();

            BuchungBearbeitenController buchungBearbeitenController = loader.getController();
            buchungBearbeitenController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void buchungszusammenfassung() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Buchungszusammenfassung.fxml"));
            Pane pane = loader.load();

            BuchungszusammenfassungController buchungszusammenfassungController = loader.getController();
            buchungszusammenfassungController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void flugliste() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Flugliste.fxml"));
            Pane pane = loader.load();

            FluglisteController fluglisteController = loader.getController();
            fluglisteController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void gepaeckBearbeiten() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/GepaeckBearbeiten.fxml"));
            Pane pane = loader.load();

            GepaeckBearbeitenController gepaeckBearbeitenController = loader.getController();
            gepaeckBearbeitenController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void kundenProfil() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/KundenProfil.fxml"));
            Pane pane = loader.load();

            KundenProfilControler kundenProfilControler = loader.getController();
            kundenProfilControler.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void profilBearbeiten() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/ProfilBearbeiten.fxml"));
            Pane pane = loader.load();

            ProfilBearbeitenController profilBearbeitenController = loader.getController();
            profilBearbeitenController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void registrieren() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Registrieren.fxml"));
            Pane pane = loader.load();

            RegistrierenController registrierenController = loader.getController();
            registrierenController.setMain(this);

            Scene scene = new Scene(pane);


            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setFullScreen(true);
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void zahlung() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/Zahlung.fxml"));
            Pane pane = loader.load();

            ZahlungController zahlungController = loader.getController();
            zahlungController.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void zuBearbeitendeBuchungFinden() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/ZuBearbeitendeBuchungFinden.fxml"));
            Pane pane = loader.load();

            ZuBearbeitendeBuchungFindenConroller zuBearbeitendeBuchungFindenConroller = loader.getController();
            zuBearbeitendeBuchungFindenConroller.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }

    public void zuBearbeitendenNutzerFinden() {
        try {
            FXMLLoader loader = new FXMLLoader(MAIN.class.getResource("/View/ZuBearbeitendenNutzerFinden.fxml"));
            Pane pane = loader.load();

            ZuBearbeitendenNutzerFindenConroller zuBearbeitendenNutzerFindenConroller = loader.getController();
            zuBearbeitendenNutzerFindenConroller.setMain(this);

            Scene scene = new Scene(pane);

            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (final java.io.IOException e) {
            System.out.println("Exception");
        }
    }
}