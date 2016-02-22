package io.thappx.data.exception;

/**
 * Exception thrown when there is an error while fetching movie detail
 */
public class MovieDetailRequestException extends Exception {
	public MovieDetailRequestException() {
		super();
	}

	public MovieDetailRequestException(String detailMessage) {
		super(detailMessage);
	}

	public MovieDetailRequestException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public MovieDetailRequestException(Throwable throwable) {
		super(throwable);
	}
}
