package Model.Klassen.Verwaltung;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Flug;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by knoll on 27.03.2017.
 */

public abstract class Fluege {

    private static ArrayList<Flug> fluege = new ArrayList<>();
    private static int fluegecounter = 0;


    public static void addFlug(Flug flug) {
        fluege.add(flug);
    }

    public static Flug getFlugByID(String flugID) throws FlugNotFoundException {
        for (int i = 0; i < fluege.size(); i++) {
            if (fluege.get(i).getFlugID().equals(flugID))
                return fluege.get(i);
        }
        throw new FlugNotFoundException();
    }

    public static ArrayList<Flug> getAktuelleFluege() {
        ArrayList<Flug> l = new ArrayList<>();
        for (int i = 0; i < fluege.size(); i++) {
            if (!Fluege.isVerfallen(fluege.get(i))) {
                l.add(fluege.get(i));
            }
        }
        return l;
    }

    public static ArrayList<Flug> getVerfalleneFluege() {
        ArrayList<Flug> l = new ArrayList<>();
        for (int i = 0; i < fluege.size(); i++) {
            if (Fluege.isVerfallen(fluege.get(i))) {
                l.add(fluege.get(i));
            }
        }
        return l;
    }

    public static boolean isVerfallen(Flug flug) {
        return flug.getAbflugzeit().before(Calendar.getInstance().getTime());
    }

    public static ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit, int anzahlSitzplaetze) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getAbflugzeit().getYear() == abflugzeit.getYear() && Fluege.getFluege().get(i).getAbflugzeit().getMonth() == abflugzeit.getMonth() && Fluege.getFluege().get(i).getAbflugzeit().getDay() == abflugzeit.getDay() && Fluege.getVerfuegbarePlaetze(Fluege.getFluege().get(i)) > anzahlSitzplaetze) {
                if(!Fluege.isVerfallen(Fluege.getFluege().get(i))){
                    zutreffendeFluege.add(Fluege.getFluege().get(i));
                }
            }
        }
        if (zutreffendeFluege.size() > 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public static ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, String fluggesellschaft, int anzahlSitzplaetze) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft) && Fluege.getVerfuegbarePlaetze(Fluege.getFluege().get(i)) > anzahlSitzplaetze) {
                if(!Fluege.isVerfallen(Fluege.getFluege().get(i))){
                    zutreffendeFluege.add(Fluege.getFluege().get(i));
                }
            }
        }
        if (zutreffendeFluege.size() > 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public static ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit, String fluggesellschaft, int anzahlSitzplaetze) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getAbflugzeit().getYear() == abflugzeit.getYear() && Fluege.getFluege().get(i).getAbflugzeit().getMonth() == abflugzeit.getMonth() && Fluege.getFluege().get(i).getAbflugzeit().getDay() == abflugzeit.getDay() && Fluege.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft) && Fluege.getVerfuegbarePlaetze(Fluege.getFluege().get(i)) > anzahlSitzplaetze) {
                if(!Fluege.isVerfallen(Fluege.getFluege().get(i))){
                    zutreffendeFluege.add(Fluege.getFluege().get(i));
                }
            }
        }
        if (zutreffendeFluege.size() > 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public static ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, int anzahlSitzplaetze) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getVerfuegbarePlaetze(Fluege.getFluege().get(i)) > anzahlSitzplaetze) {
                if(!Fluege.isVerfallen(Fluege.getFluege().get(i))){
                    zutreffendeFluege.add(Fluege.getFluege().get(i));
                }
            }
        }
        if (zutreffendeFluege.size() > 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public static int getVerfuegbarePlaetze(Flug flug) {
        int verfuegbarePlaetze = flug.getAnzahlPlaetze();
        for (int i = 0; i < Buchungen.getBuchungen().size(); i++) {
            if (flug.equals(Buchungen.getBuchungen().get(i).getHinflug()) || flug.equals(Buchungen.getBuchungen().get(i).getRueckflug())) {
                verfuegbarePlaetze -= Buchungen.getBuchungen().get(i).getAnzahlSitzplaetze();
            }
        }
        return verfuegbarePlaetze;
    }

    public static int getVerfuegbaresGewicht(Flug flug) {
        int verfuegbaresGewicht = flug.getGepaeckskapazitaet();
        for (int i = 0; i < Buchungen.getBuchungen().size(); i++) {
            if (flug.equals(Buchungen.getBuchungen().get(i).getHinflug()) || flug.equals(Buchungen.getBuchungen().get(i).getHinflug())) {
                verfuegbaresGewicht -= Buchungen.getBuchungen().get(i).getGepaeck().getGewicht();
            }
        }
        return verfuegbaresGewicht;
    }

    public static int getFluegecounter() {
        return fluegecounter;
    }

    public static ArrayList<Flug> getFluege() {
        return fluege;
    }

    public static void setFluege(ArrayList<Flug> fluege) {
        Fluege.fluege = fluege;
    }

    public static void setFluegecounter(int fluegecounter) {
        Fluege.fluegecounter = fluegecounter;
    }
}
