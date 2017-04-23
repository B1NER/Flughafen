package Model.Klassen.Elemente;

import Model.Klassen.Verwaltung.Fluege;

import java.util.Date;

/**
 * Created by knoll on 17.03.2017.
 */
public class Flug {

    private String FlugID;
    private String flugGesellschaft;
    private String abflugort;
    private String ankunftsort;
    private int anzahlPlaetze;
    private int gepaeckskapazitaet;
    private Date abflugzeit;
    private Date ankunftszeit;
    private double preisProPerson;
    private String status;

    public Flug(String FlugID, String flugGesellschaft, String abflugort, String ankunftsort, int anzahlPlaetze, int gepaeckskapazitaet, Date abflugzeit, Date ankunftszeit, double preisProPerson) {
        this.FlugID = FlugID;
        this.flugGesellschaft = flugGesellschaft;
        this.abflugort = abflugort;
        this.ankunftsort = ankunftsort;
        this.anzahlPlaetze = anzahlPlaetze;
        this.gepaeckskapazitaet = gepaeckskapazitaet;
        this.abflugzeit = abflugzeit;
        this.ankunftszeit = ankunftszeit;
        this.preisProPerson = preisProPerson;
        if(Fluege.isVerfallen(this)){
            this.status = "verfallen";
        }else{
            this.status = "aktuell";
        }
    }

    public void setAbflugort(String abflugort) {
        this.abflugort = abflugort;
    }

    public void setAbflugzeit(Date abflugzeit) {
        this.abflugzeit = abflugzeit;
    }

    public void setAnkunftsort(String ankunftsort) {
        this.ankunftsort = ankunftsort;
    }

    public void setAnkunftszeit(Date ankunftszeit) {
        this.ankunftszeit = ankunftszeit;
    }

    public void setAnzahlPlaetze(int anzahlPlaetze) {
        this.anzahlPlaetze = anzahlPlaetze;
    }

    public void setFlugGesellschaft(String flugGesellschaft) {
        this.flugGesellschaft = flugGesellschaft;
    }

    public void setFlugID(String flugID) {
        FlugID = flugID;
    }

    public void setGepaeckskapazitaet(int gepaeckskapazitaet) {
        this.gepaeckskapazitaet = gepaeckskapazitaet;
    }

    public void setPreisProPerson(double preisProPerson) {
        this.preisProPerson = preisProPerson;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public int getGepaeckskapazitaet() {
        return gepaeckskapazitaet;
    }

    public Date getAbflugzeit() {
        return abflugzeit;
    }

    public Date getAnkunftszeit() {
        return ankunftszeit;
    }

    public double getPreisProPerson() {
        return preisProPerson;
    }

    public String getAbflugort() {
        return abflugort;
    }

    public String getAnkunftsort() {
        return ankunftsort;
    }

    public String getFlugGesellschaft() {
        return flugGesellschaft;
    }

    public String getFlugID() {
        return FlugID;
    }

    public String getStatus() {
        return status;
    }

    @Override

    public String toString() {
        return abflugort +" - "+ ankunftsort;
    }

    public String toStringLog() {
        return "Flug{" +
                "FlugID='" + FlugID + '\'' +
                ", flugGesellschaft='" + flugGesellschaft + '\'' +
                ", abflugort='" + abflugort + '\'' +
                ", ankunftsort='" + ankunftsort + '\'' +
                ", anzahlPlaetze=" + anzahlPlaetze +
                ", gepaeckskapazitaet=" + gepaeckskapazitaet +
                ", abflugzeit=" + abflugzeit +
                ", ankunftszeit=" + ankunftszeit +
                ", preisProPerson=" + preisProPerson +
                '}';
    }
}
