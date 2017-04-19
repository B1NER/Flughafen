package Model.Klassen.Nutzer;

import Model.Klassen.Verwaltung.Anwenders;

/**
 * Created by knoll on 14.03.2017.
 */

public class Anwender extends Mensch {
    private int anwenderID;

    public Anwender(int anwenderID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAnwenderID(anwenderID);
    }

    public Anwender(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Anwenders.setAnwenderCounter(Anwenders.getAnwenderCounter() + 1);
        setAnwenderID(Anwenders.getAnwenderCounter());
    }


    public int getAnwenderID() {
        return anwenderID;
    }

    public void setAnwenderID(int anwenderID) {
        this.anwenderID = anwenderID;
    }

    @Override
    public String toString() {
        return "Anwender: "+super.toString() + "angestelltenID=" + anwenderID;
    }
}
