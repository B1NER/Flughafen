package Model.Klassen.Elemente;


import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Buchungen;

/**
 * Created by knoll on 17.03.2017.
 */
public class Buchung {

    private int buchungsID;
    private Flug hinflug;
    private Flug rueckflug;
    private Anwender anwender;
    private int anzahlSitzplaetze;
    private Gepaeck gepaeck;
    private double buchungspreis;
    private boolean createdByAnwender; //1 = Anwender hat gbucht = true     0 = Angestellter hat gebucht = false


    public Buchung(int buchungsID, Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender){
        setBuchungsID(buchungsID);
        setAnwender(anwender);
        setAnzahlSitzplaetze(anzahlSitzplaetze);
        setBuchungsID(buchungsID);
        setBuchungspreis(buchungspreis);
        setHinflug(hinflug);
        setRueckflug(rueckflug);
        setGepaeck(gepaeck);
        setCreatedByAnwender(createdByAnwender);
    }

    public Buchung(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender){
        Buchungen.setBuchungsCounter(Buchungen.getBuchungsCounter() + 1);
        setBuchungsID(Buchungen.getBuchungsCounter());
        setAnwender(anwender);
        setAnzahlSitzplaetze(anzahlSitzplaetze);
        setBuchungsID(buchungsID);
        setBuchungspreis(buchungspreis);
        setHinflug(hinflug);
        setRueckflug(rueckflug);
        setGepaeck(gepaeck);
        setCreatedByAnwender(createdByAnwender);
    }

    public Buchung(Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender){
        Buchungen.setBuchungsCounter(Buchungen.getBuchungsCounter() + 1);
        setBuchungsID(Buchungen.getBuchungsCounter());
        setAnwender(anwender);
        setAnzahlSitzplaetze(anzahlSitzplaetze);
        setBuchungsID(buchungsID);
        setBuchungspreis(buchungspreis);
        setHinflug(hinflug);
        setGepaeck(gepaeck);
        setCreatedByAnwender(createdByAnwender);
    }


    public boolean isCreatedByAnwender() {
        return createdByAnwender;
    }

    public Anwender getAnwender() {
        return anwender;
    }

    public Flug getHinflug() {
        return hinflug;
    }

    public double getBuchungspreis() {
        return buchungspreis;
    }

    public Flug getRueckflug() {
        return rueckflug;
    }

    public Gepaeck getGepaeck() {
        return gepaeck;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public int getBuchungsID() {
        return buchungsID;
    }


    public void setAnwender(Anwender anwender) {
        this.anwender = anwender;
    }

    public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    public void setBuchungsID(int buchungsID) {
        this.buchungsID = buchungsID;
    }

    public void setBuchungspreis(double buchungspreis) {
        this.buchungspreis = buchungspreis;
    }

    public void setCreatedByAnwender(boolean createdByAnwender) {
        this.createdByAnwender = createdByAnwender;
    }

    public void setGepaeck(Gepaeck gepaeck) {
        this.gepaeck = gepaeck;
    }

    public void setHinflug(Flug hinflug) {
        this.hinflug = hinflug;
    }

    public void setRueckflug(Flug rueckflug) {
        this.rueckflug = rueckflug;
    }
}