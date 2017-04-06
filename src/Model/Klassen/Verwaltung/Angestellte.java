package Model.Klassen.Verwaltung;

import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Angestellte {

    private static ArrayList<Angestellter> angestellte = new ArrayList<>();
    private static int angestelltenCounter = 0;


    public static void addAngestellter(Angestellter angestellter) {
        angestellte.add(angestellter);
    }

    public static void removeAngestellter(Angestellter angestellter){
        angestellte.remove(angestellter);
    }

    public static void angestellterBearbeiten(){
        //Zu implementieren TODO
    }


    public static Angestellter getAngestelltenByID(int angestelltenID) throws NutzerDoesNotExistException{
        for (int i = 0; i < angestellte.size(); i++) {
            if(angestellte.get(i).getAngestelltenID() == angestelltenID){
                return angestellte.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static ArrayList<Angestellter> getAngestellte() {
        return angestellte;
    }

    public static int getAngestelltenCounter() {
        return angestelltenCounter;
    }


    public static void setAngestellte(ArrayList<Angestellter> angestellte) {
        Angestellte.angestellte = angestellte;
    }

    public static void setAngestelltenCounter(int angestelltenCounter) {
        Angestellte.angestelltenCounter = angestelltenCounter;
    }
}
