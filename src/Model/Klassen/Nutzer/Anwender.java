package Model.Klassen.Nutzer;



import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Mensch;
import Model.Klassen.Verwaltung.Verwaltung;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knoll on 14.03.2017.
 */

public class Anwender extends Mensch {
    private int anwenderID;

    public Anwender(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAnwenderID(anwenderID);
    }


    public int getAnwenderID() {
        return anwenderID;
    }

    public void setAnwenderID(int anwenderID) {
        this.anwenderID = anwenderID;
    }
}
