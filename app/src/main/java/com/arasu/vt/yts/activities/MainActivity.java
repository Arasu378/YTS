package com.arasu.vt.yts.activities;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.clients.EndlessRecyclerViewScrollListener;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.model.ScrollListenerMovies;
import com.arasu.vt.yts.pojo.Movie;
import com.arasu.vt.yts.pojo.Movy;
import com.arasu.vt.yts.pojo.RootObject;
import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Movie>movieList=new ArrayList<Movie>();
    private MovieListAdapter adapter;
    private RecyclerView recycler_view_movie;
    private SwipeRefreshLayout swipe_id;
    private int Searchedcurrentpage=1;
    private ProgressDialog progressDialog;
    private ScrollListenerMovies listenerMovies;
    private  TextView total_movie_list;
 //  private Button textView_total;
    private StaggeredGridLayoutManager stmanager;
    private static final String TAG=MainActivity.class.getSimpleName();
    private Handler handler;
    private int limit=20;
    private SearchView image_search;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipe_id=(SwipeRefreshLayout)findViewById(R.id.swipe_id) ;
        recycler_view_movie=(RecyclerView)findViewById(R.id.recycler_view_movie);
        image_search=(SearchView)findViewById(R.id.image_search);
       // recycler_view_movie.setHasFixedSize(true);
        handler=new Handler();
        total_movie_list=(TextView)findViewById(R.id.total_movie_list);
   //     textView_next=(Button)findViewById(R.id.textView_next);
      //  textView_total=(Button)findViewById(R.id.textView_total);
     //   textView_previous=(Button)findViewById(R.id.textView_previous);
      //  RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recycler_view_movie.setHasFixedSize(true);
        recycler_view_movie.setItemViewCacheSize(20);
        recycler_view_movie.setDrawingCacheEnabled(true);
        recycler_view_movie.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recycler_view_movie.setLayoutManager(mLayoutManager);
        recycler_view_movie.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        recycler_view_movie.setItemAnimator(new DefaultItemAnimator());

        getMoviesList(Searchedcurrentpage);
        stmanager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                page=page+Searchedcurrentpage;
                getMoviesList(page);
            }
        };

        swipe_id.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                getMoviesList(Searchedcurrentpage);

            }
        });
//        if(Searchedcurrentpage==1){
//            textView_previous.setVisibility(View.GONE);
//        }else{
//            textView_previous.setVisibility(View.VISIBLE);
//        }
//        textView_previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Searchedcurrentpage--;
//                getMoviesList(Searchedcurrentpage);
//            }
//        });
//        textView_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Searchedcurrentpage++;
//                getMoviesList(Searchedcurrentpage);
//            }
//        });
        image_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        image_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
             //   queryText(newText);
                return true;
            }
        });
        recycler_view_movie.addOnScrollListener(scrollListener);
        adapter=new MovieListAdapter(MainActivity.this,movieList,recycler_view_movie);

        recycler_view_movie.setAdapter(adapter);

    }
    private void queryText(String query){
        Log.d("Query : ",""+query);
        showDialog();
        POJOInterface apiClient= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<RootObject>call=apiClient.getQueryList(query);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                dismissDialog();
                movieList.clear();
                List<Movie> mov=response.body().getData().getMovies();
                if(mov!=null&&mov.size()!=0){
                    movieList.addAll(mov);

                }else{
                    Toast.makeText(getApplicationContext(),"No Movie found!",Toast.LENGTH_SHORT).show();
                }
                Log.d("Query ","Response : "+response.body().toString()+" / "+movieList.size());

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {
                dismissDialog();
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                try{
                    Log.e("Error: ","arasu YTS : "+t.getMessage());
                    t.getMessage();
                    t.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }

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
        Log.d("Page No : ",""+page);
        showDialog();
        POJOInterface apiService=
                ApiClient.getRetrofit().create(POJOInterface.class);
        Call<RootObject>call=apiService.getMoviesList(page,limit);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                dismissDialog();
                swipe_id.setRefreshing(false);
//                if(Searchedcurrentpage==1){
//                    textView_previous.setVisibility(View.GONE);
//                }else{
//                    textView_previous.setVisibility(View.VISIBLE);
//                }
              // movieList.clear();
                    try{
                        String returned_response=new Gson().toJson(response.body());
                        String status=response.body().getStatus();
                        String status_message=response.body().getStatusMessage();
                        if(status.equals("ok")){
                            Log.d("value : ",""+returned_response);
                            String tMvo=String.valueOf(response.body().getData().getMovieCount());
                            tMvo=tMvo.substring(0,tMvo.length()-2);
                            String text=tMvo+" YIFY Movies Found";
                            total_movie_list.setText(text);
                            double totalmovie=response.body().getData().getMovieCount();
                            String pageNumber=String .valueOf(response.body().getData().getPageNumber());
                            Log.d("PageNumber : ",pageNumber);
                            double balanceMovie=totalmovie/20;
                            String finalbalanceMovie=String.valueOf(balanceMovie);
                            try{
                                pageNumber=pageNumber.substring(0,pageNumber.length()-2);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            finalbalanceMovie=finalbalanceMovie.substring(0,finalbalanceMovie.length()-2);
                            String centeredText=pageNumber+" of "+finalbalanceMovie;
                         //   textView_total.setText(centeredText);
                            List<Movie> mov=response.body().getData().getMovies();
                            movieList.addAll(mov);

                            adapter.notifyDataSetChanged();
                            Log.d("Movie : ","Movie size : "+movieList.size());
                            adapter.setOnLoadMoreListerner(new MovieListAdapter.OnLoadMoreListener() {
                                @Override
                                public void onLoadMore() {

                                }
                            });

                        }else{
                            Toast.makeText(getApplicationContext(),"Error : "+status_message,Toast.LENGTH_SHORT).show();
                        }


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
                    Log.e("Error: ","arasu YTS : "+t.getMessage());
                    t.getMessage();
                    t.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
