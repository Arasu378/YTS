package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kyros on 04-10-2017.
 */

public class Movie{
    @SerializedName("id")
    @Expose
    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }
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
    private int year;

    public int getYear() { return this.year; }

    public void setYear(int year) { this.year = year; }
    @SerializedName("rating")
    @Expose
    private double rating;

    public double getRating() { return this.rating; }

    public void setRating(double rating) { this.rating = rating; }
    @SerializedName("runtime")
    @Expose
    private int runtime;

    public int getRuntime() { return this.runtime; }

    public void setRuntime(int runtime) { this.runtime = runtime; }
    @SerializedName("genres")
    @Expose
    private ArrayList<String> genres;

    public ArrayList<String> getGenres() { return this.genres; }

    public void setGenres(ArrayList<String> genres) { this.genres = genres; }
    @SerializedName("download_count")
    @Expose
    private int download_count;

    public int getDownloadCount() { return this.download_count; }

    public void setDownloadCount(int download_count) { this.download_count = download_count; }
    @SerializedName("like_count")
    @Expose
    private int like_count;

    public int getLikeCount() { return this.like_count; }

    public void setLikeCount(int like_count) { this.like_count = like_count; }
    @SerializedName("description_intro")
    @Expose
    private String description_intro;

    public String getDescriptionIntro() { return this.description_intro; }

    public void setDescriptionIntro(String description_intro) { this.description_intro = description_intro; }
    @SerializedName("description_full")
    @Expose
    private String description_full;

    public String getDescriptionFull() { return this.description_full; }

    public void setDescriptionFull(String description_full) { this.description_full = description_full; }
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
    @SerializedName("medium_screenshot_image1")
    @Expose
    private String medium_screenshot_image1;

    public String getMediumScreenshotImage1() { return this.medium_screenshot_image1; }

    public void setMediumScreenshotImage1(String medium_screenshot_image1) { this.medium_screenshot_image1 = medium_screenshot_image1; }
    @SerializedName("medium_screenshot_image2")
    @Expose
    private String medium_screenshot_image2;

    public String getMediumScreenshotImage2() { return this.medium_screenshot_image2; }

    public void setMediumScreenshotImage2(String medium_screenshot_image2) { this.medium_screenshot_image2 = medium_screenshot_image2; }
    @SerializedName("medium_screenshot_image3")
    @Expose
    private String medium_screenshot_image3;

    public String getMediumScreenshotImage3() { return this.medium_screenshot_image3; }

    public void setMediumScreenshotImage3(String medium_screenshot_image3) { this.medium_screenshot_image3 = medium_screenshot_image3; }
    @SerializedName("large_screenshot_image1")
    @Expose
    private String large_screenshot_image1;

    public String getLargeScreenshotImage1() { return this.large_screenshot_image1; }

    public void setLargeScreenshotImage1(String large_screenshot_image1) { this.large_screenshot_image1 = large_screenshot_image1; }
    @SerializedName("large_screenshot_image2")
    @Expose
    private String large_screenshot_image2;

    public String getLargeScreenshotImage2() { return this.large_screenshot_image2; }

    public void setLargeScreenshotImage2(String large_screenshot_image2) { this.large_screenshot_image2 = large_screenshot_image2; }
    @SerializedName("large_screenshot_image3")
    @Expose
    private String large_screenshot_image3;

    public String getLargeScreenshotImage3() { return this.large_screenshot_image3; }

    public void setLargeScreenshotImage3(String large_screenshot_image3) { this.large_screenshot_image3 = large_screenshot_image3; }
    @SerializedName("cast")
    @Expose
    private ArrayList<Cast> cast;

    public ArrayList<Cast> getCast() { return this.cast; }

    public void setCast(ArrayList<Cast> cast) { this.cast = cast; }
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
    private int date_uploaded_unix;

    public int getDateUploadedUnix() { return this.date_uploaded_unix; }

    public void setDateUploadedUnix(int date_uploaded_unix) { this.date_uploaded_unix = date_uploaded_unix; }
}
