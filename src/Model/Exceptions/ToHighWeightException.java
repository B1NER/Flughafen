package Model.Exceptions;

/**
 * Created by knoll on 27.03.2017.
 */
public class ToHighWeightException extends Exception {
    public ToHighWeightException() {
    }

    public ToHighWeightException(final String specialInfo) {
        super(specialInfo);
    }
}
