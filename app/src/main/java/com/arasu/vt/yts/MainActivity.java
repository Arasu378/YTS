package com.arasu.vt.yts;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.model.ScrollListenerMovies;
import com.arasu.vt.yts.model.ScrollListenerPicture;
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
    private int Searchedcurrentpage=1;
    private ProgressDialog progressDialog;
    private ScrollListenerMovies listenerMovies;
    private  TextView total_movie_list;
    private Button textView_previous,textView_total,textView_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe_id=(SwipeRefreshLayout)findViewById(R.id.swipe_id) ;
        recycler_view_movie=(RecyclerView)findViewById(R.id.recycler_view_movie);
        total_movie_list=(TextView)findViewById(R.id.total_movie_list);
        textView_next=(Button)findViewById(R.id.textView_next);
        textView_total=(Button)findViewById(R.id.textView_total);
        textView_previous=(Button)findViewById(R.id.textView_previous);
        getMoviesList(Searchedcurrentpage);


        swipe_id.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                getMoviesList(Searchedcurrentpage);

            }
        });
        if(Searchedcurrentpage==1){
            textView_previous.setVisibility(View.GONE);
        }else{
            textView_previous.setVisibility(View.VISIBLE);
        }
        textView_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchedcurrentpage--;
                getMoviesList(Searchedcurrentpage);
            }
        });
        textView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchedcurrentpage++;
                getMoviesList(Searchedcurrentpage);
            }
        });


    }
    private void showDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(MainActivity.this);

            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
           int value= progressDialog.getProgress();
            progressDialog.setMessage("Please wait...");


        }
        progressDialog.show();
    }
    private void dismissDialog(){
        if(progressDialog!=null&& progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissDialog();
    }

    private void getMoviesList(int page) {
        showDialog();
        POJOInterface apiService=
                ApiClient.getRetrofit().create(POJOInterface.class);
        Call<RootObject>call=apiService.getMoviesList(page);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                dismissDialog();
                swipe_id.setRefreshing(false);
                if(Searchedcurrentpage==1){
                    textView_previous.setVisibility(View.GONE);
                }else{
                    textView_previous.setVisibility(View.VISIBLE);
                }
               movieList.clear();
                    try{
                        String returned_response=new Gson().toJson(response.body());
                        Log.d("value : ",""+returned_response);
                        String tMvo=String.valueOf(response.body().getData().getMovieCount());
                        tMvo=tMvo.substring(0,tMvo.length()-2);
                        String text=tMvo+" YIFY Movies Found";
                        total_movie_list.setText(text);
                        double totalmovie=response.body().getData().getMovieCount();
                        String pageNumber=String .valueOf(response.body().getData().getPageNumber());
                        double balanceMovie=totalmovie/20;
                        String finalbalanceMovie=String.valueOf(balanceMovie);
                        pageNumber=pageNumber.substring(0,pageNumber.length()-2);
                        finalbalanceMovie=finalbalanceMovie.substring(0,finalbalanceMovie.length()-2);
                        String centeredText=pageNumber+" of "+finalbalanceMovie;
                        textView_total.setText(centeredText);
                        ArrayList<Movy>mov=response.body().getData().getMovies();
                        movieList=mov;
                        adapter=new MovieListAdapter(MainActivity.this,movieList);
                         StaggeredGridLayoutManager stmanager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

                        final LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                        recycler_view_movie.setLayoutManager(stmanager);
                        recycler_view_movie.setItemAnimator(new DefaultItemAnimator());
                        recycler_view_movie.setAdapter(adapter);
                        adapter.notifyDataSetChanged();



                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if(adapter!=null){
                        adapter.notifyDataSetChanged();

                    }



            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                swipe_id.setRefreshing(false);
dismissDialog();
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
