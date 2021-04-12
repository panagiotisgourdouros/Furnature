package team.groupproject.errorHandling;

public class CartNotFoundException extends RuntimeException {

    private String message;

    public CartNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
