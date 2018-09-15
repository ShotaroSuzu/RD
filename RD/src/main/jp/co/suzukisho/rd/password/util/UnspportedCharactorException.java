package main.jp.co.suzukisho.rd.password.util;

public class UnspportedCharactorException extends RuntimeException {

    public UnspportedCharactorException() {
        super();
    }

    public UnspportedCharactorException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnspportedCharactorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnspportedCharactorException(String message) {
        super(message);
    }

    public UnspportedCharactorException(Throwable cause) {
        super(cause);
    }
}
