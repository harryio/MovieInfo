package io.thappx.data.exception;

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
