package customException;

public class IncorrectAmountOfWindowsException extends RuntimeException {

    public IncorrectAmountOfWindowsException(String message) {
        super(message);
    }
}