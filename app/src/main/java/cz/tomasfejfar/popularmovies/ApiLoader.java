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

public class ApiLoader {

    public static final String LOG_TAG = "ApiLoader";

    public ArrayList<Movie> loadPopular() {
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https")
                .authority("api.themoviedb.org")
                .appendEncodedPath("/3/movie/popular")
                .appendQueryParameter("api_key", BuildConfig.MOVIEDB_API_KEY);

        String jsonString = makeHttpRequest(uriBuilder.toString());
        ArrayList<Movie> moviesList = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(jsonString);
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                moviesList.add(new Movie(
                        item.getString("title"),
                        item.getString("poster_path"),
                        item.getDouble("vote_average"),
                        item.getDouble("popularity"),
                        item.optString("backdrop"),
                        item.getString("overview"),
                        item.getString("release_date"),
                        item.getInt("id")
                ));
            }
            Log.v(LOG_TAG, results.toString());
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Can't parse JSON");
            Log.e(LOG_TAG, e.getMessage());
        }
        return moviesList;
    }

    private String makeHttpRequest(String uri) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL httpUrl = new URL(uri);
            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) httpUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            return buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
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
