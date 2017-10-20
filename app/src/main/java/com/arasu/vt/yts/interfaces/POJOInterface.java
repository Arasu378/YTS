package com.arasu.vt.yts.interfaces;

import com.arasu.vt.yts.pojo.MovieDetailResponse;
import com.arasu.vt.yts.pojo.ParentalResponse;
import com.arasu.vt.yts.pojo.RootObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by kyros on 29-09-2017.
 */

public interface POJOInterface {
    @GET("list_movies.json")
    Call<RootObject>getMoviesList(@Query("page") int pageNo,@Query("limit")int limit);
    @GET("movie_details.json")
    Call<MovieDetailResponse>getMovieDetails(@Query("movie_id")String movie_id,@Query("with_images")boolean with_images,@Query("with_cast")boolean with_cast);
    @GET
    Call<ResponseBody>downloadFile(@Url String url);
    @GET("movie_parental_guides.json")
    Call<ParentalResponse>getParentalDetails(@Query("movie_id")String movie_id);

}
