package Model.Klassen.Nutzer;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Verwaltung.Verwaltung;

import java.util.ArrayList;

/**
 * Created by knoll on 14.03.2017.
 */
public class Administrator extends Mensch {

    private int adminID;


    public Administrator(int adminID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAdminID(adminID);
    }


    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}
