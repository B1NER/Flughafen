package Model.Klassen.Verwaltung;

import Model.Enums.Gepaecktypen;
import Model.Exceptions.FlugNotFoundException;
import Model.Exceptions.GepaeckDoesNotExist;
import Model.Exceptions.ToHighWeightException;
import Model.Klassen.Elemente.Gepaeck;

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

    public static void gepeckBearbeiten(Gepaeck gepaeck, double neuesGewicht, Gepaecktypen gepaeckTyp) throws ToHighWeightException {   //TODO Methode funktioniert nicht!
        gepaeck.setGepaeckTyp(gepaeckTyp);
        String flugID = "";

        int anzahlPersonen = 1;     //Auf 1 gesetzt, sollte aus irgend einem Grund die Buchung nicht gefunden werden, würde bei 20* anzPErsonen mit 0 multipliziert und das neue geicht dürfte nicht über 0 sein
        for (int i = 0; i < Buchungen.getBuchungen().size(); i++) {
            if (Buchungen.getBuchungen().get(i).getGepaeck().equals(gepaeck)) {
                anzahlPersonen = Buchungen.getBuchungen().get(i).getAnzahlSitzplaetze();
                flugID = Buchungen.getBuchungen().get(i).getHinflug().getFlugID();
            }
        }

        try {
            if (neuesGewicht < 20 * anzahlPersonen && neuesGewicht * anzahlPersonen < Fluege.getVerfuegbarePlaetze(Fluege.getFlugByID(flugID))) {
                gepaeck.setGewicht(neuesGewicht);
            } else {
                throw new ToHighWeightException();
            }
        } catch (final FlugNotFoundException e) {

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
