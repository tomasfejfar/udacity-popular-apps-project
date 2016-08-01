package cz.tomasfejfar.popularmovies.tasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import cz.tomasfejfar.popularmovies.ApiLoader;
import cz.tomasfejfar.popularmovies.MoviesArrayAdapter;
import cz.tomasfejfar.popularmovies.models.Movie;

public class FetchMoviesTask extends AsyncTask<Integer, Void, ArrayList<Movie>> {
    public static final int MOST_POPULAR = 1;
    public static final int TOP_RATED = 2;

    private MoviesArrayAdapter adapter;

    @Override
    protected ArrayList<Movie> doInBackground(Integer... params) {
        ApiLoader loader = new ApiLoader();
        return loader.loadPopular();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        if (movies != null) {
            adapter.clear();
            adapter.addAll(movies);
        }
    }

    public void setAdapter(MoviesArrayAdapter adapter) {
        this.adapter = adapter;
    }
}
