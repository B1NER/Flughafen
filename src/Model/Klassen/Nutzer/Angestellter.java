package Model.Klassen.Nutzer;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;

import java.util.ArrayList;

/**
 * Created by knoll on 14.03.2017.
 */
public class Angestellter extends Mensch {
    private int angestelltenID;
    private static int angestelltenCounter;

    public Angestellter(int angestelltenID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAngestelltenID(angestelltenID);
    }


    public static void setAngestelltenCounter(int angestelltenCounter) {
        Angestellter.angestelltenCounter = angestelltenCounter;
    }

    public static int getAngestelltenCounter() {
        return angestelltenCounter;
    }

    public ArrayList<Flug> getAktuelleFluege() {
        return new ArrayList<>();
    }

    public ArrayList<Anwender> getMyAnwender() {
        return new ArrayList<>();
    }

    public ArrayList<Anwender> getAnwenderByID(int anwenderID) {
        return new ArrayList<>();
    }

    public void buchungDurchfueren(Anwender anwender) {

    }

    public ArrayList<Buchung> getAnwenderBuchungen(Anwender anwender) {
        return anwender.getBuchungen();
    }

    public void gepeckBearbeiten(Anwender anwender, Buchung buchung, double neuesGewicht) {
        anwender.gepaeckBearbeiten(buchung, neuesGewicht);
    }


    public int getAngestelltenID() {
        return angestelltenID;
    }

    public void setAngestelltenID(int angestelltenID) {
        this.angestelltenID = angestelltenID;
    }

    public String toString() {
        return "Angestellter: "+super.toString() + "angestelltenID=" + angestelltenID;
    }
}
