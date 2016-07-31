package cz.tomasfejfar.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cz.tomasfejfar.popularmovies.models.Movie;

public class MoviesArrayAdapter extends ArrayAdapter<Movie> {

    public MoviesArrayAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movies, parent, false);
        }
        ImageView image = (ImageView) convertView.findViewById(R.id.movie_list_item_image);
        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/".concat(movie.getImage()))
                .resize(185, 185)
                .centerCrop()
                .into(image);
        return convertView;
    }
}
