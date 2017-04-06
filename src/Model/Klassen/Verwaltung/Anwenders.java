package Model.Klassen.Verwaltung;

import Model.Exceptions.FlugNotFoundException;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Anwenders {

    private static ArrayList<Anwender> anwenders = new ArrayList<>();
    private static int anwenderCounter = 0;


    public static void addAnwender(Anwender anwender) {
        anwenders.add(anwender);
    }

    public static void removeAnwender(Anwender anwender) {
        anwenders.remove(anwender);
    }

    public static void anwenderBearbeiten(Anwender anwender) {
        //Zu implementieren TODO
    }


    public static Anwender getAnwenderByID(int anwenderID) throws NutzerDoesNotExistException{
        for (int i = 0; i < anwenders.size(); i++) {
            if(anwenders.get(i).getAnwenderID() == anwenderID){
                return anwenders.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static ArrayList<Anwender> getAnwenders() {
        return anwenders;
    }

    public static int getAnwenderCounter() {
        return anwenderCounter;
    }


    public static void setAnwender(ArrayList<Anwender> anwenders) {
        Anwenders.anwenders = anwenders;
    }

    public static void setAnwenderCounter(int anwenderCounter) {
        Anwenders.anwenderCounter = anwenderCounter;
    }
}
