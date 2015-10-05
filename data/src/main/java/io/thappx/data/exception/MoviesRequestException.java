package io.thappx.data.exception;

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
