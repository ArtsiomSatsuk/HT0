package customException;

public class FurnitureNotFoundException extends RuntimeException {
    public FurnitureNotFoundException(String message) {
        super(message);
    }
}
