package Model.Klassen.Nutzer;

import Model.Klassen.Verwaltung.Administratoren;

/**
 * Created by knoll on 14.03.2017.
 */
public class Administrator extends Mensch {

    private int adminID;


    public Administrator(int adminID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAdminID(adminID);
    }

    public Administrator(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Administratoren.setAdminCounter(Administratoren.getAdminCounter() + 1);
        setAdminID(Administratoren.getAdminCounter());
    }


    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String toString() {
        return "Angestellter: " + super.toString() + "angestelltenID=" + adminID;
    }
}
