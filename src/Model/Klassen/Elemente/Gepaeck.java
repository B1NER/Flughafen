package Model.Klassen.Elemente;

import Model.Enums.Gepaecktypen;
import Model.Klassen.Verwaltung.Gepaecke;

/**
 * Created by knoll on 17.03.2017.
 */
public class Gepaeck {

    private int gepaeckID;
    private Gepaecktypen gepaeckTyp;
    private double gewicht;

    public Gepaeck(int gepaeckID, double gewicht, Gepaecktypen gepaeckTyp) {
        this.gepaeckID = gepaeckID;
        this.gepaeckTyp = gepaeckTyp;
        this.gewicht = gewicht;
    }

    public Gepaeck(double gewicht, Gepaecktypen gepaeckTyp) {
        Gepaecke.setGepaeckecounter(Gepaecke.getGepaeckecounter() + 1);
        this.gepaeckID = Gepaecke.getGepaeckecounter();
        this.gepaeckTyp = gepaeckTyp;
        this.gewicht = gewicht;
    }


    public double getGewicht() {
        return gewicht;
    }

    public int getGepaeckID() {
        return gepaeckID;
    }

    public Gepaecktypen getGepaeckTyp() {
        return gepaeckTyp;
    }

    public void setGepaeckID(int gepaeckID) {
        this.gepaeckID = gepaeckID;
    }

    public void setGepaeckTyp(Gepaecktypen gepaeckTyp) {
        this.gepaeckTyp = gepaeckTyp;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }


    @Override
    public String toString() {
        return gepaeckTyp+ " - " +gewicht;
    }

    public String toStringLog() {
        return "Gepaeck{" +
                "gepaeckID=" + gepaeckID +
                ", gepaeckTyp=" + gepaeckTyp +
                ", gewicht=" + gewicht +
                '}';
    }
}
