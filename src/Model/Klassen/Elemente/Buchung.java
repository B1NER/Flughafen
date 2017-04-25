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
    private boolean createdByAnwender;
    private String createdBy;


    public Buchung(int buchungsID, Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        this.buchungsID = buchungsID;
        this.anwender = anwender;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
        this.buchungspreis = buchungspreis;
        this.hinflug = hinflug;
        this.rueckflug = rueckflug;
        this.gepaeck = gepaeck;
        this.createdByAnwender = createdByAnwender;
    }

    public Buchung(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchungen.setBuchungsCounter(Buchungen.getBuchungsCounter() + 1);
        setBuchungsID(Buchungen.getBuchungsCounter());
        this.anwender = anwender;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
        this.buchungspreis = buchungspreis;
        this.hinflug = hinflug;
        this.rueckflug = rueckflug;
        this.gepaeck = gepaeck;
        this.createdByAnwender = createdByAnwender;
    }

    public Buchung(int buchungsID, Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        this.buchungsID = buchungsID;
        this.anwender = anwender;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
        this.buchungspreis = buchungspreis;
        this.hinflug = hinflug;
        this.gepaeck = gepaeck;
        this.createdByAnwender = createdByAnwender;
    }

    public Buchung(Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchungen.setBuchungsCounter(Buchungen.getBuchungsCounter() + 1);
        setBuchungsID(Buchungen.getBuchungsCounter());
        this.anwender = anwender;
        this.anzahlSitzplaetze = anzahlSitzplaetze;
        this.buchungspreis = buchungspreis;
        this.hinflug = hinflug;
        this.gepaeck = gepaeck;
        this.createdByAnwender = createdByAnwender;
    }


    public boolean isCreatedByAnwender() {
        return createdByAnwender;
    }

    public void setCreatedByAnwender(boolean createdByAnwender) {
        this.createdByAnwender = createdByAnwender;
    }

    public Anwender getAnwender() {
        return anwender;
    }

    public void setAnwender(Anwender anwender) {
        this.anwender = anwender;
    }

    public Flug getHinflug() {
        return hinflug;
    }

    public void setHinflug(Flug hinflug) {
        this.hinflug = hinflug;
    }

    public double getBuchungspreis() {
        return buchungspreis;
    }

    public void setBuchungspreis(double buchungspreis) {
        this.buchungspreis = buchungspreis;
    }

    public Flug getRueckflug() {
        return rueckflug;
    }

    public void setRueckflug(Flug rueckflug) {
        this.rueckflug = rueckflug;
    }

    public Gepaeck getGepaeck() {
        return gepaeck;
    }

    public void setGepaeck(Gepaeck gepaeck) {
        this.gepaeck = gepaeck;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    public int getBuchungsID() {
        return buchungsID;
    }

    public void setBuchungsID(int buchungsID) {
        this.buchungsID = buchungsID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Buchung{" +
                "buchungsID=" + buchungsID +
                ", hinflug=" + hinflug +
                ", rueckflug=" + rueckflug +
                ", anwender=" + anwender +
                ", anzahlSitzplaetze=" + anzahlSitzplaetze +
                ", gepaeck=" + gepaeck +
                ", buchungspreis=" + buchungspreis +
                ", createdByAnwender=" + createdByAnwender +
                '}';
    }
}