package customException;

public class LampNotFoundException extends RuntimeException {
    public LampNotFoundException(String message) {
        super(message);
    }
}
