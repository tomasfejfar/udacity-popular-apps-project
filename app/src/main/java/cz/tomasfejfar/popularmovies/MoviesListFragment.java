package cz.tomasfejfar.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import cz.tomasfejfar.popularmovies.models.Movie;

public class MoviesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_movies_list, container, false);
        MoviesArrayAdapter adapter = new MoviesArrayAdapter(getActivity(), new ArrayList<Movie>());
        FetchMoviesTask task = new FetchMoviesTask();
        task.setAdapter(adapter);

        task.execute(ApiLoader.TOP_RATED);
        GridView list = (GridView) layout.findViewById(R.id.movie_list);
        list.setAdapter(adapter);
        return layout;
    }

    public class FetchMoviesTask extends AsyncTask<Integer, Void, ArrayList<Movie>> {

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
            } else {
                Toast.makeText(getContext(), "Could not fetch movies data", Toast.LENGTH_SHORT).show();
            }
        }

        public void setAdapter(MoviesArrayAdapter adapter) {
            this.adapter = adapter;
        }
    }


}