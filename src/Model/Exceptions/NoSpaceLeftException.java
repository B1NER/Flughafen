package Model.Exceptions;

/**
 * Created by knoll on 11.04.2017.
 */
public class NoSpaceLeftException extends Exception {
    public NoSpaceLeftException() {
    }

    public NoSpaceLeftException(final String specialInfo) {
        super(specialInfo);
    }
}
