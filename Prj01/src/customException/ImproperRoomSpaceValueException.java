package customException;

public class ImproperRoomSpaceValueException extends RuntimeException {

    public ImproperRoomSpaceValueException(String message) {
        super(message);
    }
}