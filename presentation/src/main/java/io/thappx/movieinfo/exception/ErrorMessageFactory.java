package io.thappx.movieinfo.exception;

import android.content.Context;

import io.thappx.data.exception.MoviesRequestException;
import io.thappx.data.exception.NetworkConnectionException;
import io.thappx.movieinfo.R;

public class ErrorMessageFactory {
	private ErrorMessageFactory() {
		//Do nothing
	}

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
