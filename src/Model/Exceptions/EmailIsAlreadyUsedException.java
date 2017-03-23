package Model.Exceptions;

/**
 * Created by knoll on 21.03.2017.
 */
public class EmailIsAlreadyUsedException extends Exception {
    public EmailIsAlreadyUsedException() {
    }

    public EmailIsAlreadyUsedException(final String specialInfo) {
        super(specialInfo);
    }
}
