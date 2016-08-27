package cz.tomasfejfar.popularmovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cz.tomasfejfar.popularmovies.models.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Picasso picasso = new Picasso.Builder(getContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e(LOG_TAG, exception.getMessage());
                    }
                })
                .build();
        Movie movie = getActivity().getIntent().getExtras().getParcelable(Movie.class.getCanonicalName());
        View layout = inflater.inflate(R.layout.fragment_detail, container, false);
        if (movie == null) {
            throw new RuntimeException("Failed to pull parcelable from intent!");
        }
        TextView name = (TextView) layout.findViewById(R.id.detail_name);
        name.setText(movie.getName());
        TextView rating = (TextView) layout.findViewById(R.id.detail_rating);
        rating.setText(getContext().getString(R.string.detail_rating_default, movie.getRating()));
        TextView releaseDate = (TextView) layout.findViewById(R.id.detail_release_date);
        releaseDate.setText(getContext().getString(R.string.detail_release_date, movie.getReleaseDate()));
        ImageView image = (ImageView) layout.findViewById(R.id.detail_image);
        picasso.load("http://image.tmdb.org/t/p/w185/".concat(movie.getImage()))
                .resize(185, 185)
                .centerCrop()
                .into(image);
        TextView overview = (TextView) layout.findViewById(R.id.detail_overview);
        overview.setText(movie.getOverview());
        return layout;
    }
}
