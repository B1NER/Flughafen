package Model.Klassen.Elemente;


import Model.Klassen.Nutzer.Anwender;

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
    private boolean createdByAnwender;      //true = 0      false = 1
    private static int buchungsCounter = 0;

    public Buchung(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender){
        buchungsCounter++;
        setBuchungsID(buchungsCounter);
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
        buchungsCounter++;
        setBuchungsID(buchungsCounter);
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

    public boolean isRueckflug(){
        if(rueckflug == null){
            return false;
        }else {
            return true;
        }
    }

}