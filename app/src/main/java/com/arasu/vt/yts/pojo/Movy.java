package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kyros on 03-10-2017.
 */

public class Movy{
    @SerializedName("id")
    @Expose
    private double id;

    public double getId() { return this.id; }

    public void setId(double id) { this.id = id; }
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }
    @SerializedName("imdb_code")
    @Expose
    private String imdb_code;

    public String getImdbCode() { return this.imdb_code; }

    public void setImdbCode(String imdb_code) { this.imdb_code = imdb_code; }
    @SerializedName("title")
    @Expose
    private String title;

    public String getTitle() { return this.title; }

    public void setTitle(String title) { this.title = title; }
    @SerializedName("title_english")
    @Expose
    private String title_english;

    public String getTitleEnglish() { return this.title_english; }

    public void setTitleEnglish(String title_english) { this.title_english = title_english; }
    @SerializedName("title_long")
    @Expose
    private String title_long;

    public String getTitleLong() { return this.title_long; }

    public void setTitleLong(String title_long) { this.title_long = title_long; }
    @SerializedName("slug")
    @Expose
    private String slug;

    public String getSlug() { return this.slug; }

    public void setSlug(String slug) { this.slug = slug; }
    @SerializedName("year")
    @Expose
    private double year;

    public double getYear() { return this.year; }

    public void setYear(double year) { this.year = year; }
    @SerializedName("rating")
    @Expose
    private double rating;

    public double getRating() { return this.rating; }

    public void setRating(double rating) { this.rating = rating; }
    @SerializedName("runtime")
    @Expose
    private double runtime;

    public double getRuntime() { return this.runtime; }

    public void setRuntime(double runtime) { this.runtime = runtime; }
    @SerializedName("genres")
    @Expose
    private ArrayList<String> genres;

    public ArrayList<String> getGenres() { return this.genres; }

    public void setGenres(ArrayList<String> genres) { this.genres = genres; }
    @SerializedName("summary")
    @Expose
    private String summary;

    public String getSummary() { return this.summary; }

    public void setSummary(String summary) { this.summary = summary; }
    @SerializedName("description_full")
    @Expose
    private String description_full;

    public String getDescriptionFull() { return this.description_full; }

    public void setDescriptionFull(String description_full) { this.description_full = description_full; }
    @SerializedName("synopsis")
    @Expose
    private String synopsis;

    public String getSynopsis() { return this.synopsis; }

    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    @SerializedName("yt_trailer_code")
    @Expose
    private String yt_trailer_code;

    public String getYtTrailerCode() { return this.yt_trailer_code; }

    public void setYtTrailerCode(String yt_trailer_code) { this.yt_trailer_code = yt_trailer_code; }
    @SerializedName("language")
    @Expose
    private String language;

    public String getLanguage() { return this.language; }

    public void setLanguage(String language) { this.language = language; }
    @SerializedName("mpa_rating")
    @Expose
    private String mpa_rating;

    public String getMpaRating() { return this.mpa_rating; }

    public void setMpaRating(String mpa_rating) { this.mpa_rating = mpa_rating; }
    @SerializedName("background_image")
    @Expose
    private String background_image;

    public String getBackgroundImage() { return this.background_image; }

    public void setBackgroundImage(String background_image) { this.background_image = background_image; }
    @SerializedName("background_image_original")
    @Expose
    private String background_image_original;

    public String getBackgroundImageOriginal() { return this.background_image_original; }

    public void setBackgroundImageOriginal(String background_image_original) { this.background_image_original = background_image_original; }
    @SerializedName("small_cover_image")
    @Expose
    private String small_cover_image;

    public String getSmallCoverImage() { return this.small_cover_image; }

    public void setSmallCoverImage(String small_cover_image) { this.small_cover_image = small_cover_image; }
    @SerializedName("medium_cover_image")
    @Expose
    private String medium_cover_image;

    public String getMediumCoverImage() { return this.medium_cover_image; }

    public void setMediumCoverImage(String medium_cover_image) { this.medium_cover_image = medium_cover_image; }
    @SerializedName("large_cover_image")
    @Expose
    private String large_cover_image;

    public String getLargeCoverImage() { return this.large_cover_image; }

    public void setLargeCoverImage(String large_cover_image) { this.large_cover_image = large_cover_image; }
    @SerializedName("state")
    @Expose
    private String state;

    public String getState() { return this.state; }

    public void setState(String state) { this.state = state; }
    @SerializedName("torrents")
    @Expose
    private ArrayList<Torrent> torrents;

    public ArrayList<Torrent> getTorrents() { return this.torrents; }

    public void setTorrents(ArrayList<Torrent> torrents) { this.torrents = torrents; }
    @SerializedName("date_uploaded")
    @Expose
    private String date_uploaded;

    public String getDateUploaded() { return this.date_uploaded; }

    public void setDateUploaded(String date_uploaded) { this.date_uploaded = date_uploaded; }
    @SerializedName("date_uploaded_unix")
    @Expose
    private double date_uploaded_unix;

    public double getDateUploadedUnix() { return this.date_uploaded_unix; }

    public void setDateUploadedUnix(double date_uploaded_unix) { this.date_uploaded_unix = date_uploaded_unix; }
}