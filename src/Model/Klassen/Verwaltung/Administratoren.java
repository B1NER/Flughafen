package Model.Klassen.Verwaltung;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Administratoren {
    private static ArrayList<Administrator> administratoren = new ArrayList<>();
    private static int adminCounter;


    public static void gepaeckeBearbeiten(Buchung buchung, double neuesGewicht) {

    }

    public static void removeBuchung(Buchung buchung) {

    }

    public static void removeAdministrator(Administrator admin) {

    }

    public static void addAndministrator(Administrator administrator) {
        administratoren.add(administrator);
    }


    public static ArrayList<Administrator> getAdministratoren() {
        return administratoren;
    }

    public static int getAdminCounter() {
        return adminCounter;
    }

    public static void setAdminCounter(int adminCounter) {
        Administratoren.adminCounter = adminCounter;
    }

    public static void setAdministratoren(ArrayList<Administrator> administratoren) {
        Administratoren.administratoren = administratoren;
    }
}
