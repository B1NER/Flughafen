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
    private static int adminCounter;

    public Administrator(int adminID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAdminID(adminID);
    }

    public static int getAdminCounter() {
        return adminCounter;
    }

    public static void setAdminCounter(int adminCounter) {
        Administrator.adminCounter = adminCounter;
    }

    public ArrayList<Flug> getAktuelleFluege() {
        return new ArrayList<>();
    }

    public ArrayList<Buchung> getBuchungen() {
        return new ArrayList<>();
    }

    public ArrayList<Anwender> getAnwender() {
        return new ArrayList<>();
    }

    public ArrayList<Angestellter> getAngestellten() {
        return new ArrayList<>();
    }

    public void gepaeckeBearbeiten(Buchung buchung, int gewicht) {
        buchung.getAnwender().gepaeckBearbeiten(buchung,gewicht);
    }

    public void buchungLoeschen(Buchung buchung) {
        for (int i = 0; i < Verwaltung.getBuchungen().size(); i++) {
            if(Verwaltung.getBuchungen().get(i).getBuchungsID() == buchung.getBuchungsID()){
                Verwaltung.getBuchungen().remove(i);
            }
        }
    }

    public void administratorLoeschen(Administrator admin) {
        for (int i = 0; i < Verwaltung.getAdministratoren().size(); i++) {
            if(Verwaltung.getAdministratoren().get(i).getAdminID() == admin.getAdminID()){
                Verwaltung.getAdministratoren().remove(i);
            }
        }
    }

    public void angestelltenHinzufuegen(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        Angestellter.setAngestelltenCounter(Angestellter.getAngestelltenCounter()+1);
        Verwaltung.getAngestellte().add(new Angestellter(Angestellter.getAngestelltenCounter(),vorname,nachname,geburtsdatum,passnummer,eMail,passwort));
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

}
