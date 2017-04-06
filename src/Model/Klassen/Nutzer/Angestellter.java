package Model.Klassen.Nutzer;

import Model.Klassen.Verwaltung.Angestellte;


/**
 * Created by knoll on 14.03.2017.
 */
public class Angestellter extends Mensch {
    private int angestelltenID;

    public Angestellter(int angestelltenID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAngestelltenID(angestelltenID);
    }

    public Angestellter(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Angestellte.setAngestelltenCounter(Angestellte.getAngestelltenCounter() + 1);
        setAngestelltenID(Angestellte.getAngestelltenCounter());
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
