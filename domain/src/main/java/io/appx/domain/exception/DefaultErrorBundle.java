package io.appx.domain.exception;

/**
 * Wrapper around Exception used to manage default Errors
 */
public class DefaultErrorBundle implements ErrorBundle {
    private static final String DEFAULT_ERROR_MESSAGE = "Unknown Error";

    private Exception mException;

    public DefaultErrorBundle(Exception pException) {
        mException = pException;
    }

    @Override
    public Exception getException() {
        return mException;
    }

    @Override
    public String getErrorMessage() {
        return mException == null ? DEFAULT_ERROR_MESSAGE : this.mException.getMessage();
    }
}
