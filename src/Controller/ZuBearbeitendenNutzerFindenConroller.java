package Controller;

import Model.Klassen.Nutzer.Mensch;
import Model.Klassen.Verwaltung.Administratoren;
import Model.Klassen.Verwaltung.Angestellte;
import Model.Klassen.Verwaltung.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ZuBearbeitendenNutzerFindenConroller {

    @FXML
    private Label NachnameText;

    @FXML
    private TableColumn<Mensch, String> SpalteVorname;

    @FXML
    private TableColumn<Mensch, String> SpalteGeburtsdatum;

    @FXML
    private Button BearbeitenButton;

    @FXML
    private Label VornameText;

    @FXML
    private TextField VornameFeld;

    @FXML
    private TextField NachnameFeld;

    @FXML
    private Button ZuruckButton;

    @FXML
    private TableColumn<Mensch, String> SpaltEmail;

    @FXML
    private TableView<Mensch> tabelle;

    @FXML
    private Button LoschenButton;

    @FXML
    private Label NuterbearbeitenText;

    @FXML
    private TableColumn<Mensch, String> SpalteNachname;

    @FXML
    private Label SuchenText;

    @FXML
    private Button SuchenButton;

    private ObservableList<Mensch> observableList;
    private ArrayList<Mensch> menschen = new ArrayList<>();

    public void initialize() {
        SpalteVorname.setCellValueFactory(new PropertyValueFactory<Mensch, String>("vorname"));
        SpalteNachname.setCellValueFactory(new PropertyValueFactory<Mensch, String>("nachname"));
        SpalteGeburtsdatum.setCellValueFactory(new PropertyValueFactory<Mensch, String>("geburtsdatum"));
        SpaltEmail.setCellValueFactory(new PropertyValueFactory<Mensch, String>("eMail"));

        menschen.addAll(Verwaltung.getAnwender());
        menschen.addAll(Administratoren.getAdministratoren());
        menschen.addAll(Angestellte.getAngestellte());


        observableList = FXCollections.observableList(menschen);
        if (observableList.size() == 0) {
            System.out.println("Der Anwender hat keine Kunden!");
            Label keineFluege = new Label("Keine Anwender gefunden!");
            keineFluege.setId("keineFluegeGefunden");
            tabelle.setPlaceholder(keineFluege);
        }
        tabelle.setItems(observableList);

    }


    @FXML
    void SuchenAction(ActionEvent event) {

    }

    @FXML
    void LoschenAction(ActionEvent event) {

    }

    @FXML
    void BearbeitenAction(ActionEvent event) {

    }

    @FXML
    void ZuruckAction(ActionEvent event) {

    }
}
