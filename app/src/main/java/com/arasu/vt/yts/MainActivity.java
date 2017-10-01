package com.arasu.vt.yts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_edit=(TextView)findViewById(R.id.tv_edit);
        getMoviesList();

    }

    private void getMoviesList() {
        POJOInterface apiService=
                ApiClient.getRetrofit().create(POJOInterface.class);
        Call<Object>call=apiService.getMoviesList(1);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                    try{
                     String st=   new Gson().toJson(response.body());
                        Log.d("VL",""+st);
                        tv_edit.setText(st);
                        JSONObject obj=new JSONObject(st);
                        String status=obj.getString("status");
                        String status_message=obj.getString("status_message");
                        JSONObject meta=obj.getJSONObject("@meta");
                        String server_time=meta.getString("server_time");
                        String server_timezone=meta.getString("server_timezone");
                        String api_version=meta.getString("api_version");
                        String execution_time=meta.getString("execution_time");
                        JSONObject data=obj.getJSONObject("data");
                        int movie_count=data.getInt("movie_count");
                        int limit=data.getInt("limit");
                        int page_number=data.getInt("page_number");
                        JSONArray movies=data.getJSONArray("movies");
                        for(int i=0; i<movies.length();i++){
                            JSONObject first=movies.getJSONObject(i);
                            int id=first.getInt("id");
                            String url=first.getString("url");
                            String imdb_code=first.getString("imdb_code");
                            String title=first.getString("title");
                            String title_english=first.getString("title_english");
                            String title_long=first.getString("title_long");
                            String slug=first.getString("slug");
                            int year=first.getInt("year");
                            int rating=first.getInt("rating");
                            int runtime=first.getInt("runtime");
                            String summary=first.getString("summary");
                            String description_full=first.getString("description_full");
                            String synopsis=first.getString("synopsis");
                            String trailercode=first.getString("yt_trailer_code");
                            String language=first.getString("language");
                            String mpa_rating=first.getString("mpa_rating");
                            String background_image=first.getString("background_image");
                            String background_image_original=first.getString("background_image_original");
                            String small_cover_image=first.getString("small_cover_image");
                            String medium_cover_image=first.getString("medium_cover_image");
                            String large_cover_image=first.getString("large_cover_image");
                            String state=first.getString("state");
                            String date_uploaded=first.getString("date_uploaded");
                            JSONArray torrents=first.getJSONArray("torrents");
                            for(int k=0; k<torrents.length();k++){
                                JSONObject second=torrents.getJSONObject(k);
                                String urL=second.getString("url");
                                String hash=second.getString("hash");
                                String quality=second.getString("quality");
                                int seeds=second.getInt("seeds");
                                int peers=second.getInt("peers");
                                String size=second.getString("size");
                                String date_Uploaded=second.getString("date_uploaded");

                            }
                            JSONArray genres=first.getJSONArray("genres");
                            for(int j=0;j<genres.length();j++){
                                String value=genres.getString(j);
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                try{
                    t.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
