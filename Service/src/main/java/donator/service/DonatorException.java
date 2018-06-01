package donator.service;

public class DonatorException extends Exception {
    public DonatorException() {
    }

    public DonatorException(String message) {
        super(message);
    }

    public DonatorException(String message, Throwable cause) {
        super(message, cause);
    }
}