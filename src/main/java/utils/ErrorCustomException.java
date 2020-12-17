package utils;

public class ErrorCustomException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public ErrorCustomException(String message) {
        super(ERROR_PREFIX + message);
    }
}
