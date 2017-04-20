
package Model.Klassen.Verwaltung;
import Model.Enums.Gepaecktypen;
import Model.Exceptions.*;
import Model.Klassen.Elemente.Buchung;
import Model.Klassen.Elemente.Flug;
import Model.Klassen.Elemente.Gepaeck;
import Model.Klassen.Nutzer.Administrator;
import Model.Klassen.Nutzer.Angestellter;
import Model.Klassen.Nutzer.Anwender;
import Model.Klassen.Nutzer.Mensch;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by knoll on 14.03.2017.
 */

public abstract class Verwaltung {

    private static HashMap<Anwender, Angestellter> angestelltenAnwender = new HashMap<>();
    private static Mensch angemeldeter;


    public static void init() {
        try {
            fluegeEinlesen("Model/Daten/Fluege/Flugliste.csv");
            administratorenEinlesen("Model/Daten/Menschen/Admin.csv");
            angestellteEinlesen("Model/Daten/Menschen/Angestellter.csv");
            anwenderEinlesen("Model/Daten/Menschen/Anwender.csv");
            angestellteAnwenderEinlesen("");
            gepaeckEinlesen("Model/Daten/Gepaeck/Gepaecke.csv");
            buchungenEinlesen("Model/Daten/Buchungen/Buchungen.csv");
        } catch (final IOException e) {
            System.out.println("File not Found");
        } catch (final NutzerDoesNotExistException e) {
            System.out.println("User Does not Exist");
        } catch (final GepaeckDoesNotExist e) {
            System.out.println("Gepaeck Does not Exist");
        } catch (final FlugNotFoundException e) {
            System.out.println("Flight not found!");
        }
    }

