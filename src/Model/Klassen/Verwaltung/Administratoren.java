package Model.Klassen.Verwaltung;
import Model.Exceptions.NutzerDoesNotExistException;
import Model.Klassen.Nutzer.Administrator;


import java.util.ArrayList;

/**
 * Created by knoll on 27.03.2017.
 */
public abstract class Administratoren {

    private static ArrayList<Administrator> administratoren = new ArrayList<>();
    private static int adminCounter = 0;


    public static void addAndministrator(Administrator administrator) {
        administratoren.add(administrator);
    }

    public static void removeAdministrator(Administrator admin) {
        administratoren.remove(admin);
    }

    public static void administratorBearbeiten(Administrator admin) {

    }


    public static Administrator getAdministratorByID(int adminID) throws NutzerDoesNotExistException{
        for (int i = 0; i < administratoren.size(); i++) {
            if(administratoren.get(i).getAdminID() == adminID){
                return administratoren.get(i);
            }
        }
        throw new NutzerDoesNotExistException();
    }

    public static ArrayList<Administrator> getAdministratoren() {
        return administratoren;
    }

    public static int getAdminCounter() {
        return adminCounter;
    }


    public static void setAdminCounter(int adminCounter) {
        Administratoren.adminCounter = adminCounter;
    }

    public static void setAdministratoren(ArrayList<Administrator> administratoren) {
        Administratoren.administratoren = administratoren;
    }
}
