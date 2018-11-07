package customException;

public class ImproperLampIlluminanceValue extends RuntimeException {

    public ImproperLampIlluminanceValue(String message) {
        super(message);
    }
}