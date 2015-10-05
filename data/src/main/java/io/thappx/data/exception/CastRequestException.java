package io.thappx.data.exception;

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
