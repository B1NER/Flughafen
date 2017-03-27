package Model.Klassen.Verwaltung;

import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Angestellten {

    private static ArrayList<Angestellter> angestellte = new ArrayList<>();
    private static int angestelltenCounter;


    public static void addAngestellter(Angestellter angestellter) {
        angestellte.add(angestellter);
    }

    public static Angestellten getAngestelltenByID(int angestelltenID) {
        return null;
    }

    public static ArrayList<Anwender> getAgestelltenAnwenders(Angestellter angestellter) {
        return new ArrayList<>();
    }

    public static void buchungDurchfueren(Angestellter angestellter, Anwender anwender) {

    }


    public static ArrayList<Angestellter> getAngestellte() {
        return angestellte;
    }

    public static int getAngestelltenCounter() {
        return angestelltenCounter;
    }

    public static void setAngestellte(ArrayList<Angestellter> angestellte) {
        Angestellten.angestellte = angestellte;
    }

    public static void setAngestelltenCounter(int angestelltenCounter) {
        Angestellten.angestelltenCounter = angestelltenCounter;
    }
}
