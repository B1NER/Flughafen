package Model.Exceptions;

/**
 * Created by knoll on 27.03.2017.
 */
public class WrongPasswordException extends Exception {
    public WrongPasswordException() {
    }

    public WrongPasswordException(final String specialInfo) {
        super(specialInfo);
    }
}
