package cz.tomasfejfar.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;

import cz.tomasfejfar.popularmovies.models.Movie;

public class MoviesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_movies_list, container, false);
        Movie[] data = {
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
        };
        MoviesArrayAdapter adapter = new MoviesArrayAdapter(getActivity(), Arrays.asList(data));
        GridView list = (GridView) layout.findViewById(R.id.movie_list);
        list.setAdapter(adapter);
        return layout;
    }

}