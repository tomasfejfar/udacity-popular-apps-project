package cz.tomasfejfar.popularmovies;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import cz.tomasfejfar.popularmovies.models.Movie;
import cz.tomasfejfar.popularmovies.tasks.FetchMoviesTask;

public class ApiLoader {

    public static final String LOG_TAG = ApiLoader.class.getSimpleName();

    public ArrayList<Movie> loadPopular() {
        return loadData(FetchMoviesTask.MOST_POPULAR);
    }

    public ArrayList<Movie> loadTopRated() {
        return loadData(FetchMoviesTask.TOP_RATED);
    }

    private ArrayList<Movie> loadData(int endpointType) {
        String jsonString = makeHttpRequest(getApiUri(endpointType));
        ArrayList<Movie> moviesList = new ArrayList<>();
        if (jsonString == null) {
            Log.e(LOG_TAG, "Failed to get JSON");
            return moviesList;
        }

        try {
            JSONObject response = new JSONObject(jsonString);
            moviesList = parseJsonToMoviesList(response);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Can't parse JSON: ".concat(e.getMessage()));
        }
        return moviesList;
    }

    private String getApiUri(int endpointType) {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https")
                .authority("api.themoviedb.org")
                .appendEncodedPath("/3/movie/")
                .appendQueryParameter("page", "4")
                .appendQueryParameter("api_key", BuildConfig.MOVIEDB_API_KEY);

        switch (endpointType) {
            case FetchMoviesTask.TOP_RATED:
                uriBuilder.appendEncodedPath("top_rated");
                break;
            case FetchMoviesTask.MOST_POPULAR:
                uriBuilder.appendEncodedPath("popular");
        }
        return uriBuilder.toString();
    }

    private ArrayList<Movie> parseJsonToMoviesList(JSONObject response) throws JSONException {
        ArrayList<Movie> moviesList = new ArrayList<>();
        JSONArray results = response.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            JSONObject item = results.getJSONObject(i);
            moviesList.add(Movie.fromJsonObject(item));
        }
        return moviesList;

    }

    private String makeHttpRequest(String uri) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL httpUrl = new URL(uri);
            urlConnection = (HttpURLConnection) httpUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
    }
}
