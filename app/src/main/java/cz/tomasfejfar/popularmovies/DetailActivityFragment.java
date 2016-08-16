package cz.tomasfejfar.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Movie movie = getActivity().getIntent().getExtras().getParcelable(Movie.class.getCanonicalName());
        View layout = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView name = (TextView) layout.findViewById(R.id.detail_name);
        if (movie == null) {
            throw new RuntimeException("Failed to pull parcelable from intent!");
        }
        name.setText(movie.getName());
        ImageView image = (ImageView) layout.findViewById(R.id.detail_image);
        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/".concat(movie.getImage()))
                .resize(185, 185)
                .centerCrop()
                .into(image);
        TextView overview = (TextView) layout.findViewById(R.id.detail_overview);
        overview.setText(movie.getOverview());
        return layout;
    }
}