    public static void exit() {
        try {
            buchungenSpeichern("Model/Daten/Buchungen/Buchungen.csv");
            administratorenSpeichern("Model/Daten/Menschen/Admin.csv");
            angestelltenSpeichern("Model/Daten/Menschen/Angestellter.csv");
            anwenderSpeichern("Model/Daten/Menschen/Anwender.csv");
            angestelltenAnwenderSpeichern("");
            gepaeckSpeichern("Model/Daten/Gepaeck/Gepaecke.csv");

        } catch (IOException e) {
            System.out.println("File not Found");
        }
        System.out.println("Alle Daten wurden erfolgreich gespeichert.\n\nDas Programm wird nun beendet!");
        System.exit(0);
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
            System.out.println("Fluege angelegt:" + eingelesenerFlug);
        }
        s.close();
    }

    public static void buchungenEinlesen(String pfad) throws IOException, NutzerDoesNotExistException, GepaeckDoesNotExist, FlugNotFoundException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Buchung eingeleseneBuchung;
            if (zs.length == 8) { //Mit Rueckflug
                if (zs[7].equals("0")) {
                    eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID((zs[1])), Fluege.getFlugByID((zs[2])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]), false);
                } else {
                    eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID((zs[1])), Fluege.getFlugByID((zs[2])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]), true);
                }
            } else { //Ohne Rueckflug
                if (zs[6].equals("0")) {
                    eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID((zs[1])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]), false);
                } else {
                    eingeleseneBuchung = new Buchung(Integer.parseInt(zs[0]), Fluege.getFlugByID((zs[1])), Anwenders.getAnwenderByID(Integer.parseInt(zs[3])), Integer.parseInt(zs[4]), Gepaecke.getGepaeckByID(Integer.parseInt(zs[5])), Double.parseDouble(zs[6]), true);
                }
            }
            Buchungen.addBuchung(eingeleseneBuchung);
            System.out.println("Buchung angelegt:" + eingeleseneBuchung);
        }
        s.close();
        Buchungen.setBuchungsCounter(getBiggestID("Buchung"));
    }

    public static void administratorenEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Administrator eingelesenerAdministrator = new Administrator(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Administratoren.addAndministrator(eingelesenerAdministrator);
            System.out.println("Administrator angelegt:" + eingelesenerAdministrator);
        }
        s.close();
        Administratoren.setAdminCounter(getBiggestID("Administrator"));
    }

    public static void angestellteEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Angestellter eingelesenerAngestellter = new Angestellter(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Angestellte.addAngestellter(eingelesenerAngestellter);
            System.out.println("Angestellter angelegt:" + eingelesenerAngestellter);
        }
        s.close();
        Angestellte.setAngestelltenCounter(getBiggestID("Angestellter"));
    }

    public static void anwenderEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Anwender eingelesenerAnwender = new Anwender(zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            Anwenders.addAnwender(eingelesenerAnwender);
            System.out.println("Anwender angelegt:" + eingelesenerAnwender);
        }
        s.close();
        Anwenders.setAnwenderCounter(getBiggestID("Anwender"));
    }

    public static void angestellteAnwenderEinlesen(String pfad) throws IOException, NutzerDoesNotExistException {
        //AnwenderAngestellter umgeändert
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            angestelltenAnwender.put(Anwenders.getAnwenderByID(Integer.parseInt(zs[0])), Angestellte.getAngestelltenByID(Integer.parseInt(zs[0])));
            System.out.println(Angestellte.getAngestelltenByID(Integer.parseInt(zs[0]))+" legte "+Anwenders.getAnwenderByID(Integer.parseInt(zs[0]))+" an");
        }
        s.close();
    }

    public static void gepaeckEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Gepaeck eingelesenesGepaeck;

            if(zs[3].equals(Gepaecktypen.Handgepaeck.toString())){
                eingelesenesGepaeck = new Gepaeck(Integer.parseInt(zs[1]), Double.parseDouble(zs[2]), Gepaecktypen.Handgepaeck);
            }
            else if(zs[3].equals(Gepaecktypen.Sportgepaeck.toString())){
                eingelesenesGepaeck = new Gepaeck(Integer.parseInt(zs[1]), Double.parseDouble(zs[2]), Gepaecktypen.Sportgepaeck);
            }
            else if(zs[3].equals(Gepaecktypen.Tasche.toString())){
                eingelesenesGepaeck = new Gepaeck(Integer.parseInt(zs[1]), Double.parseDouble(zs[2]), Gepaecktypen.Tasche);
            }
            else{
                eingelesenesGepaeck = new Gepaeck(Integer.parseInt(zs[1]), Double.parseDouble(zs[2]), Gepaecktypen.Koffer);
            }

            Gepaecke.addGepaeck(eingelesenesGepaeck);
            System.out.println("Gepaeck angelegt:" + eingelesenesGepaeck);

        }
        s.close();
        Gepaecke.setGepaeckecounter(getBiggestID("Gepaeck"));
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
            System.out.println("Speichern: "+Buchungen.getBuchungen().get(i));
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
            System.out.println("Speichern: "+Administratoren.getAdministratoren().get(i));
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
            System.out.println("Speichern: "+Administratoren.getAdministratoren().get(i));
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
            System.out.println("Speichern: "+Administratoren.getAdministratoren().get(i));
        }
        bw.close();
    }

    public static void angestelltenAnwenderSpeichern(String pfad) throws IOException {
        //AngesteltenAnwender ausgebessert
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (HashMap.Entry<Anwender, Angestellter> h : angestelltenAnwender.entrySet()) {
            bw.write(h.getKey().getAnwenderID() + ";" + h.getValue().getAngestelltenID());
            System.out.println("Speichern: " + h.getValue() + " " + h.getKey());
        }
    }

    public static void gepaeckSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < Gepaecke.getGepaecke().size(); i++) {
            bw.write(Gepaecke.getGepaecke().get(i).getGepaeckID());
            bw.write(";");
            bw.write(String.valueOf(Gepaecke.getGepaecke().get(i).getGewicht()));
            bw.write(";");
            bw.write(Gepaecke.getGepaecke().get(i).getGepaeckTyp().toString());
            System.out.println("Speichern: "+Administratoren.getAdministratoren().get(i));
        }
    }


    //Funktionelle Methoden
    public static void gepaeckErstellen(double gewicht, Gepaecktypen gepaeckTyp) throws ToHighWeightException {
        Gepaecke.addGepaeck(new Gepaeck(gewicht, gepaeckTyp));
    }

    public static void anwenderErstellen(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) throws InvalidEmailException, EmailIsAlreadyUsedException {
        //AnwenderAngestellte umgeändert
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
        Anwender a = new Anwender(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Anwenders.addAnwender(a);
        if(angemeldeter instanceof Angestellter){
            angestelltenAnwender.put(a,(Angestellter) angemeldeter);
        }
        System.out.println("Anwender angelegt:" + a);
    }

    public static void adminErstellten(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) throws InvalidEmailException, EmailIsAlreadyUsedException {
        assert !(angemeldeter instanceof Administrator);
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
        Administrator a = new Administrator(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Administratoren.addAndministrator(a);
        System.out.println("Administrator angelegt:" + a);
    }

    public static void angestellterErstellen(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort) throws InvalidEmailException, EmailIsAlreadyUsedException {
        assert !(angemeldeter instanceof Administrator);
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
        Angestellter a = new Angestellter(vorname, nachname, geburtsdatum, passnummer, eMail, passwort);
        Angestellte.addAngestellter(a);
        System.out.println("Angestellter angelegt:" + a);
    }

    public static void anwenderLoeschen(Anwender anwender) {
        //AnwenderAngestellter geändert
        for (int i = 0; i < Buchungen.getBuchungenByAnwender(anwender).size(); i++) {
            Gepaecke.removeGepaeck(Buchungen.getBuchungenByAnwender(anwender).get(i).getGepaeck());
            Buchungen.getBuchungen().remove(Buchungen.getBuchungenByAnwender(anwender).get(i));
        }
        for (HashMap.Entry<Anwender, Angestellter> h : angestelltenAnwender.entrySet()) {
            if (h.getKey().equals(anwender)) {
                angestelltenAnwender.remove(h.getKey());
            }
        }
        Anwenders.getAnwenders().remove(anwender);
    }

    public static void adminLoeschen(Administrator administrator) {
        Administratoren.getAdministratoren().remove(administrator);
    }

    public static void angestelltenLoeschen(Angestellter angestellter) {
        //AnwenderAngetsellter geändert
        for (int i = 0; i < Verwaltung.getAnwenderByAngestellten(angestellter).size(); i++) {
            angestelltenAnwender.remove(Verwaltung.getAnwenderByAngestellten(angestellter).get(i),angestellter);

        }
        Angestellte.getAngestellte().remove(angestellter);
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

    public static void anmelden(Anwender anwender) {
        //Sobald anwender auf registrieren klickt und er sich registriert sollte er direkt danach auch angemeldet sein
        //Diese Funktion erleichtert den GUI Programmierern das Registrieren/Anmelden
        //*Klick* Registrieren --> anwenderErstellen --> erstellten Anwender dieser Funktion übergeben
        angemeldeter = anwender;
        System.out.println("Erfolgreich Angemeldet:" + angemeldeter);
    }

    public static void buchungErstellen(Flug hinflug, Flug rueckflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung b = new Buchung(hinflug, rueckflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
        Buchungen.addBuchung(b);
        System.out.println("Buchung angelegt: " + b);
    }

    public static void buchungErstellen(Flug hinflug, Anwender anwender, int anzahlSitzplaetze, Gepaeck gepaeck, double buchungspreis, boolean createdByAnwender) {
        Buchung b = new Buchung(hinflug, anwender, anzahlSitzplaetze, gepaeck, buchungspreis, createdByAnwender);
        Buchungen.addBuchung(b);
        System.out.println("Buchung angelegt: " + b);
    }

    public static ArrayList<Anwender> getAnwenderByAngestellten(Angestellter angestellter) {
        //AnwenderAngetsellten geändert
        ArrayList<Anwender> l = new ArrayList<Anwender>();
        for (HashMap.Entry<Anwender, Angestellter> h : angestelltenAnwender.entrySet()) {
            if (h.getValue().equals(angestellter)) {
                l.add(h.getKey());
            }
        }
        return l;
    }

    public static boolean isAngemeldet() {
        return angemeldeter != null;
    }

    public static int getBiggestID(String eigenschaft) {
        int biggestID = 0;
        if (eigenschaft.equals("Gepaeck")) {
            for (int i = 0; i < Gepaecke.getGepaecke().size(); i++) {
                if (Gepaecke.getGepaecke().get(i).getGepaeckID() > biggestID)
                    biggestID = Gepaecke.getGepaecke().get(i).getGepaeckID();
            }
        } else if (eigenschaft.equals("Buchung")) {
            for (int i = 0; i < Buchungen.getBuchungen().size(); i++) {
                if (Buchungen.getBuchungen().get(i).getBuchungsID() > biggestID)
                    biggestID = Buchungen.getBuchungen().get(i).getBuchungsID();
            }
        } else if (eigenschaft.equals("Administrator")) {
            for (int i = 0; i < Administratoren.getAdministratoren().size(); i++) {
                if (Administratoren.getAdministratoren().get(i).getAdminID() > biggestID)
                    biggestID = Administratoren.getAdministratoren().get(i).getAdminID();
            }
        } else if (eigenschaft.equals("Angestellter")) {
            for (int i = 0; i < Angestellte.getAngestellte().size(); i++) {
                if (Angestellte.getAngestellte().get(i).getAngestelltenID() > biggestID)
                    biggestID = Angestellte.getAngestellte().get(i).getAngestelltenID();
            }
        } else if (eigenschaft.equals("Anwender")) {
            for (int i = 0; i < Anwenders.getAnwenders().size(); i++) {
                if (Anwenders.getAnwenders().get(i).getAnwenderID() > biggestID)
                    biggestID = Anwenders.getAnwenders().get(i).getAnwenderID();
            }
        }
        return biggestID;
    }


    //Getter
    public static HashMap<Anwender, Angestellter> getAngestelltenAnwender() {
        return angestelltenAnwender;
    }

    public static Mensch getAngemeldeter() {
        return angemeldeter;
    }


    //Setter
    public static void setAngemeldeter(Mensch angemeldeter) {
        Verwaltung.angemeldeter = angemeldeter;
    }

    public static void setAngestelltenAnwender(HashMap<Anwender,Angestellter> angestelltenAnwender) {
        Verwaltung.angestelltenAnwender = angestelltenAnwender;
    }
}