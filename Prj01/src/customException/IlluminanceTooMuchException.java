package customException;

public class IlluminanceTooMuchException extends RuntimeException {

    public IlluminanceTooMuchException(String message) {
        super(message);
    }
}