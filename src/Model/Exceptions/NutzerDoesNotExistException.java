package Model.Exceptions;

/**
 * Created by knoll on 21.03.2017.
 */
public class NutzerDoesNotExistException extends Exception {
    public NutzerDoesNotExistException() {
    }

    public NutzerDoesNotExistException(final String specialInfo) {
        super(specialInfo);
    }
}
