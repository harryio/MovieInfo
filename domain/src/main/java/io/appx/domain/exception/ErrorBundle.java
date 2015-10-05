package io.appx.domain.exception;

public interface ErrorBundle {
	Exception getException();

	String getErrorMessage();
}
