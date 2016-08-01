package cz.tomasfejfar.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import cz.tomasfejfar.popularmovies.models.Movie;
import cz.tomasfejfar.popularmovies.tasks.FetchMoviesTask;

public class MoviesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_movies_list, container, false);
        MoviesArrayAdapter adapter = new MoviesArrayAdapter(getActivity(), new ArrayList<Movie>());
        FetchMoviesTask task = new FetchMoviesTask();
        task.setAdapter(adapter);

        task.execute(FetchMoviesTask.TOP_RATED);
        GridView list = (GridView) layout.findViewById(R.id.movie_list);
        list.setAdapter(adapter);
        return layout;
    }

}