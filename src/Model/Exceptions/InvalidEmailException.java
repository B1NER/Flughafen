package Model.Exceptions;

/**
 * Created by matti on 22.03.2017.
 */
public class InvalidEmailException extends Exception{
    public InvalidEmailException() {
    }

    public InvalidEmailException(final String specialInfo) {
        super(specialInfo);
    }
}
