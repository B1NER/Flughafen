package Model.Klassen.Elemente;

/**
 * Created by knoll on 17.03.2017.
 */
public class Gepaeck {

    private int gepaeckID;
    private String gepaeckTyp;
    private double gewicht;

    public Gepaeck(int gepaeckID, double gewicht, String gepaeckTyp) {
        setGepaeckID(gepaeckID);
        setGepaeckTyp(gepaeckTyp);
        setGewicht(gewicht);
    }

    public double getGewicht() {
        return gewicht;
    }

    public int getGepaeckID() {
        return gepaeckID;
    }

    public String getGepaeckTyp() {
        return gepaeckTyp;
    }

    public void setGepaeckID(int gepaeckID) {
        this.gepaeckID = gepaeckID;
    }

    public void setGepaeckTyp(String gepaeckTyp) {
        this.gepaeckTyp = gepaeckTyp;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

}
