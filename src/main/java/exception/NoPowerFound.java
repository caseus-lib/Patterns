package exception;

public class NoPowerFound extends RuntimeException {

    public NoPowerFound() {
        super();
    }

    public NoPowerFound(String message) {
        super(message);
    }

    public NoPowerFound(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPowerFound(Throwable cause) {
        super(cause);
    }

    protected NoPowerFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
