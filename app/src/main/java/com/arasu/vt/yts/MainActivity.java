package com.arasu.vt.yts;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Movy;
import com.arasu.vt.yts.pojo.RootObject;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movy>movieList=new ArrayList<Movy>();
    private MovieListAdapter adapter;
    private RecyclerView recycler_view_movie;
    private SwipeRefreshLayout swipe_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe_id=(SwipeRefreshLayout)findViewById(R.id.swipe_id) ;
        recycler_view_movie=(RecyclerView)findViewById(R.id.recycler_view_movie);

        getMoviesList();
        swipe_id.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                getMoviesList();

            }
        });

    }

    private void getMoviesList() {
        POJOInterface apiService=
                ApiClient.getRetrofit().create(POJOInterface.class);
        Call<RootObject>call=apiService.getMoviesList(1);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                swipe_id.setRefreshing(false);

                movieList.clear();
                    try{
                        String returned_response=new Gson().toJson(response.body());
                        Log.d("value : ",""+returned_response);
                        movieList=response.body().getData().getMovies();
                        adapter=new MovieListAdapter(MainActivity.this,movieList);
                        final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                        recycler_view_movie.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                        recycler_view_movie.setItemAnimator(new DefaultItemAnimator());
                        recycler_view_movie.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                swipe_id.setRefreshing(false);

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
