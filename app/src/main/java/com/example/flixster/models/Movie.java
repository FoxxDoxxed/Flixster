//this is a pojo (plain old java object). it will encapsulate the idea of a movie

package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel

public class Movie {
    String posterPath;
    String title;
    String overview;

    double rating;

    int movieId;

    //empty constructor needed by the Parceler library
    public Movie()
    {

    }

    //if any of these fail the constructor will throw jsonexception and the method that calls the constructor will handle the exception
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");

        rating = jsonObject.getDouble("vote_average");

        movieId = jsonObject.getInt("id");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    //generated getters to get data out of the objects
    //!!what i should be doing is making an api request to the configurations api, figure out what image sizes are available (fetch all of the available sizes), apend them to base url and apend relative path from movie now playing api
    public String getPosterPath() {
        //the %s in the following link allows me to replace that portion with the following parameter, in this case the posterpath
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getMovieId() {
        return movieId;
    }
}
