
import Model.Exceptions.BuchungDoesNotExistException;
import Model.Exceptions.EmailIsAlreadyUsedException;
import Model.Exceptions.InvalidEmailException;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.Verwaltung;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by jonas on 22.03.2017.
 */
public class VerwaltungTest {


    @Before
    public void before() {
        System.out.println("Teste einlesen...");
        //prüfen, ob anwender, angestellte und admins richtig eingelesen werden
        //Für Test müssen zuerst neue Anwenderm, Angestellte und Admins erstellt werden
        //Weitere Vorraussetzung: es müssen immer nur 2 Elemente in den Listen sein
        //,da davon ausgegangen wird, dass das die Methoder für beliebig viele Elemen
        //funktionieren, wenn sie für 2 Elemente funktionieren

        //Anwender erstellen, um dann zu vergleichen
        ArrayList<Anwender> anwender = new ArrayList<Anwender>();
        anwender.add(new Anwender(1, "Max", "Mustermann", "01.01.2000", 1111, "max.mustermann@test.com", "maxi123"));
        anwender.add(new Anwender(2, "Martina", "Musterfrau", "03.02.2000", 2222, "martina.musterfrau@test.com", "martina123"));

        try {
            Verwaltung.anwenderEinlesen("src\\Model\\Daten\\Menschen\\Anwender.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < anwender.size(); i++) {
            assertEquals(Verwaltung.getAnwender().get(i).getAnwenderID(), anwender.get(i).getAnwenderID());
            assertEquals(Verwaltung.getAnwender().get(i).getVorname(), anwender.get(i).getVorname());
            assertEquals(Verwaltung.getAnwender().get(i).getNachname(), anwender.get(i).getNachname());
            assertEquals(Verwaltung.getAnwender().get(i).getGeburtsdatum(), anwender.get(i).getGeburtsdatum());
            assertEquals(Verwaltung.getAnwender().get(i).getPassnummer(), anwender.get(i).getPassnummer());
            assertEquals(Verwaltung.getAnwender().get(i).geteMail(), anwender.get(i).geteMail());
            assertEquals(Verwaltung.getAnwender().get(i).getPasswort(), anwender.get(i).getPasswort());
        }

        //Angestellte
        ArrayList<Angestellter> angestellte = new ArrayList<Angestellter>();
        angestellte.add(new Angestellter(1, "Matthias", "Obergasser", "23.09.1999", 8274, "matti.climb@gmail.com", "mattrivals1999"));
        angestellte.add(new Angestellter(2, "Jonas", "Pfeifhofer", "06.11.1999", 3948, "jonas.pfeifhofer99@gmail.com", "jonaspf127"));

        try {
            Verwaltung.angestellteEinlesen("src\\Model\\Daten\\Menschen\\Angestellter.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < angestellte.size(); i++) {
            assertEquals(Verwaltung.getAngestellte().get(i).getAngestelltenID(), angestellte.get(i).getAngestelltenID());
            assertEquals(Verwaltung.getAngestellte().get(i).getVorname(), angestellte.get(i).getVorname());
            assertEquals(Verwaltung.getAngestellte().get(i).getNachname(), angestellte.get(i).getNachname());
            assertEquals(Verwaltung.getAngestellte().get(i).getGeburtsdatum(), angestellte.get(i).getGeburtsdatum());
            assertEquals(Verwaltung.getAngestellte().get(i).getPassnummer(), angestellte.get(i).getPassnummer());
            assertEquals(Verwaltung.getAngestellte().get(i).geteMail(), angestellte.get(i).geteMail());
            assertEquals(Verwaltung.getAngestellte().get(i).getPasswort(), angestellte.get(i).getPasswort());
        }

        //Administratoren
        ArrayList<Administrator> administratoren = new ArrayList<Administrator>();
        administratoren.add(new Administrator(1, "Marvin", "Knoll", "15.02.1999", 6276, "marvinknoll6@gmail.com", "osterpiegl123"));
        administratoren.add(new Administrator(2, "Jonas", "Pfeifhofer", "06.11.1999", 3948, "jonas.pfeifhofer99@gmail.com", "jonaspf12"));

        try {
            Verwaltung.administratorenEinlesen("src\\Model\\Daten\\Menschen\\Admin.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < administratoren.size(); i++) {
            assertEquals(Verwaltung.getAdministratoren().get(i).getAdminID(), administratoren.get(i).getAdminID());
            assertEquals(Verwaltung.getAdministratoren().get(i).getVorname(), administratoren.get(i).getVorname());
            assertEquals(Verwaltung.getAdministratoren().get(i).getNachname(), administratoren.get(i).getNachname());
            assertEquals(Verwaltung.getAdministratoren().get(i).getGeburtsdatum(), administratoren.get(i).getGeburtsdatum());
            assertEquals(Verwaltung.getAdministratoren().get(i).getPassnummer(), administratoren.get(i).getPassnummer());
            assertEquals(Verwaltung.getAdministratoren().get(i).geteMail(), administratoren.get(i).geteMail());
            assertEquals(Verwaltung.getAdministratoren().get(i).getPasswort(), administratoren.get(i).getPasswort());
        }

        /*
        //Buchungen
        ArrayList <Buchung> buchungen = new ArrayList<Buchung>();
        buchungen.add(new Buchung());
        buchungen.add(new Buchung());

        try {
            Verwaltung.buchungenEinlesen("C:\\Users\\jonas\\Documents\\Schule\\4BT\\TP\\Projekt Flughafen\\Flughafen-B1NER\\src\\Daten\\Buchungen\\Buchungen.csv");
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < buchungen.size(); i++) {
            assertEquals(verwaltung.getBuchungen().get(i).getBuchungsID(),buchungen.get(i).getBuchungsID());
            assertEquals(verwaltung.getBuchungen().get(i).getHinflugID(),buchungen.get(i).getHinflugID());
            assertEquals(verwaltung.getBuchungen().get(i).getRueckflugID(),buchungen.get(i).getRueckflugID());
            assertEquals(verwaltung.getBuchungen().get(i).getAnwenderID(),buchungen.get(i).getAnwenderID());
            assertEquals(verwaltung.getBuchungen().get(i).getAnzahlSitzplaetze(),buchungen.get(i).getAnzahlSitzplaetze());
            assertEquals(verwaltung.getBuchungen().get(i).getGepaeckID(),buchungen.get(i).getGepaeckID());
            //assertEquals(verwaltung.getBuchungen().get(i).getBuchungspreis(),buchungen.get(i).getBuchungspreis());
            assertEquals(verwaltung.getBuchungen().get(i).getAngestelltenID(),buchungen.get(i).getAngestelltenID());

        }*/
        /*
        //Flüge
        ArrayList <Flug> fluege = new ArrayList<Flug>();
        fluege.add(new Flug("LH3428","Lufthansa", "München","Berlin",150,   20000, new Date(14:00), 21.03.2017", "	21.03.2017 15:10	150));
        fluege.add(new Flug());
        fluege.add(new Flug());

        try {
            Verwaltung.fluegeEinlesen("C:\\Users\\jonas\\Documents\\Schule\\4BT\\TP\\Projekt Flughafen\\Flughafen-B1NER\\src\\Daten\\Fluege\\Flugliste.csv");
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < fluege.size(); i++) {
            assertEquals(verwaltung.getFluege().get(i).getFlugID(),fluege.get(i).getFlugID());
            assertEquals(verwaltung.getFluege().get(i).getFlugGesellschaft(),fluege.get(i).getFlugGesellschaft());
            assertEquals(verwaltung.getFluege().get(i).getAbflugort(),fluege.get(i).getAbflugort());
            assertEquals(verwaltung.getFluege().get(i).getAnkunftsort(),fluege.get(i).getAnkunftsort());
            assertEquals(verwaltung.getFluege().get(i).getAbflugzeit(),fluege.get(i).getAbflugzeit());
            assertEquals(verwaltung.getFluege().get(i).getAnkunftszeit(),fluege.get(i).getAnkunftszeit());
            assertEquals(verwaltung.getFluege().get(i).getAnzahlPlaetze(),fluege.get(i).getAnzahlPlaetze());
            assertEquals(verwaltung.getFluege().get(i).getGepaeckskapazitaet(),fluege.get(i).getGepaeckskapazitaet());
            assertEquals(verwaltung.getFluege().get(i).getPreisProPerson(),fluege.get(i).getPreisProPerson());
        }*/

    }

    @BeforeClass
    public static void beforeClass() {

    }

    @Test
    public void test() {
        System.out.println("Teste Funktionalitäten aus Klasse Verwaltung...");
        //testen der Funktionalitäten
        //prüfen, ob richtige Buchung zurückkommt
        Flug flug = new Flug("ID", "B1nerGMBH", "Brixen", "Bozen", 140, 1000, new Date(2017, 03, 22), new Date(2017, 04, 22), 130);
        Anwender anwender = new Anwender(12, "Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123");
        Gepaeck gepaeck = new Gepaeck(32, 1000, "Handgepaek");

        Buchung buchung = new Buchung(flug, anwender, 3, gepaeck, 140, false);
        Verwaltung.getBuchungen().add(buchung);

        try {
            //Bei Flug, Anwender und Gepäck reicht nur der Vergleich der IDs
            assertEquals(buchung.getBuchungsID(), Verwaltung.getBuchungByID(1).getBuchungsID());
            assertEquals(buchung.getHinflug().getFlugID(), Verwaltung.getBuchungByID(1).getHinflug().getFlugID());
            assertEquals(buchung.getAnwender().getAnwenderID(), Verwaltung.getBuchungByID(1).getAnwender().getAnwenderID());
            assertEquals(buchung.getAnzahlSitzplaetze(), Verwaltung.getBuchungByID(1).getAnzahlSitzplaetze());
            assertEquals(buchung.getGepaeck().getGepaeckID(), Verwaltung.getBuchungByID(1).getGepaeck().getGepaeckID());
            assertTrue(buchung.getBuchungspreis() == Verwaltung.getBuchungByID(1).getBuchungspreis());
            assertEquals(buchung.isCreatedByAnwender(), Verwaltung.getBuchungByID(1).isCreatedByAnwender());

        } catch (BuchungDoesNotExistException e) {
            e.printStackTrace();
        }

        Verwaltung.getBuchungen().remove(buchung);


        //prüfen, ob richtige Arraylist Buchungen zurückkommt, welche ein Anwender gebucht hat.
        anwender.getBuchungen().add(new Buchung(flug, anwender, 3, gepaeck, 178, false));
        anwender.getBuchungen().add(new Buchung(flug, anwender, 4, gepaeck, 134, true));
        anwender.getBuchungen().add(new Buchung(flug, anwender, 5, gepaeck, 147, true));


        //Bei Flug, Anwender und Gepäck reicht nur der Vergleich der IDs
        for (int i = 0; i < anwender.getBuchungen().size(); i++) {

            assertEquals(Verwaltung.getBuchungenByAnwenderID(12).get(i).getHinflug().getFlugID(), flug.getFlugID());
            assertEquals(Verwaltung.getBuchungenByAnwenderID(12).get(i).getGepaeck().getGepaeckID(), gepaeck.getGepaeckID());

            if (i == 0) {
                assertTrue(3 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getAnzahlSitzplaetze());
                assertTrue(178 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getBuchungspreis());
                assertEquals(false, Verwaltung.getBuchungenByAnwenderID(12).get(i).isCreatedByAnwender());
            } else if (i == 1) {
                assertTrue(4 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getAnzahlSitzplaetze());
                assertTrue(134 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getBuchungspreis());
                assertEquals(true, Verwaltung.getBuchungenByAnwenderID(12).get(i).isCreatedByAnwender());
            } else if (i == 2) {
                assertTrue(5 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getAnzahlSitzplaetze());
                assertTrue(147 == Verwaltung.getBuchungenByAnwenderID(12).get(i).getBuchungspreis());
                assertEquals(true, Verwaltung.getBuchungenByAnwenderID(12).get(i).isCreatedByAnwender());
            }

        }


        //prüfen, ob richtiger Angestellter zurückkommt
        Verwaltung.getAngestellte().add(new Angestellter(12, "Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123"));
        try {
            assertEquals("Jonas", Verwaltung.getAngestellterByAngestelltenID(12).getVorname());
            assertEquals("Pfeifhofer", Verwaltung.getAngestellterByAngestelltenID(12).getNachname());
            assertEquals("06.11.99", Verwaltung.getAngestellterByAngestelltenID(12).getGeburtsdatum());
            assertEquals(3342, Verwaltung.getAngestellterByAngestelltenID(12).getPassnummer());
            assertEquals("jonas@gmail.com", Verwaltung.getAngestellterByAngestelltenID(12).geteMail());
            assertEquals("asdf123", Verwaltung.getAngestellterByAngestelltenID(12).getPasswort());
        } catch (NutzerDoesNotExistException e) {
            e.printStackTrace();
        }


        //prüfen, ob richtiger Anwender zurückkommt
        Verwaltung.getAnwender().add(new Anwender(12, "Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123"));
        try {
            assertEquals("Jonas", Verwaltung.getAnwenderByAnwenderID(12).getVorname());
            assertEquals("Pfeifhofer", Verwaltung.getAnwenderByAnwenderID(12).getNachname());
            assertEquals("06.11.99", Verwaltung.getAnwenderByAnwenderID(12).getGeburtsdatum());
            assertEquals(3342, Verwaltung.getAnwenderByAnwenderID(12).getPassnummer());
            assertEquals("jonas@gmail.com", Verwaltung.getAnwenderByAnwenderID(12).geteMail());
            assertEquals("asdf123", Verwaltung.getAnwenderByAnwenderID(12).getPasswort());
        } catch (NutzerDoesNotExistException e) {
            e.printStackTrace();
        }

        /*
        //prüfen, ob richtige Anwender zurückkommt (Problem mit Hashmap)
        ArrayList <Anwender> anwenderArrayList = new ArrayList<>();
        anwenderArrayList.add(new Anwender());
        anwenderArrayList.add(new Anwender());
        anwenderArrayList.add(new Anwender());
        assertEquals(anwenderArrayList, Verwaltung.getAnwenderByAngestelltenID(10));
        */

        //prüfen, ob anwender richtig hinzugefügt wird und ob die credentials passen
        for (int i = 0; i < Verwaltung.getAnwender().size(); i++) {
            Verwaltung.getAnwender().remove(i);
        }

        try {
            Verwaltung.addAnwender("Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123");
            assertEquals(1, Verwaltung.getAnwender().get(0).getAnwenderID());
            assertEquals("Jonas", Verwaltung.getAnwender().get(0).getVorname());
            assertEquals("Pfeifhofer", Verwaltung.getAnwender().get(0).getNachname());
            assertEquals("06.11.99", Verwaltung.getAnwender().get(0).getGeburtsdatum());
            assertTrue(3342 == Verwaltung.getAnwender().get(0).getPassnummer());
            assertEquals("jonas@gmail.com", Verwaltung.getAnwender().get(0).geteMail());
            assertEquals("asdf123", Verwaltung.getAnwender().get(0).getPasswort());

        } catch (EmailIsAlreadyUsedException ee) {
            ee.printStackTrace();
        } catch (InvalidEmailException ie) {
            ie.printStackTrace();
        }

    }


}