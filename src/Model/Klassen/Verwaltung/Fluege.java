package Model.Klassen.Verwaltung;

import Model.Exceptions.FlugNotFoundException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Nutzer.Anwender;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Fluege {

    private static ArrayList<Flug> fluege = new ArrayList<>();
    private static int fluegecounter;


    public static void addFlug(Flug flug) {
        fluege.add(flug);
    }

    public static Flug getFlugByID(int flugID) {
        return null;
    }

    public ArrayList<Flug> getAktuelleFluege() {
        return new ArrayList<>();
    }

    public ArrayList<Flug> getVerfalleneFluege() {
        return new ArrayList<>();
    }

    public boolean isVerfallen(Flug flug) {
        if (flug.getAnkunftszeit().before(flug.getAbflugzeit())) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Buchung> getAnwenderBuchungen(Anwender anwender) {

        return new ArrayList<Buchung>();
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getAbflugzeit().equals(abflugzeit)) {
                zutreffendeFluege.add(Fluege.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, String fluggesellschaft) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft)) {
                zutreffendeFluege.add(Fluege.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort, Date abflugzeit, String fluggesellschaft) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort) && Fluege.getFluege().get(i).getAbflugzeit().equals(abflugzeit) && Fluege.getFluege().get(i).getFlugGesellschaft().equals(fluggesellschaft)) {
                zutreffendeFluege.add(Fluege.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
    }

    public ArrayList<Flug> getZutreffendeFluege(String abflugort, String ankunftsort) throws FlugNotFoundException {
        ArrayList<Flug> zutreffendeFluege = new ArrayList<>();
        for (int i = 0; i < Fluege.getFluege().size(); i++) {
            if (Fluege.getFluege().get(i).getAbflugort().equals(abflugort) && Fluege.getFluege().get(i).getAnkunftsort().equals(ankunftsort)) {
                zutreffendeFluege.add(Fluege.getFluege().get(i));
            }
        }
        if (zutreffendeFluege.size() < 0) {
            return zutreffendeFluege;
        } else {
            throw new FlugNotFoundException();
        }
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
