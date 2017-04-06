package Model.Klassen.Verwaltung;


import Model.Exceptions.*;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Nutzer.Mensch;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by knoll on 14.03.2017.
 */

public abstract class Verwaltung {

    private static HashMap<Angestellter, Anwender> angestelltenAnwender = new HashMap<>();
    private static Mensch angemeldeter;


    public static void init() {
        try{
            fluegeEinlesen("");
            administratorenEinlesen("");
            angestellteEinlesen("");
            anwenderEinlesen("");
            angestellteAnwenderEinlesen("");
            gepaeckEinlesen("");
            buchungenEinlesen("");
        }
        catch(IOException e){
            System.out.println("File not Found");
        }
        catch(NutzerDoesNotExistException e){
            System.out.println("User Does not Exist");
        }
        catch(GepaeckDoesNotExist e){
            System.out.println("Gepaeck Does not Exist");
        }
    }


    //Einlesen der Daten
    public static void fluegeEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String[] zeile = s.nextLine().split("\\s+");
            Date abflugdatum = new Date(Integer.parseInt(zeile[4].split("\\.")[2]) - 1900, Integer.parseInt(zeile[4].split("\\.")[1]) - 1, Integer.parseInt(zeile[4].split("\\.")[0]), Integer.parseInt(zeile[5].split(":")[0]), Integer.parseInt(zeile[5].split(":")[1]));
            Date ankunftdatum = new Date(Integer.parseInt(zeile[6].split("\\.")[2]) - 1900, Integer.parseInt(zeile[6].split("\\.")[1]) - 1, Integer.parseInt(zeile[6].split("\\.")[0]), Integer.parseInt(zeile[7].split(":")[0]), Integer.parseInt(zeile[7].split(":")[1]));
            Flug eingelesenerFlug = new Flug(zeile[0], zeile[1], zeile[2], zeile[3], Integer.parseInt(zeile[8]), Integer.parseInt(zeile[9]), abflugdatum, ankunftdatum, 100);
            Fluege.addFlug(eingelesenerFlug);
        }
        s.close();
    }

    public static void buchungenEinlesen(String pfad) throws IOException, NutzerDoesNotExistException, GepaeckDoesNotExist {
        //Wichtig --> getByID + Liest zusätzlich noch Gepäck ein
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Buchung eingeleseneBuchung;
            if(zs[7].equals("0")){
                eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID(Integer.parseInt(zs[1])), Fluege.getFlugByID(Integer.parseInt(zs[2])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]),true);
            }
            else {
                eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID(Integer.parseInt(zs[1])), Fluege.getFlugByID(Integer.parseInt(zs[2])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]), true);
            }
            Buchungen.addBuchung(eingeleseneBuchung);
        }
        s.close();
    }

    public static void administratorenEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Administrator eingelesenerAdministrator = new Administrator(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Administratoren.addAndministrator(eingelesenerAdministrator);
        }
        s.close();
    }

    public static void angestellteEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Angestellter eingelesenerAngestellter = new Angestellter(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Angestellte.addAngestellter(eingelesenerAngestellter);
        }
        s.close();
    }

    public static void anwenderEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Anwender eingelesenerAnwender = new Anwender(zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Anwenders.addAnwender(eingelesenerAnwender);
        }
        s.close();
    }

    public static void angestellteAnwenderEinlesen(String pfad) throws IOException, NutzerDoesNotExistException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            angestelltenAnwender.put(Angestellte.getAngestelltenByID(Integer.parseInt(zs[0])),Anwenders.getAnwenderByID(Integer.parseInt(zs[0])));
        }
        s.close();
    }

    public static void gepaeckEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            //gepaeckErstellen();
        }
        s.close();
    }


    //Speichern der Daten
    public static void buchungenSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Buchungen.getBuchungen().size(); i++) {
            bw.write(String.valueOf(Buchungen.getBuchungen().get(i).getBuchungsID()));
            bw.write(';');
            bw.write(Buchungen.getBuchungen().get(i).getHinflug().getFlugID());
            bw.write(';');
            bw.write(Buchungen.getBuchungen().get(i).getRueckflug().getFlugID());
            bw.write(';');
            bw.write(String.valueOf(Buchungen.getBuchungen().get(i).getAnwender().getAnwenderID()));
            bw.write(';');
            bw.write(String.valueOf(Buchungen.getBuchungen().get(i).getAnzahlSitzplaetze()));
            bw.write(';');
            bw.write(String.valueOf(Buchungen.getBuchungen().get(i).getGepaeck().getGepaeckID()));
            bw.write(';');
            bw.write(String.valueOf(Buchungen.getBuchungen().get(i).getBuchungspreis()));
            bw.write(';');
            if (Buchungen.getBuchungen().get(i).isCreatedByAnwender()) {
                bw.write(String.valueOf(0));
            } else {
                bw.write(String.valueOf(1));
            }
            bw.write(';');
            bw.write('\n');
            System.out.println(Buchungen.getBuchungen().get(i));
        }
        bw.close();
    }

    public static void administratorenSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Administratoren.getAdministratoren().size(); i++) {
            bw.write(String.valueOf(Administratoren.getAdministratoren().get(i).getAdminID()));
            bw.write(';');
            bw.write(Administratoren.getAdministratoren().get(i).getVorname());
            bw.write(';');
            bw.write(Administratoren.getAdministratoren().get(i).getNachname());
            bw.write(';');
            bw.write(Administratoren.getAdministratoren().get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(Administratoren.getAdministratoren().get(i).getPassnummer()));
            bw.write(';');
            bw.write(Administratoren.getAdministratoren().get(i).geteMail());
            bw.write(';');
            bw.write(Administratoren.getAdministratoren().get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(Administratoren.getAdministratoren().get(i));
        }
        bw.close();
    }

    public static void angestelltenSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Angestellte.getAngestellte().size(); i++) {
            bw.write(String.valueOf(Angestellte.getAngestellte().get(i).getAngestelltenID()));
            bw.write(';');
            bw.write(Angestellte.getAngestellte().get(i).getVorname());
            bw.write(';');
            bw.write(Angestellte.getAngestellte().get(i).getNachname());
            bw.write(';');
            bw.write(Angestellte.getAngestellte().get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(Angestellte.getAngestellte().get(i).getPassnummer()));
            bw.write(';');
            bw.write(Angestellte.getAngestellte().get(i).geteMail());
            bw.write(';');
            bw.write(Angestellte.getAngestellte().get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(Angestellte.getAngestellte().get(i));
        }
        bw.close();
    }

    public static void anwenderSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Anwenders.getAnwenders().size(); i++) {
            bw.write(String.valueOf(Anwenders.getAnwenders().get(i).getAnwenderID()));
            bw.write(';');
            bw.write(Anwenders.getAnwenders().get(i).getVorname());
            bw.write(';');
            bw.write(Anwenders.getAnwenders().get(i).getNachname());
            bw.write(';');
            bw.write(Anwenders.getAnwenders().get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(Anwenders.getAnwenders().get(i).getPassnummer()));
            bw.write(';');
            bw.write(Anwenders.getAnwenders().get(i).geteMail());
            bw.write(';');
            bw.write(Anwenders.getAnwenders().get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(Anwenders.getAnwenders().get(i));
        }
        bw.close();
    }

    public static void angestelltenAnwenderSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (HashMap.Entry<Angestellter, Anwender> h : angestelltenAnwender.entrySet()) {
            bw.write(h.getKey().getAngestelltenID()+";"+h.getValue().getAnwenderID());
        }
    }

    public static void gepaeckSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Gepaecke.getGepaecke().size(); i++) {
            bw.write(Gepaecke.getGepaecke().get(i).getGepaeckID());
            bw.write(";");
            bw.write(String.valueOf(Gepaecke.getGepaecke().get(i).getGewicht()));
            bw.write(";");
            bw.write(Gepaecke.getGepaecke().get(i).getGepaeckTyp());
        }
    }


    //Funktionelle Methoden
    public static void gepaeckErstellen() throws ToHighWeightException {

    }

    public static void anwenderErstellen(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) throws InvalidEmailException, EmailIsAlreadyUsedException {
        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(eMail);

        if (!matcher.matches()) {
            throw new InvalidEmailException();
        }

        for (int i = 0; i < Administratoren.getAdministratoren().size(); i++) {
            if (Administratoren.getAdministratoren().get(i).geteMail().equals(eMail)) {
                throw new EmailIsAlreadyUsedException();
            }
        }
        for (int i = 0; i < Angestellte.getAngestellte().size(); i++) {
            if (Angestellte.getAngestellte().get(i).geteMail().equals(eMail)) {
                throw new EmailIsAlreadyUsedException();
            }
        }
        for (int i = 0; i < Anwenders.getAnwenders().size(); i++) {
            if (Anwenders.getAnwenders().get(i).geteMail().equals(eMail)) {
                throw new EmailIsAlreadyUsedException();
            }
        }
        Anwenders.addAnwender(new Anwender(vorname, nachname, geburtsdatum, passnummer, eMail, passwort));
        System.out.println("Anwender:" + vorname + " " + nachname + " " + " added\t -->" + eMail);
    }

    public static void adminErstellten() throws InvalidEmailException, EmailIsAlreadyUsedException {

    }

    public static void angestellterErstellen() throws InvalidEmailException, EmailIsAlreadyUsedException {

    }

    public static void anmelden(String eMail, String password) throws NutzerDoesNotExistException {
        for (int i = 0; i < Administratoren.getAdministratoren().size(); i++) {
            if (Administratoren.getAdministratoren().get(i).geteMail().equals(eMail) && Administratoren.getAdministratoren().get(i).getPasswort().equals(password)) {
                angemeldeter = Administratoren.getAdministratoren().get(i);
                System.out.println("Erfolgreich Angemeldet:" + angemeldeter);
                return;
            }
        }
        if (angemeldeter == null) {
            for (int i = 0; i < Angestellte.getAngestellte().size(); i++) {
                if (Angestellte.getAngestellte().get(i).geteMail().equals(eMail) && Angestellte.getAngestellte().get(i).getPasswort().equals(password)) {
                    angemeldeter = Angestellte.getAngestellte().get(i);
                    System.out.println("Erfolgreich Angemeldet:" + angemeldeter);
                    return;
                }
            }
        }
        if (angemeldeter == null) {
            for (int i = 0; i < Anwenders.getAnwenders().size(); i++) {
                if (Anwenders.getAnwenders().get(i).geteMail().equals(eMail) && Anwenders.getAnwenders().get(i).getPasswort().equals(password)) {
                    angemeldeter = Anwenders.getAnwenders().get(i);
                    System.out.println("Erfolgreich Angemeldet:" + angemeldeter);
                    return;
                }
            }
        }

        throw new NutzerDoesNotExistException();
    }

    public static void anmelden(Anwender anwender){
        //Sobald anwender auf registrieren klickt und er sich registriert sollte er direkt danach auch angemeldet sein
        //Diese Funktion erleichtert den GUI Programmierern das Registrieren/Anmelden
        //*Klick* Registrieren --> anwenderErstellen --> erstellten Anwender dieser Funktion übergeben
        angemeldeter = anwender;
    }

    public static void buchungErstellen(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchungen.addBuchung(new Buchung(hinflug, rueckflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender));
    }

    public static void buchungErstellen(Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchungen.addBuchung(new Buchung(hinflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender));
    }

    public static ArrayList<Anwender> getAngestelltenByAnwender(Angestellter angestellter) {
        ArrayList<Anwender> l = new ArrayList<Anwender>();
        for (HashMap.Entry<Angestellter, Anwender> h : angestelltenAnwender.entrySet()) {
            if (h.getKey().equals(angestellter)) {
                l.add(h.getValue());
            }
        }
        return l;
    }

    public static boolean isAngemeldet() {
        return angemeldeter != null;
    }


    //Getter
    public static HashMap<Angestellter, Anwender> getAngestelltenAnwender() {
        return angestelltenAnwender;
    }

    public static Mensch getAngemeldeter() {
        return angemeldeter;
    }


    //Setter
    public static void setAngemeldeter(Mensch angemeldeter) {
        Verwaltung.angemeldeter = angemeldeter;
    }

    public static void setAngestelltenAnwender(HashMap<Angestellter, Anwender> angestelltenAnwender) {
        Verwaltung.angestelltenAnwender = angestelltenAnwender;
    }
}
























