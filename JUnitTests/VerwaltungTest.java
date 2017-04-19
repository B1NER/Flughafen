
import Model.Enums.Gepaecktypen;
import Model.Exceptions.*;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Verwaltung.*;

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

        ArrayList<Anwender> anwender = new ArrayList<Anwender>();

        //prüfen, ob anwender, angestellte und admins richtig eingelesen werden
        //Für Test müssen zuerst neue Anwenderm, Angestellte und Admins erstellt werden
        //Weitere Vorraussetzung: es müssen immer nur 2 Elemente in den Listen sein
        //,da davon ausgegangen wird, dass das die Methoder für beliebig viele Elemen
        //funktionieren, wenn sie für 2 Elemente funktionieren

        //Anwender erstellen, um dann zu vergleichen
        anwender.add(new Anwender("Max", "Mustermann", "01.01.2000", 1111, "max.mustermann@test.com", "maxi123"));
        anwender.add(new Anwender("Martina", "Musterfrau", "03.02.2000", 2222, "martina.musterfrau@test.com", "martina123"));

        try {
            Verwaltung.anwenderEinlesen("src\\Model\\Daten\\Menschen\\Anwender.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < anwender.size(); i++) {
            //assertEquals(i, Anwenders.getAnwenders().get(i).getAnwenderID());
            assertEquals(anwender.get(i).getVorname(), Anwenders.getAnwenders().get(i).getVorname());
            assertEquals(anwender.get(i).getNachname(), Anwenders.getAnwenders().get(i).getNachname());
            assertEquals(anwender.get(i).getGeburtsdatum(), Anwenders.getAnwenders().get(i).getGeburtsdatum());
            assertEquals(anwender.get(i).getPassnummer(), Anwenders.getAnwenders().get(i).getPassnummer());
            assertEquals(anwender.get(i).geteMail(), Anwenders.getAnwenders().get(i).geteMail());
            assertEquals(anwender.get(i).getPasswort(), Anwenders.getAnwenders().get(i).getPasswort());
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
            //assertEquals(i, Angestellte.getAngestellte().get(i).getAngestelltenID());
            assertEquals(angestellte.get(i).getVorname(), Angestellte.getAngestellte().get(i).getVorname());
            assertEquals(angestellte.get(i).getNachname(), Angestellte.getAngestellte().get(i).getNachname());
            assertEquals(angestellte.get(i).getGeburtsdatum(), Angestellte.getAngestellte().get(i).getGeburtsdatum());
            assertEquals(angestellte.get(i).getPassnummer(), Angestellte.getAngestellte().get(i).getPassnummer());
            assertEquals(angestellte.get(i).geteMail(), Angestellte.getAngestellte().get(i).geteMail());
            assertEquals(angestellte.get(i).getPasswort(), Angestellte.getAngestellte().get(i).getPasswort());
        }

        //Administratoren
        ArrayList<Administrator> administratoren = new ArrayList<Administrator>();
        administratoren.add(new Administrator(1, "Marvin", "Knoll", "15.02.1999", 6276, "marvinknoll6@gmail.com", "osterpiegl123"));
        administratoren.add(new Administrator(2, "Jonas", "Pfeifhofer", "06.11.1999", 3948, "jonas.pfeifhofer99@gmail.com", "jonaspf127"));

        try {
            Verwaltung.administratorenEinlesen("src\\Model\\Daten\\Menschen\\Admin.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < administratoren.size(); i++) {
            //assertEquals(i, Administratoren.getAdministratoren().get(i).getAdminID());
            assertEquals(administratoren.get(i).getVorname(), Administratoren.getAdministratoren().get(i).getVorname());
            assertEquals(administratoren.get(i).getNachname(), Administratoren.getAdministratoren().get(i).getNachname());
            assertEquals(administratoren.get(i).getGeburtsdatum(), Administratoren.getAdministratoren().get(i).getGeburtsdatum());
            assertEquals(administratoren.get(i).getPassnummer(), Administratoren.getAdministratoren().get(i).getPassnummer());
            assertEquals(administratoren.get(i).geteMail(), Administratoren.getAdministratoren().get(i).geteMail());
            assertEquals(administratoren.get(i).getPasswort(), Administratoren.getAdministratoren().get(i).getPasswort());
        }

        //Buchungen
        /*
        ArrayList <Buchung> buchungen = new ArrayList<Buchung>();
        Flug flug = new Flug("LH3428", "Lufthansa", "München", "Berlin", 150, 20000, new Date(2017 - 1900, 2, 21, 14, 0), new Date(2017 - 1900, 2, 21, 15, 10), 150);
        Gepaeck gepaeck = new Gepaeck(32, 1000, Gepaecktypen.Handgepaeck);
        Anwender a = new Anwender("Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123");
        buchungen.add(new Buchung(flug, a, 3, gepaeck, 178, false));

       // buchungen.add(new Buchung());

        try {
            Verwaltung.buchungenEinlesen("src\\Model\\Daten\\Buchungen\\Buchungen.csv");
        }catch (NutzerDoesNotExistException e){
            e.printStackTrace();
        }
        catch (GepaeckDoesNotExist e){
            e.printStackTrace();
        }
        catch (FlugNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        for (int i = 0; i < buchungen.size(); i++) {

            assertEquals(buchungen.get(i).getBuchungsID(), Buchungen.getBuchungen().get(i).getBuchungsID());
            assertEquals(buchungen.get(i).getHinflug().getFlugID(), Buchungen.getBuchungen().get(i).getHinflug().getFlugID());
            assertEquals(buchungen.get(i).getRueckflug().getFlugID(), Buchungen.getBuchungen().get(i).getRueckflug().getFlugID());
            assertEquals(buchungen.get(i).getAnwender().getAnwenderID(), Buchungen.getBuchungen().get(i).getAnwender().getAnwenderID());
            assertEquals(buchungen.get(i).getAnzahlSitzplaetze(), Buchungen.getBuchungen().get(i).getAnzahlSitzplaetze());
            assertEquals(buchungen.get(i).getGepaeck().getGepaeckID(), Buchungen.getBuchungen().get(i).getGepaeck().getGepaeckID());
            assertTrue(buchungen.get(i).getBuchungspreis() == Buchungen.getBuchungen().get(i).getBuchungspreis());

        }
        */
        //Flüge
        ArrayList<Flug> fluege = new ArrayList<Flug>();
        fluege.add(new Flug("LH3428", "Lufthansa", "München", "Berlin", 150, 20000, new Date(2017 - 1900, 2, 21, 14, 0), new Date(2017 - 1900, 2, 21, 15, 10), 150));
        fluege.add(new Flug("EJ1221", "EasyJet", "London", "Innsbruck", 130, 17000, new Date(2017 - 1900, 2, 22, 15, 0), new Date(2017 - 1900, 2, 22, 16, 55), 150));
        fluege.add(new Flug("EJ1222", "EasyJet", "Innsbruck", "London", 130, 17000, new Date(2017 - 1900, 2, 22, 19, 0), new Date(2017 - 1900, 2, 22, 16, 45), 150));


        try {
            Verwaltung.fluegeEinlesen("src\\Model\\Daten\\Fluege\\Flugliste.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < fluege.size(); i++) {
            assertEquals(fluege.get(i).getFlugID(), Fluege.getFluege().get(i).getFlugID());
            assertEquals(fluege.get(i).getFlugGesellschaft(), Fluege.getFluege().get(i).getFlugGesellschaft());
            assertEquals(fluege.get(i).getAbflugort(), Fluege.getFluege().get(i).getAbflugort());
            assertEquals(fluege.get(i).getAnkunftsort(), Fluege.getFluege().get(i).getAnkunftsort());
            assertEquals(fluege.get(i).getAbflugzeit(), Fluege.getFluege().get(i).getAbflugzeit());
            assertEquals(fluege.get(i).getAnkunftszeit(), Fluege.getFluege().get(i).getAnkunftszeit());
            assertEquals(fluege.get(i).getAnzahlPlaetze(), Fluege.getFluege().get(i).getAnzahlPlaetze());
            assertEquals(fluege.get(i).getGepaeckskapazitaet(), Fluege.getFluege().get(i).getGepaeckskapazitaet());
            assertTrue(fluege.get(i).getPreisProPerson() == 150);
        }


    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass");
    }

    @Test
    public void test() {

        //testen der Funktionalitäten
        //prüfen, ob richtige Buchung zurückkommt
        Flug flug = new Flug("ID", "B1nerGMBH", "Brixen", "Bozen", 140, 1000, new Date(2017, 03, 22), new Date(2017, 04, 22), 130);
        Anwender anwender = new Anwender("Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas@gmail.com", "asdf123");
        Gepaeck gepaeck = new Gepaeck(32, 1000, Gepaecktypen.Handgepaeck);

        Buchung buchung = new Buchung(flug, anwender, 3, gepaeck, 140, false);

        Buchungen.getBuchungen().add(buchung);

        //Bei Flug, Anwender und Gepäck reicht nur der Vergleich der IDs
        try {
            assertEquals(buchung.getBuchungsID(), Buchungen.getBuchungByID(1).getBuchungsID());
            assertEquals(buchung.getHinflug().getFlugID(), Buchungen.getBuchungByID(1).getHinflug().getFlugID());
            assertEquals(buchung.getAnwender().getAnwenderID(), Buchungen.getBuchungByID(1).getAnwender().getAnwenderID());
            assertEquals(buchung.getAnzahlSitzplaetze(), Buchungen.getBuchungByID(1).getAnzahlSitzplaetze());
            assertEquals(buchung.getGepaeck().getGepaeckID(), Buchungen.getBuchungByID(1).getGepaeck().getGepaeckID());
            assertTrue(buchung.getBuchungspreis() == Buchungen.getBuchungByID(1).getBuchungspreis());
            assertEquals(buchung.isCreatedByAnwender(), Buchungen.getBuchungByID(1).isCreatedByAnwender());
        }catch (BuchungDoesNotExistException e){
            e.printStackTrace();
        }


        Buchungen.getBuchungen().remove(buchung);


        //prüfen, ob richtige Buchungen zurückkommen
        ArrayList<Buchung> buchungs = new ArrayList<>();
        buchungs.add(new Buchung(2, flug, anwender, 3, gepaeck, 178, false));
        buchungs.add(new Buchung(3, flug, anwender, 4, gepaeck, 134, true));
        buchungs.add(new Buchung(4, flug, anwender, 5, gepaeck, 147, true));

        Verwaltung.buchungErstellen(flug, anwender, 3, gepaeck, 178, false);
        Verwaltung.buchungErstellen(flug, anwender, 4, gepaeck, 134, true);
        Verwaltung.buchungErstellen(flug, anwender, 5, gepaeck, 147, true);

        //Bei Flug, Anwender und Gepäck reicht nur der Vergleich der IDs
        for (int i = 0; i < buchungs.size(); i++) {
            //oben erstellter anwender mit ID 17
            if (i == 0) {
                assertEquals(2, Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungsID());
                assertEquals(3, Buchungen.getBuchungenByAnwender(anwender).get(i).getAnzahlSitzplaetze());
                assertTrue(178 == Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungspreis());
                assertTrue(!Buchungen.getBuchungenByAnwender(anwender).get(i).isCreatedByAnwender());
            } else if (i == 1) {
                assertEquals(3, Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungsID());
                assertEquals(4, Buchungen.getBuchungenByAnwender(anwender).get(i).getAnzahlSitzplaetze());
                assertTrue(134 == Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungspreis());
                assertTrue(Buchungen.getBuchungenByAnwender(anwender).get(i).isCreatedByAnwender());
            } else if (i == 2) {
                assertEquals(4, Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungsID());
                assertEquals(5, Buchungen.getBuchungenByAnwender(anwender).get(i).getAnzahlSitzplaetze());
                assertTrue(147 == Buchungen.getBuchungenByAnwender(anwender).get(i).getBuchungspreis());
                assertTrue(Buchungen.getBuchungenByAnwender(anwender).get(i).isCreatedByAnwender());
            }

            assertEquals(flug.getFlugID(), Buchungen.getBuchungenByAnwender(anwender).get(i).getHinflug().getFlugID());
            assertEquals(anwender.getAnwenderID(), Buchungen.getBuchungenByAnwender(anwender).get(i).getAnwender().getAnwenderID());
            assertEquals(gepaeck.getGepaeckID(), Buchungen.getBuchungenByAnwender(anwender).get(i).getGepaeck().getGepaeckID());

        }


        //prüfen, ob richtiger Angestellter zurückkommt
        Angestellte.getAngestellte().add(new Angestellter(74, "Matthias", "Obergasser", "23.09.1999", 8274, "matti.climb@gmail.com", "mattrivals1999"));
        try{

            assertTrue(74 == Angestellte.getAngestelltenByID(74).getAngestelltenID());
            assertEquals("Matthias", Angestellte.getAngestelltenByID(74).getVorname());
            assertEquals("Obergasser", Angestellte.getAngestelltenByID(74).getNachname());
            assertEquals("23.09.1999", Angestellte.getAngestelltenByID(74).getGeburtsdatum());
            assertTrue(8274 == Angestellte.getAngestelltenByID(74).getPassnummer());
            assertEquals("matti.climb@gmail.com", Angestellte.getAngestelltenByID(74).geteMail());
            assertEquals("mattrivals1999", Angestellte.getAngestelltenByID(74).getPasswort());

        }catch (NutzerDoesNotExistException e){
            e.printStackTrace();
        }


        //prüfen, ob richtige Anwender zurückkommt
        Angestellter angestellter = new Angestellter(74, "Matthias", "Obergasser", "23.09.1999", 8274, "matti.climb@gmail.com", "mattrivals1999");
        Verwaltung.setAngemeldeter(angestellter);
        try {
            Verwaltung.anwenderErstellen("Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas99@gmail.com", "asdf123");
            //Verwaltung.anwenderErstellen("Matti", "Obergasser", "23.09.1999", 8274, "matti.climb99@gmail.com", "mattrivals1999");
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayList <Anwender> anwenderArrayList = new ArrayList<>();
        anwenderArrayList.add(new Anwender("Jonas", "Pfeifhofer", "06.11.99", 3342, "jonas99@gmail.com", "asdf123"));
        anwenderArrayList.add(new Anwender("Matti", "Obergasser", "23.09.1999", 8274, "matti.climb99@gmail.com", "mattrivals1999"));

        System.out.println("\n\n" + Verwaltung.getAngestelltenAnwender() + "\n\n");

        for (int i = 0; i < anwenderArrayList.size(); i++) {
            /*
            assertEquals(anwenderArrayList.get(i).getVorname(), Verwaltung.getAnwenderByAngestellten(angestellter).get(i).getVorname());
            assertEquals(anwenderArrayList.get(i).getNachname(), Verwaltung.getAnwenderByAngestellten(angestellter).get(i).getNachname());
            assertEquals(anwenderArrayList.get(i).getGeburtsdatum(), Verwaltung.getAnwenderByAngestellten(angestellter).get(i).getGeburtsdatum());
            assertTrue(anwenderArrayList.get(i).getPassnummer() == Verwaltung.getAnwenderByAngestellten(angestellter).get(i).getPassnummer());
            assertEquals(anwenderArrayList.get(i).geteMail(), Verwaltung.getAnwenderByAngestellten(angestellter).get(i).geteMail());
            assertEquals(anwenderArrayList.get(i).getPasswort(), Verwaltung.getAnwenderByAngestellten(angestellter).get(i).getPasswort());
            */
            //System.out.println(Verwaltung.getAnwenderByAngestellten(angestellter).get(1));
        }
    }


}
