package com.arasu.vt.yts.clients;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kyros on 29-09-2017.
 */

public class ApiClient {
    //use yts.pe url also it is working 21-10-2017
    public static final String BASE_URL1="https://yts.unblocked.re/api/v2/";
    public static final String BASE_URL2="https://yts.ag/api/v2/";
    public static final String BASE_URL3="https://yts.unblocked.kim/api/v2/";
    public static final String BASE_URL4="https://yts.immunicity.kim/api/v2/";
    public static final String BASE_URL5="https://yts.bypassed.kim/api/v2/";
    public static final String CONSTANT_IMAGE_URL="https://www.yts.am";
    public static final String BASE_URL="https://myjson-proxy.herokuapp.com/api/v2/";
    public static final String BASE_URL6="http://yify.is/api/v2/";
    public static final String END_URL="https://www.yts.am/api/v2/";
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(END_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                   .build();
        }
        return retrofit;
    }


}
