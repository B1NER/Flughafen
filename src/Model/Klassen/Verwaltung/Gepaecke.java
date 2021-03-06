package Model.Klassen.Verwaltung;

import Model.Exceptions.GepaeckDoesNotExist;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Gepaecke {
    private static ArrayList<Gepaeck> gepaecke = new ArrayList<>();
    private static int gepaeckecounter = 0;


    public static void addGepaeck(Gepaeck gepaeck) {
        gepaecke.add(gepaeck);
    }

    public static void removeGepaeck(Gepaeck gepaeck) {
        gepaecke.remove(gepaeck);
    }

    public static void gepeckBearbeiten(Gepaeck gepaeck, double neuesGewicht) throws ToHighWeightException {
        if (neuesGewicht < 20) {
            gepaeck.setGewicht(neuesGewicht);
        } else {
            throw new ToHighWeightException();
        }
    }


    public static Gepaeck getGepaeckByID(int gepaeckID)throws GepaeckDoesNotExist{
        for (int i = 0; i < gepaecke.size(); i++) {
            if(gepaecke.get(i).getGepaeckID() == gepaeckID){
                return gepaecke.get(i);
            }
        }
        throw new GepaeckDoesNotExist();
    }

    public static ArrayList<Gepaeck> getGepaecke() {
        return gepaecke;
    }

    public static int getGepaeckecounter() {
        return gepaeckecounter;
    }

    public static void setGepaecke(ArrayList<Gepaeck> gepaecke) {
        Gepaecke.gepaecke = gepaecke;
    }

    public static void setGepaeckecounter(int gepaeckecounter) {
        Gepaecke.gepaeckecounter = gepaeckecounter;
    }
}
