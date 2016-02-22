package io.thappx.data.exception;

/**
 * Exception thrown when there is an error connecting to the internet
 */
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
