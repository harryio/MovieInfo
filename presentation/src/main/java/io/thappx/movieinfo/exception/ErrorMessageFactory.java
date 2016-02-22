package io.thappx.movieinfo.exception;

import android.content.Context;

import io.thappx.data.exception.MoviesRequestException;
import io.thappx.data.exception.NetworkConnectionException;
import io.thappx.movieinfo.R;

public class ErrorMessageFactory {
    /**
     * Factory used to create error messages from an Exception as a condition.
     */
    private ErrorMessageFactory() {
        //Do nothing
    }

    /**
     * Creates a String representing an error message.
     *
     * @param pContext   Context needed to retrieve string resources.
     * @param pException An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Context pContext, Exception pException) {
        String message = pContext.getString(R.string.error_generic);

        if (pException instanceof NetworkConnectionException) {
            message = pContext.getString(R.string.error_no_internet_connection);
        } else if (pException instanceof MoviesRequestException) {
            message = pContext.getString(R.string.error_api_connection_error);
        }

        return message;
    }
}
