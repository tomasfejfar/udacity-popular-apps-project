package cz.tomasfejfar.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie implements Parcelable {


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    String name;
    String image;
    Double rating;
    Double popularity;
    String backdrop;
    String overview;
    String releaseDate;
    int id;

    public Movie(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Movie(String name, String image, Double rating, Double popularity, String backdrop, String overview, String releaseDate, int id) {
        this.name = name;
        this.image = image;
        this.rating = rating;
        this.popularity = popularity;
        this.backdrop = backdrop;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    protected Movie(Parcel in) {
        name = in.readString();
        image = in.readString();
        rating = in.readDouble();
        popularity = in.readDouble()
        backdrop = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readInt();
    }

    public static Movie fromJsonObject(JSONObject item) throws JSONException {
        return new Movie(
                item.getString("title"),
                item.getString("poster_path"),
                item.getDouble("vote_average"),
                item.getDouble("popularity"),
                item.optString("backdrop"),
                item.getString("overview"),
                item.getString("release_date"),
                item.getInt("id")
        );
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Double getRating() {
        return rating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeDouble(rating);
        dest.writeDouble(popularity);
        dest.writeString(backdrop);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}
