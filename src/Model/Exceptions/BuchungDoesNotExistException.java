package Model.Exceptions;

/**
 * Created by matti on 22.03.2017.
 */
public class BuchungDoesNotExistException extends Exception {
    public BuchungDoesNotExistException() {
    }

    public BuchungDoesNotExistException(final String specialInfo) {
        super(specialInfo);
    }
}
