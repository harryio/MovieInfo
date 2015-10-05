package io.thappx.movieinfo.view;

import io.thappx.movieinfo.model.CastModel;

public interface MovieCastView {
	void showCastError(String pMessage);

	void showCast(CastModel pCastModel);
}
