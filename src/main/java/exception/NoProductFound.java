package exception;

public class NoProductFound extends RuntimeException {

    public NoProductFound() {
        super();
    }

    public NoProductFound(String message) {
        super(message);
    }

    public NoProductFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoProductFound(Throwable cause) {
        super(cause);
    }

    protected NoProductFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
