package cz.tomasfejfar.popularmovies.tasks;

import android.os.AsyncTask;

import cz.tomasfejfar.popularmovies.ApiLoader;
import cz.tomasfejfar.popularmovies.MoviesArrayAdapter;
import cz.tomasfejfar.popularmovies.models.Movie;

public class FetchMoviesTask extends AsyncTask<Integer, Void, Movie[]> {
    public static final int MOST_POPULAR = 1;
    public static final int  BEST_RATED = 2;

    private MoviesArrayAdapter adapter;

    @Override
    protected Movie[] doInBackground(Integer... params) {
        ApiLoader loader = new ApiLoader();
        Movie[] data = loader.loadPopular();
        /*Movie[] data = {
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/75GFqrnHMKqkcNZ2wWefWXfqtMV.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/9KQX22BeFzuNM66pBA6JbiaJ7Mi.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
                new Movie("Interstellar", "http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg", 1),
        };*/
        return data;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        if (movies != null) {
            adapter.clear();
            adapter.addAll(movies);
        }
    }

    public void setAdapter(MoviesArrayAdapter adapter) {
        this.adapter = adapter;
    }
}
