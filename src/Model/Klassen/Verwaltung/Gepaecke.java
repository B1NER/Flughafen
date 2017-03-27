package Model.Klassen.Verwaltung;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Gepaecke {
    private static ArrayList<Gepaeck> gepaecke = new ArrayList<>();
    private static int gepaeckecounter;


    public void gepeckBearbeiten(Gepaeck gepaeck, double neuesGewicht) {

    }


    public static ArrayList<Gepaeck> getGepaecke() {
        return gepaecke;
    }

    public static int getGepaeckecounter() {
        return gepaeckecounter;
    }

    public static void setGepaecke(ArrayList<Gepaeck> gepaecke) {
        Gepaecke.gepaecke = gepaecke;
    }

    public static void setGepaeckecounter(int gepaeckecounter) {
        Gepaecke.gepaeckecounter = gepaeckecounter;
    }
}
