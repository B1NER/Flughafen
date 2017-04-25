package Model.Klassen.Nutzer;

/**
 * Created by knoll on 14.03.2017.
 */
public class Mensch {

    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private int passnummer;
    private String email;
    private String passwort;
    private String typ;


    public Mensch(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.passnummer = passnummer;
        this.email = eMail;
        this.passwort = passwort;
        if (this instanceof Administrator) {
            typ = "Administrator";
        } else if (this instanceof Angestellter) {
            typ = "Angestellter";
        } else {
            typ = "Anwender";
        }
    }


    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public int getPassnummer() {
        return passnummer;
    }

    public void setPassnummer(int passnummer) {
        this.passnummer = passnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String toString() {
        return vorname + ',' + nachname + ',' + geburtsdatum + ',' + passnummer + ',' + email + ',' + passwort + '}';
    }
}
