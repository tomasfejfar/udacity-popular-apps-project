package cz.tomasfejfar.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import cz.tomasfejfar.popularmovies.models.Movie;

public class MoviesListFragment extends Fragment {

    private MoviesArrayAdapter moviesArrayAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        moviesArrayAdapter = new MoviesArrayAdapter(getActivity(), new ArrayList<Movie>());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_list, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_action_popularity) {
            progressBar.setVisibility(View.VISIBLE);
            FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
            fetchMoviesTask.setAdapter(moviesArrayAdapter).execute(ApiLoader.MOST_POPULAR);
        }
        if (id == R.id.menu_action_rating) {
            progressBar.setVisibility(View.VISIBLE);
            FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
            fetchMoviesTask.setAdapter(moviesArrayAdapter).execute(ApiLoader.TOP_RATED);
        }
        return super.onOptionsItemSelected(item);
    }

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_movies_list, container, false);
        progressBar = (ProgressBar) layout.findViewById(R.id.movie_fragment_progressbar);
        FetchMoviesTask task = new FetchMoviesTask();
        task.setAdapter(moviesArrayAdapter);
        task.execute(ApiLoader.TOP_RATED);
        GridView list = (GridView) layout.findViewById(R.id.movie_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = moviesArrayAdapter.getItem(position);
                Intent detailIntent = new Intent(getContext(), DetailActivity.class);
                //detailIntent.putExtra
            }
        });
        list.setAdapter(moviesArrayAdapter);
        return layout;
    }

    public class FetchMoviesTask extends AsyncTask<Integer, Void, ArrayList<Movie>> {
        public final String LOG_TAG = ApiLoader.class.getSimpleName();

        private MoviesArrayAdapter adapter;

        @Override
        protected ArrayList<Movie> doInBackground(Integer... params) {
            ApiLoader loader = new ApiLoader();
            int type = params[0];
            return loader.loadData(type);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            if (movies.size() > 0) {
                adapter.clear();
                adapter.addAll(movies);
            } else {
                Toast.makeText(getContext(), "Could not fetch movies data", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }

        public FetchMoviesTask setAdapter(MoviesArrayAdapter adapter) {
            this.adapter = adapter;
            return this;
        }
    }


}