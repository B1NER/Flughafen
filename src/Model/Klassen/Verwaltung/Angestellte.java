package Model.Klassen.Verwaltung;

import Model.Exceptions.NutzerDoesNotExistException;
import Model.Exceptions.WrongPasswordException;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Angestellte {

    private static ArrayList<Angestellter> angestellte = new ArrayList<>();
    private static int angestelltenCounter = 0;


    public static void addAngestellter(Angestellter angestellter) {
        angestellte.add(angestellter);
    }

    public static void removeAngestellter(Angestellter angestellter) {
        for (HashMap.Entry<Anwender, Angestellter> h : Verwaltung.getAnwenderAnestellten().entrySet()) {
            if (h.getValue().equals(angestellter)) {
                Verwaltung.getAnwenderAnestellten().remove(h.getKey());
            }
        }
        System.out.println(Verwaltung.getAnwenderAnestellten());
        angestellte.remove(angestellter);
    }

    public static void angestelltenBearbeiten(Angestellter angestellter, String vorname, String nachname, String geburtsdatum, String passwort, String bestaetigungsPasswort) throws WrongPasswordException {
        Angestellter a = angestellter;
        angestellter.setVorname(vorname);
        angestellter.setNachname(nachname);
        angestellter.setGeburtsdatum(geburtsdatum);
        if (passwort.equals(bestaetigungsPasswort)) {
            angestellter.setPasswort(passwort);
        } else {
            throw new WrongPasswordException();
        }
        System.out.println(a + "zu" + angestellter + " bearbeitet!");
    }


    public static Angestellter getAngestelltenByID(int angestelltenID) throws NutzerDoesNotExistException {
        for (int i = 0; i < angestellte.size(); i++) {
            if (angestellte.get(i).getAngestelltenID() == angestelltenID) {
                return angestellte.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static ArrayList<Angestellter> getAngestellte() {
        return angestellte;
    }

    public static void setAngestellte(ArrayList<Angestellter> angestellte) {
        Angestellte.angestellte = angestellte;
    }

    public static int getAngestelltenCounter() {
        return angestelltenCounter;
    }

    public static void setAngestelltenCounter(int angestelltenCounter) {
        Angestellte.angestelltenCounter = angestelltenCounter;
    }
}
