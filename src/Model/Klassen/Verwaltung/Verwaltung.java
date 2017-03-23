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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by knoll on 14.03.2017.
 */

public class Verwaltung {
    private static ArrayList<Buchung> buchungen = new ArrayList<>();
    private static ArrayList<Gepaeck> gepaecke = new ArrayList<>();
    private static ArrayList<Angestellter> angestellte = new ArrayList<>();
    private static ArrayList<Anwender> anwender = new ArrayList<>();
    private static ArrayList<Administrator> administratoren = new ArrayList<>();
    private static ArrayList<Flug> fluege = new ArrayList<>();
    private static HashMap<Angestellter, Anwender> angestelltenAnwender = new HashMap<>();
    private static Mensch angemeldeter;


    public static void init() {

        /*      MATT RIVALS TRY CATCH BLOCK
        try{
            addAnwender("Matthias","Obergasser","23.09.1999",1234,"matti.climb@gmail.com","mattrivals1999");
            //addAnwender("Matthias","Obergasser","23.09.1999",1234,"matti.climb@gmail.com","mattrivals1999");
            getAnwenderByAnwenderID(1);
            angestelltenSpeichern("src\\test.csv");

            anmelden("matti.climb@gmail.com","mattrivals1999");

        }catch(IOException e){
            System.out.println("File not Found");
        }
        catch(EmailIsAlreadyUsedException e){
            System.out.println("Email ist bereits in Verwendung");
        }
        catch(InvalidEmailException e){
            System.out.println("Email ungültig");
        }
        catch(NutzerDoesNotExistException e){
            System.out.println("Username und/oder Password falsch");
        }
        */
    }


