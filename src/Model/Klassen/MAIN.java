package Model.Klassen;

import Controller.AnmeldenController;
import Controller.BuchenController;
import Controller.RegistrierenController;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by knoll on 21.03.2017.
 */
public class MAIN extends Application {

    static private Stage primaryStage;

    public void start(Stage primaryStage) {
        MAIN.primaryStage = primaryStage;
        buchen();
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

    public static void main(String[] args) {
        Verwaltung.init();
        launch(args);
        Verwaltung.exit();
    }
}