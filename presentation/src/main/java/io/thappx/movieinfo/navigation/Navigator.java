package io.thappx.movieinfo.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.view.activity.MovieDetailActivity;

@Singleton
public class Navigator {

	public void navigateToMovieDetails(Context pContext, MovieModel pMovieModel,
									   int[] pLoc) {
		if (pContext != null) {
			Intent lIntent = MovieDetailActivity
					.getCallingIntent(pContext, pMovieModel, pLoc);
			pContext.startActivity(lIntent);
		}
	}
}
