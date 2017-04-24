package Controller;

import Model.Enums.Views;
import Model.Klassen.MAIN;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Nutzer.Mensch;
import Model.Klassen.Verwaltung.Administratoren;
import Model.Klassen.Verwaltung.Angestellte;
import Model.Klassen.Verwaltung.Anwenders;
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
    private TableColumn<Mensch, String> spalteTyp;

    @FXML
    private Label SuchenText;

    @FXML
    private Button SuchenButton;


    private ArrayList<Mensch> menschen = new ArrayList<>();
    private ObservableList<Mensch> observableList = FXCollections.observableList(menschen);

    public void initialize() {
        SpalteVorname.setCellValueFactory(new PropertyValueFactory<Mensch, String>("vorname"));
        SpalteNachname.setCellValueFactory(new PropertyValueFactory<Mensch, String>("nachname"));
        SpalteGeburtsdatum.setCellValueFactory(new PropertyValueFactory<Mensch, String>("geburtsdatum"));
        SpaltEmail.setCellValueFactory(new PropertyValueFactory<Mensch, String>("email"));
        spalteTyp.setCellValueFactory(new PropertyValueFactory<Mensch, String>("typ"));

        menschen.clear();
        menschen.addAll(Anwenders.getAnwenders());
        menschen.addAll(Administratoren.getAdministratoren());
        menschen.addAll(Angestellte.getAngestellte());


        observableList = FXCollections.observableList(menschen);
        tabelle.setItems(observableList);
    }


    @FXML
    void SuchenAction(ActionEvent event) {
        ArrayList<Mensch> zutreffendeMenschen = new ArrayList<>();

        if (!VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            //Suche nach Vornamen
            for (int i = 0; i < menschen.size(); i++) {
                if (menschen.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeMenschen.add(menschen.get(i));
                }
            }
        } else if (VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")) {
            //Suche nach Nachnamen
            for (int i = 0; i < menschen.size(); i++) {
                if (menschen.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase())) {
                    zutreffendeMenschen.add(menschen.get(i));
                }
            }
        } else if (!VornameFeld.getText().equals("") && !NachnameFeld.getText().equals("")){
            //Suche nach Vornamen und Nachnamen
            for (int i = 0; i < menschen.size(); i++) {
                if (menschen.get(i).getNachname().toLowerCase().contains(NachnameFeld.getText().toLowerCase()) && menschen.get(i).getVorname().toLowerCase().contains(VornameFeld.getText().toLowerCase())) {
                    zutreffendeMenschen.add(menschen.get(i));
                }
            }
        }

        observableList = FXCollections.observableList(zutreffendeMenschen);
        tabelle.setItems(observableList);

        if (VornameFeld.getText().equals("") && NachnameFeld.getText().equals("")) {
            observableList = FXCollections.observableList(menschen);
            tabelle.setItems(observableList);
        }

        if (observableList.size() < 1) {
            observableList.clear();
            tabelle.setItems(observableList);
            System.out.println("Es gibt keinen Ergebnisse mit diesen Eigenschaften");
            Label keinErgebnis = new Label("Kein Ergebnis gefunden!");
            keinErgebnis.setId("keinErgebnis");
            tabelle.setPlaceholder(keinErgebnis);
        }


    }

    @FXML
    void LoschenAction(ActionEvent event) {
        if(tabelle.getSelectionModel().getSelectedItem() instanceof Administrator){
            Administratoren.removeAdministrator((Administrator)tabelle.getSelectionModel().getSelectedItem());
        }else if(tabelle.getSelectionModel().getSelectedItem() instanceof Angestellter){
            Angestellte.removeAngestellter((Angestellter)tabelle.getSelectionModel().getSelectedItem());
        }else{
            Anwenders.removeAnwender((Anwender)tabelle.getSelectionModel().getSelectedItem());
        }
        observableList.remove(tabelle.getSelectionModel().getSelectedItem());
        initialize();
    }

    @FXML
    void BearbeitenAction(ActionEvent event) {  //TODO Admin Kundenprofil
        if(tabelle.getSelectionModel().getSelectedItem() != null) {
            ProfilBearbeitenController.setZuBearbeitenderMensch(tabelle.getSelectionModel().getSelectedItem());
            MAIN.fensterOeffnen(Views.ProfilBearbeiten);
        }
    }

    @FXML
    void ZuruckAction(ActionEvent event) {
        if (Verwaltung.getAngemeldeter() instanceof Administrator) {
            MAIN.fensterOeffnen(Views.AdminStartseite);
        } else {
            MAIN.fensterOeffnen(Views.AngestellterStartseite);
        }
    }
}
