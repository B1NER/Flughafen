package Model.Klassen.Verwaltung;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Anwenders {

    private static ArrayList<Anwender> anwenders = new ArrayList<>();
    private static int anwenderCounter;


    public static Anwender getAnwenderByID(int anwenderID) {
        return null;
    }

    public static void addAnwender(Anwender anwender) {
        anwenders.add(anwender);
    }

    public static void buchungErstellen(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung buchung = new Buchung(hinflug, rueckflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
    }

    public static void buchungErstellen(Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung buchung = new Buchung(hinflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
    }

    public static void gepaeckBearbeiten(Buchung buchung, double neuesGewicht) {
        if (neuesGewicht < 20) {
            buchung.getGepaeck().setGewicht(neuesGewicht);
        } else {
            //Throw new ToMuchGewichtException();
        }

    }

    public static void anwenderBearbeiten(Anwender anwender) {

    }


    public static ArrayList<Anwender> getAnwenders() {
        return anwenders;
    }

    public static int getAnwenderCounter() {
        return anwenderCounter;
    }

    public static void setAnwender(ArrayList<Anwender> anwenders) {
        Anwenders.anwenders = anwenders;
    }

    public static void setAnwenderCounter(int anwenderCounter) {
        Anwenders.anwenderCounter = anwenderCounter;
    }
}
