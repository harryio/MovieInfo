package io.thappx.data.exception;

/**
 * Exception thrown when there is an error while fetching cast of the movie
 */
public class CastRequestException extends Exception {
	public CastRequestException() {
		super();
	}

	public CastRequestException(String detailMessage) {
		super(detailMessage);
	}

	public CastRequestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public CastRequestException(Throwable throwable) {
		super(throwable);
	}
}
