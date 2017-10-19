
package com.arasu.vt.yts.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("movie_count")
    @Expose
    private Integer movieCount;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("page_number")
    @Expose
    private Integer pageNumber;
    @SerializedName("movies")
    @Expose
    private List<Movie> movies = null;


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_long")
    @Expose
    private String titleLong;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("runtime")
    @Expose
    private String runtime;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("mpa_rating")
    @Expose
    private String mpaRating;
    @SerializedName("download_count")
    @Expose
    private String downloadCount;
    @SerializedName("like_count")
    @Expose
    private String likeCount;
    @SerializedName("yt_trailer_code")
    @Expose
    private String ytTrailerCode;
    @SerializedName("rt_critics_score")
    @Expose
    private String rtCriticsScore;
    @SerializedName("rt_critics_rating")
    @Expose
    private String rtCriticsRating;
    @SerializedName("rt_audience_score")
    @Expose
    private String rtAudienceScore;
    @SerializedName("description_intro")
    @Expose
    private String descriptionIntro;
    @SerializedName("description_full")
    @Expose
    private String descriptionFull;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("small_cover_image")
    @Expose
    private String smallCoverImage;
    @SerializedName("medium_cover_image")
    @Expose
    private String mediumCoverImage;
    @SerializedName("large_cover_image")
    @Expose
    private String largeCoverImage;
    @SerializedName("medium_screenshot1_image")
    @Expose
    private String mediumScreenshot1Image;
    @SerializedName("large_screenshot1_image")
    @Expose
    private String largeScreenshot1Image;
    @SerializedName("medium_screenshot2_image")
    @Expose
    private String mediumScreenshot2Image;
    @SerializedName("large_screenshot2_image")
    @Expose
    private String largeScreenshot2Image;
    @SerializedName("medium_screenshot3_image")
    @Expose
    private String mediumScreenshot3Image;
    @SerializedName("large_screenshot3_image")
    @Expose
    private String largeScreenshot3Image;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("torrents")
    @Expose
    private List<Torrent> torrents = null;
    @SerializedName("date_uploaded")
    @Expose
    private String dateUploaded;
    @SerializedName("date_uploaded_unix")
    @Expose
    private String dateUploadedUnix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLong() {
        return titleLong;
    }

    public void setTitleLong(String titleLong) {
        this.titleLong = titleLong;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpaRating() {
        return mpaRating;
    }

    public void setMpaRating(String mpaRating) {
        this.mpaRating = mpaRating;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getYtTrailerCode() {
        return ytTrailerCode;
    }

    public void setYtTrailerCode(String ytTrailerCode) {
        this.ytTrailerCode = ytTrailerCode;
    }

    public String getRtCriticsScore() {
        return rtCriticsScore;
    }

    public void setRtCriticsScore(String rtCriticsScore) {
        this.rtCriticsScore = rtCriticsScore;
    }

    public String getRtCriticsRating() {
        return rtCriticsRating;
    }

    public void setRtCriticsRating(String rtCriticsRating) {
        this.rtCriticsRating = rtCriticsRating;
    }

    public String getRtAudienceScore() {
        return rtAudienceScore;
    }

    public void setRtAudienceScore(String rtAudienceScore) {
        this.rtAudienceScore = rtAudienceScore;
    }

    public String getDescriptionIntro() {
        return descriptionIntro;
    }

    public void setDescriptionIntro(String descriptionIntro) {
        this.descriptionIntro = descriptionIntro;
    }

    public String getDescriptionFull() {
        return descriptionFull;
    }

    public void setDescriptionFull(String descriptionFull) {
        this.descriptionFull = descriptionFull;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    public void setSmallCoverImage(String smallCoverImage) {
        this.smallCoverImage = smallCoverImage;
    }

    public String getMediumCoverImage() {
        return mediumCoverImage;
    }

    public void setMediumCoverImage(String mediumCoverImage) {
        this.mediumCoverImage = mediumCoverImage;
    }

    public String getLargeCoverImage() {
        return largeCoverImage;
    }

    public void setLargeCoverImage(String largeCoverImage) {
        this.largeCoverImage = largeCoverImage;
    }

    public String getMediumScreenshot1Image() {
        return mediumScreenshot1Image;
    }

    public void setMediumScreenshot1Image(String mediumScreenshot1Image) {
        this.mediumScreenshot1Image = mediumScreenshot1Image;
    }

    public String getLargeScreenshot1Image() {
        return largeScreenshot1Image;
    }

    public void setLargeScreenshot1Image(String largeScreenshot1Image) {
        this.largeScreenshot1Image = largeScreenshot1Image;
    }

    public String getMediumScreenshot2Image() {
        return mediumScreenshot2Image;
    }

    public void setMediumScreenshot2Image(String mediumScreenshot2Image) {
        this.mediumScreenshot2Image = mediumScreenshot2Image;
    }

    public String getLargeScreenshot2Image() {
        return largeScreenshot2Image;
    }

    public void setLargeScreenshot2Image(String largeScreenshot2Image) {
        this.largeScreenshot2Image = largeScreenshot2Image;
    }

    public String getMediumScreenshot3Image() {
        return mediumScreenshot3Image;
    }

    public void setMediumScreenshot3Image(String mediumScreenshot3Image) {
        this.mediumScreenshot3Image = mediumScreenshot3Image;
    }

    public String getLargeScreenshot3Image() {
        return largeScreenshot3Image;
    }

    public void setLargeScreenshot3Image(String largeScreenshot3Image) {
        this.largeScreenshot3Image = largeScreenshot3Image;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(List<Torrent> torrents) {
        this.torrents = torrents;
    }

    public String getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(String dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String getDateUploadedUnix() {
        return dateUploadedUnix;
    }

    public void setDateUploadedUnix(String dateUploadedUnix) {
        this.dateUploadedUnix = dateUploadedUnix;
    }
    public Integer getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(Integer movieCount) {
        this.movieCount = movieCount;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
