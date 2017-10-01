package com.arasu.vt.yts.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kyros on 29-09-2017.
 */

public interface POJOInterface {
    @GET("list_movies.json")
    Call<Object>getMoviesList(@Query("page") int pageNo);
}
