package Model.Klassen.Verwaltung;

import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Buchungen {

    private static ArrayList<Buchung> buchungen = new ArrayList<>();
    private static int buchungsCounter = 0;


    public static ArrayList<Buchung> getBuchungenByAnwender(Anwender anwender) {
        ArrayList<Buchung> l = new ArrayList<>();
        for (int i = 0; i < buchungen.size(); i++) {
            if (buchungen.get(i).getAnwender().equals(anwender) && !buchungen.get(i).isCreatedByAnwender()) {
                l.add(buchungen.get(i));
            }
        }
        return l;
    }

    public static void addBuchung(Buchung buchung) {
        buchungen.add(buchung);
    }

    public static void buchungBearbeiten(Buchung buchung, Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis) {
        buchung.setHinflug(hinflug);
        buchung.setRueckflug(rueckflug);
        buchung.setAnwender(anwender);
        buchung.setAnzahlSitzplaetze(anzahlSitzplaetze);
        buchung.setGepaeck(gepaeck);
        buchung.setBuchungspreis(buchungspreis);
    }

    public static void removeBuchung(Buchung buchung) {
        buchungen.remove(buchung);
    }

    public static boolean isRueckflug(Buchung buchung) {
        if (buchung.getRueckflug() == null) {
            return false;
        } else {
            return true;
        }
    }

    public static Buchung getBuchungByID(int buchungsID) {
        return null;
    }


    public static void setBuchungen(ArrayList<Buchung> buchungen) {
        Buchungen.buchungen = buchungen;
    }

    public static void setBuchungsCounter(int buchungsCounter) {
        Buchungen.buchungsCounter = buchungsCounter;
    }

    public static ArrayList<Buchung> getBuchungen() {
        return buchungen;
    }

    public static int getBuchungsCounter() {
        return buchungsCounter;
    }
}
