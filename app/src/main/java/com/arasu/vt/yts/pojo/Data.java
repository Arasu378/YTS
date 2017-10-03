package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kyros on 03-10-2017.
 */

public class Data{
    @SerializedName("movie_count")
    @Expose
    private double movie_count;

    public double getMovieCount() { return this.movie_count; }

    public void setMovieCount(double movie_count) { this.movie_count = movie_count; }
    @SerializedName("limit")
    @Expose
    private double limit;

    public double getLimit() { return this.limit; }

    public void setLimit(double limit) { this.limit = limit; }
    @SerializedName("page_number")
    @Expose
    private double page_number;

    public double getPageNumber() { return this.page_number; }

    public void setPageNumber(double page_number) { this.page_number = page_number; }
    @SerializedName("movies")
    @Expose
    private ArrayList<Movy> movies;

    public ArrayList<Movy> getMovies() { return this.movies; }

    public void setMovies(ArrayList<Movy> movies) { this.movies = movies; }
}