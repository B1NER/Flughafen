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
    private static int anwenderCounter;

    public Anwender(int anwenderID, String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        super(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        setAnwenderID(anwenderID);
    }


    public void buchungErstellenMitRueckflug(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung buchung = new Buchung(hinflug, rueckflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
        Verwaltung.addBuchung(buchung);
    }

    public void buchungErstellenOhneRueckflug(int buchungsID, Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung buchung = new Buchung(hinflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
        Verwaltung.addBuchung(buchung);
    }


    public static void setAnwenderCounter(int anwenderCounter) {
        Anwender.anwenderCounter = anwenderCounter;
    }

    public static int getAnwenderCounter() {
        return anwenderCounter;
    }

    public int getAnwenderID() {
        return anwenderID;
    }

    public void setAnwenderID(int anwenderID) {
        this.anwenderID = anwenderID;
    }


    public ArrayList<Buchung> getBuchungen() {
        return Verwaltung.getBuchungenByAnwenderID(this.anwenderID);
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Verwaltung.getFluege().size(); i++) {
            if (Verwaltung.getFluege().get(i).getAbflugort().equals(abflugort) && Verwaltung.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Verwaltung.getFluege().get(i).getAbflugzeit().equals(abflugzeit)) {
                zutreffendeFluege.add(Verwaltung.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, String fluggesellschaft) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Verwaltung.getFluege().size(); i++) {
            if (Verwaltung.getFluege().get(i).getAbflugort().equals(abflugort) && Verwaltung.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Verwaltung.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft)) {
                zutreffendeFluege.add(Verwaltung.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit, String fluggesellschaft) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Verwaltung.getFluege().size(); i++) {
            if (Verwaltung.getFluege().get(i).getAbflugort().equals(abflugort) && Verwaltung.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Verwaltung.getFluege().get(i).getAbflugzeit().equals(abflugzeit) && Verwaltung.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft)) {
                zutreffendeFluege.add(Verwaltung.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Verwaltung.getFluege().size(); i++) {
            if (Verwaltung.getFluege().get(i).getAbflugort().equals(abflugort) && Verwaltung.getFluege().get(i).getAnkunftsort().equals(ankunftsort)) {
                zutreffendeFluege.add(Verwaltung.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> sortFluegeBy(String eigenschaft) {
        return new ArrayList<>();
    }

    public void gepaeckBearbeiten(Buchung buchung, int neuesGewicht) {
        buchung.getGepaeck().setGewicht(neuesGewicht);
    }
}
