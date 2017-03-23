package Model.Exceptions;

/**
 * Created by knoll on 21.03.2017.
 */
public class FlugNotFoundException extends Exception {
    public FlugNotFoundException() {
    }

    public FlugNotFoundException(final String specialInfo) {
        super(specialInfo);
    }
}
