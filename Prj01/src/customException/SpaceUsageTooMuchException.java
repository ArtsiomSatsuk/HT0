package customException;

public class SpaceUsageTooMuchException extends RuntimeException {

    public SpaceUsageTooMuchException(String message) {
        super(message);
    }
}