    //Einlesen der Model.Daten
    public static void fluegeEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String[] zeile = s.nextLine().split("\\s+");
            Date abflugdatum = new Date(Integer.parseInt(zeile[4].split("\\.")[2]) - 1900, Integer.parseInt(zeile[4].split("\\.")[1]) - 1, Integer.parseInt(zeile[4].split("\\.")[0]), Integer.parseInt(zeile[5].split(":")[0]), Integer.parseInt(zeile[5].split(":")[1]));
            Date ankunftdatum = new Date(Integer.parseInt(zeile[6].split("\\.")[2]) - 1900, Integer.parseInt(zeile[6].split("\\.")[1]) - 1, Integer.parseInt(zeile[6].split("\\.")[0]), Integer.parseInt(zeile[7].split(":")[0]), Integer.parseInt(zeile[7].split(":")[1]));
            Flug eingelesenerFlug = new Flug(zeile[0], zeile[1], zeile[2], zeile[3], Integer.parseInt(zeile[8]), Integer.parseInt(zeile[9]), abflugdatum, ankunftdatum, 100);
            fluege.add(eingelesenerFlug);
        }
        s.close();
    }

    public static void buchungenEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            boolean createdByAnwender = true;
            int angestelltenID = 0;
            if (zs[7].equals("0")) {
                createdByAnwender = false;
                angestelltenID = Integer.parseInt(zs[7]);
            }

            Buchung eingeleseneBuchung = null; //new Buchung(Integer.parseInt(zs[0]), Integer.parseInt(zs[1]), Integer.parseInt(zs[2]), Integer.parseInt(zs[3]), Integer.parseInt(zs[4]), Integer.parseInt(zs[5]), Double.parseDouble(zs[6]), createdByAnwender);
            buchungen.add(eingeleseneBuchung);
            System.out.println(eingeleseneBuchung.toString());
        }
        s.close();
    }

    public static void administratorenEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Administrator eingelesenerAdministrator = new Administrator(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            administratoren.add(eingelesenerAdministrator);
        }
        s.close();
    }

    public static void angestellteEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Angestellter eingelesenerAngestellter = new Angestellter(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            angestellte.add(eingelesenerAngestellter);
        }
        s.close();
    }

    public static void anwenderEinlesen(String pfad) throws IOException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(pfad)));
        while (s.hasNext()) {
            String zeile = s.nextLine();
            String zs[] = zeile.split(";");
            Anwender eingelesenerAnwender = new Anwender(Integer.parseInt(zs[0]), zs[1], zs[2], zs[3], Integer.parseInt(zs[4]), zs[5], zs[6]);
            anwender.add(eingelesenerAnwender);
        }
        s.close();
    }

    public static void angestellteAnwenderEinlesen(String pfad) throws IOException {

    }


    //Speichern der Model.Daten
    public static void angestelltenAnwenderSpeichern(String pfad) throws IOException {

    }

    public static void buchungenSpeichern(String pfad) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < buchungen.size(); i++) {
            bw.write(String.valueOf(buchungen.get(i).getBuchungsID()));
            bw.write(';');
            bw.write(buchungen.get(i).getHinflug().getFlugID());
            bw.write(';');
            bw.write(buchungen.get(i).getRueckflug().getFlugID());
            bw.write(';');
            bw.write(String.valueOf(buchungen.get(i).getAnwender().getAnwenderID()));
            bw.write(';');
            bw.write(String.valueOf(buchungen.get(i).getAnzahlSitzplaetze()));
            bw.write(';');
            bw.write(String.valueOf(buchungen.get(i).getGepaeck().getGepaeckID()));
            bw.write(';');
            bw.write(String.valueOf(buchungen.get(i).getBuchungspreis()));
            bw.write(';');
            if(buchungen.get(i).isCreatedByAnwender()){
                bw.write(String.valueOf(0));
            }else{
                bw.write(String.valueOf(1));
            }
            bw.write(';');
            bw.write('\n');
            System.out.println(buchungen.get(i));
        }
        bw.close();
    }

    public static void administratorenSpeichern(String pfad) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < administratoren.size(); i++) {
            bw.write(String.valueOf(administratoren.get(i).getAdminID()));
            bw.write(';');
            bw.write(administratoren.get(i).getVorname());
            bw.write(';');
            bw.write(administratoren.get(i).getNachname());
            bw.write(';');
            bw.write(administratoren.get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(administratoren.get(i).getPassnummer()));
            bw.write(';');
            bw.write(administratoren.get(i).geteMail());
            bw.write(';');
            bw.write(administratoren.get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(administratoren.get(i));
        }
        bw.close();
    }

    public static void angestelltenSpeichern(String pfad) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < angestellte.size(); i++) {
            bw.write(String.valueOf(angestellte.get(i).getAngestelltenID()));
            bw.write(';');
            bw.write(angestellte.get(i).getVorname());
            bw.write(';');
            bw.write(angestellte.get(i).getNachname());
            bw.write(';');
            bw.write(angestellte.get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(angestellte.get(i).getPassnummer()));
            bw.write(';');
            bw.write(angestellte.get(i).geteMail());
            bw.write(';');
            bw.write(angestellte.get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(angestellte.get(i));
        }
        bw.close();
    }

    public static void anwenderSpeichern(String pfad) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(pfad));
        for (int i = 0; i < anwender.size(); i++) {
            bw.write(String.valueOf(anwender.get(i).getAnwenderID()));
            bw.write(';');
            bw.write(anwender.get(i).getVorname());
            bw.write(';');
            bw.write(anwender.get(i).getNachname());
            bw.write(';');
            bw.write(anwender.get(i).getGeburtsdatum());
            bw.write(';');
            bw.write(String.valueOf(anwender.get(i).getPassnummer()));
            bw.write(';');
            bw.write(anwender.get(i).geteMail());
            bw.write(';');
            bw.write(anwender.get(i).getPasswort());
            bw.write(';');
            bw.write('\n');
            System.out.println(anwender.get(i));
        }
        bw.close();
    }


    //Funktionelle Methoden
    public static Buchung getBuchungByID(int buchungsID) throws BuchungDoesNotExistException {
        for (int i = 0; i < buchungen.size(); i++) {
            if(buchungen.get(i).getBuchungsID() == buchungsID){
                return buchungen.get(i);
            }
        }
        throw new BuchungDoesNotExistException();
    }

    public static ArrayList<Buchung> getBuchungenByAnwenderID(int anwenderID) {
        ArrayList<Buchung> l = new ArrayList();
        for (int i = 0; i < buchungen.size(); i++) {
            if(buchungen.get(i).getAnwender().getAnwenderID() == anwenderID){
                l.add(buchungen.get(i));
            }
        }
        return l;
    }

    public static Angestellter getAngestellterByAngestelltenID(int angestelltenID) throws NutzerDoesNotExistException {
        for (int i = 0; i < angestellte.size(); i++) {
            if(angestellte.get(i).getAngestelltenID() == angestelltenID){
                return angestellte.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static Anwender getAnwenderByAnwenderID(int anwenderID)throws NutzerDoesNotExistException {
        for (int i = 0; i < anwender.size(); i++) {
            if(anwender.get(i).getAnwenderID() == anwenderID){
                return anwender.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static ArrayList<Anwender> getAnwenderByAngestelltenID(int angestelltenID) throws NutzerDoesNotExistException {

        return new ArrayList<Anwender>();
    }

    public static void addAnwender(String vorname, String nachname, String geburtsdatum, int passnummer, String eMail, String passwort)throws InvalidEmailException, EmailIsAlreadyUsedException{

        Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(eMail);

        if(!matcher.matches()){
            throw new InvalidEmailException();
        }

        for (int i = 0; i < anwender.size(); i++) {
            if(anwender.get(i).geteMail().equals(eMail)){
                throw new EmailIsAlreadyUsedException();
            }
        }
        Anwender.setAnwenderCounter(Anwender.getAnwenderCounter()+1);
        anwender.add(new Anwender(Anwender.getAnwenderCounter(),vorname,nachname,geburtsdatum,passnummer,eMail,passwort));
        System.out.println(vorname+" "+nachname+" "+" added\t -->"+ eMail);
    }

    public static void addBuchung(Buchung buchung) {
        buchungen.add(buchung);
    }

    public static ArrayList<Flug> getFluegeByVerfallen() {
        //olle de wos net vofolln sein, also olle gültign ~ Marvin Knoll
        ArrayList<Flug> l = new ArrayList();
        for (int i = 0; i < fluege.size(); i++) {
            if(fluege.get(i).isVerfallen()){
                l.add(fluege.get(i));
            }
        }
        return l;
    }

    public static void anmelden(String eMail, String password) throws NutzerDoesNotExistException{
        for (int i = 0; i < administratoren.size(); i++) {
            if(administratoren.get(i).geteMail().equals(eMail) && administratoren.get(i).getPasswort().equals(password)){
                angemeldeter = administratoren.get(i);
                System.out.println("Erfolgreich Angemeldet:" + angemeldeter);
                return;
            }
        }
        if(angemeldeter == null){
            for (int i = 0; i < angestellte.size(); i++) {
                if(angestellte.get(i).geteMail().equals(eMail) && angestellte.get(i).getPasswort().equals(password)){
                    angemeldeter = angestellte.get(i);
                    System.out.println(angemeldeter);
                    return;
                }
            }
        }
        if(angemeldeter == null){
            for (int i = 0; i < anwender.size(); i++) {
                if(anwender.get(i).geteMail().equals(eMail) && anwender.get(i).getPasswort().equals(password)){
                    angemeldeter = anwender.get(i);
                    System.out.println(angemeldeter);
                    return;
                }
            }
        }

        throw new NutzerDoesNotExistException();
    }

    public static Flug getFlugByFlugID(String flugID) throws FlugNotFoundException {
        for (int i = 0; i < fluege.size(); i++) {
            if (fluege.get(i).getFlugID().equals(flugID)) {
                return fluege.get(i);
            }
        }
        throw new FlugNotFoundException();
    }


    //getter-Methoden der Arraylists
    public static ArrayList<Administrator> getAdministratoren() {
        return administratoren;
    }

    public static ArrayList<Angestellter> getAngestellte() {
        return angestellte;
    }

    public static ArrayList<Anwender> getAnwender() {
        return anwender;
    }

    public static ArrayList<Buchung> getBuchungen() {
        return buchungen;
    }

    public static ArrayList<Flug> getFluege() {
        return fluege;
    }

    public static ArrayList<Gepaeck> getGepaecke() {
        return gepaecke;
    }

    public static HashMap<Angestellter, Anwender> getAngestelltenAnwender() {
        return angestelltenAnwender;
    }
}
