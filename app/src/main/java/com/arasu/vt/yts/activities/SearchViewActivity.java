package com.arasu.vt.yts.activities;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.clients.EndlessRecyclerViewScrollListener;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Movie;
import com.arasu.vt.yts.pojo.RootObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewActivity extends AppCompatActivity {
    private List<Movie> movieList=new ArrayList<Movie>();
    private MovieListAdapter adapter;
    private int Searchedcurrentpage=1;
    private ProgressDialog progressDialog;
    private EndlessRecyclerViewScrollListener scrollListener;
    private RecyclerView search_recycler;
    private SwipeRefreshLayout swipe_id_search;
    private SearchView image_search_act;
    private String searchString=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        search_recycler=(RecyclerView)findViewById(R.id.search_recycler);
        try{
            Bundle bundle=getIntent().getExtras();
            searchString=bundle.getString("query");
            if(searchString!=null){
                queryText(searchString,Searchedcurrentpage,"Bundle");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        image_search_act=(SearchView)findViewById(R.id.image_search_act);
//        search_recycler.setHasFixedSize(true);
//        search_recycler.setItemViewCacheSize(20);
//        search_recycler.setDrawingCacheEnabled(true);
//        search_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        search_recycler.setLayoutManager(mLayoutManager);
        search_recycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        swipe_id_search=(SwipeRefreshLayout)findViewById(R.id.swipe_id_search);
        search_recycler.setItemAnimator(new DefaultItemAnimator());

        swipe_id_search.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                if(searchString!=null){
                    movieList.clear();
                    queryText(searchString,Searchedcurrentpage,"Swipe Refresh");

                }

            }
        });
        image_search_act.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieList.clear();
                searchString=query;
                queryText(query,Searchedcurrentpage,"Query string");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //   queryText(newText);
                return true;
            }
        });
//        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to the bottom of the list
//                page=page+Searchedcurrentpage;
//                queryText(searchString,page,"Load More");
//            }
//        };
        search_recycler.addOnScrollListener(scrollListener);
        adapter=new MovieListAdapter(SearchViewActivity.this,movieList,search_recycler);

        search_recycler.setAdapter(adapter);
    }
    private void queryText(String query,int page,String  value){
        Log.d("Query : ",""+query);
        showDialog();
        Toast.makeText(getApplicationContext(),"Value "+value,Toast.LENGTH_SHORT).show();
        POJOInterface apiClient= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<RootObject> call=apiClient.getQueryList(query,page);
        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                dismissDialog();
                swipe_id_search.setRefreshing(false);
               // movieList.clear();
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
                swipe_id_search.setRefreshing(false);

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
            progressDialog=new ProgressDialog(SearchViewActivity.this);

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
