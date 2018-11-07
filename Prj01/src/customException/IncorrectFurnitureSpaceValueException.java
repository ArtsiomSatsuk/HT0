package customException;

public class IncorrectFurnitureSpaceValueException extends RuntimeException {

    public IncorrectFurnitureSpaceValueException(String message) {
        super(message);
    }
}