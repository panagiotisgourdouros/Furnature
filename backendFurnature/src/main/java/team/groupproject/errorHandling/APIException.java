package team.groupproject.errorHandling;

public class APIException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    // constructor calling parent runtime exception constructor
    public APIException(String message) {
        super(message);
        this.message = message;
    }

}
