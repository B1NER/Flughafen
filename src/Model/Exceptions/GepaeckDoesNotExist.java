package Model.Exceptions;

/**
 * Created by matti on 04.04.2017.
 */
public class GepaeckDoesNotExist extends Exception {
    public GepaeckDoesNotExist() {
    }

    public GepaeckDoesNotExist(final String specialInfo) {
        super(specialInfo);
    }
}
