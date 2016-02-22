package io.thappx.movieinfo.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Singleton;

import io.thappx.movieinfo.model.MovieModel;
import io.thappx.movieinfo.view.activity.MovieDetailActivity;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {
    /**
     * Goes to the move detail screen
     * @param pContext A context needed to open the destination activity
     */
    public void navigateToMovieDetails(Context pContext, MovieModel pMovieModel,
                                       int[] pLoc) {
        if (pContext != null) {
            Intent lIntent = MovieDetailActivity
                    .getCallingIntent(pContext, pMovieModel, pLoc);
            pContext.startActivity(lIntent);
        }
    }
}
