package io.thappx.data.exception;

public class NetworkConnectionException extends Exception {
    public NetworkConnectionException() {
        super();
    }

    public NetworkConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkConnectionException(String message) {
        super(message);
    }

    public NetworkConnectionException(Throwable throwable) {
        super(throwable);
    }
}
