package customException;

public class NotEnoughIlluminanceException extends RuntimeException {

    public NotEnoughIlluminanceException(String message) {
        super(message);
    }
}