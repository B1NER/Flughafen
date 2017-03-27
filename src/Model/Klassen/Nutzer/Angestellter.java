package Model.Klassen.Nutzer;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;

import java.util.ArrayList;

/**
 * Created by knoll on 14.03.2017.
 */
public class Angestellter extends Mensch {
    private int angestelltenID;

    public Angestellter(int angestelltenID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAngestelltenID(angestelltenID);
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
