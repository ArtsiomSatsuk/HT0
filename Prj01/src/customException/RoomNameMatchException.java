package customException;

public class RoomNameMatchException extends RuntimeException {
    public RoomNameMatchException(String message) {
        super(message);
    }
}
