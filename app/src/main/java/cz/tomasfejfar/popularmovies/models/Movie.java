package cz.tomasfejfar.popularmovies.models;

import java.util.Date;

public class Movie {
    String name;

    String image;

    Float rating;

    Float popularity;

    String backdrop;

    String overview;

    Date releaseDate;

    int id;

    public Movie(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Movie(String name, String image, Float rating, Float popularity, String backdrop, String overview, Date releaseDate, int id) {
        this.name = name;
        this.image = image;
        this.rating = rating;
        this.popularity = popularity;
        this.backdrop = backdrop;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Float getRating() {
        return rating;
    }

    public Float getPopularity() {
        return popularity;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }
}
