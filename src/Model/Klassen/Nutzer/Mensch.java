package Model.Klassen.Nutzer;

/**
 * Created by knoll on 14.03.2017.
 */
public class Mensch {

    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private int passnummer;
    private String eMail;
    private String passwort;


    public Mensch(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.passnummer = passnummer;
        this.eMail = eMail;
        this.passwort = passwort;
    }


    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public int getPassnummer() {
        return passnummer;
    }

    public String geteMail() {
        return eMail;
    }

    public String getNachname() {
        return nachname;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getVorname() {
        return vorname;
    }


    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setPassnummer(int passnummer) {
        this.passnummer = passnummer;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String toString() {
        return vorname + ',' + nachname + ',' + geburtsdatum + ',' + passnummer + ',' + eMail + ',' + passwort + '}';
    }
}
