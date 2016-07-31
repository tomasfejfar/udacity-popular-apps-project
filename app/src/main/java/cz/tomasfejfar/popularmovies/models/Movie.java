package cz.tomasfejfar.popularmovies.models;

public class Movie {
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
}
