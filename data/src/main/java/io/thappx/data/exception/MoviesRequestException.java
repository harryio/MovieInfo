package io.thappx.data.exception;

/**
 * Exception thrown when an error occurs while fetching movies
 */
public class MoviesRequestException extends Exception {
    public MoviesRequestException() {
        super();
    }

    public MoviesRequestException(String detailMessage) {
        super(detailMessage);
    }

    public MoviesRequestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public MoviesRequestException(Throwable throwable) {
        super(throwable);
    }
}
