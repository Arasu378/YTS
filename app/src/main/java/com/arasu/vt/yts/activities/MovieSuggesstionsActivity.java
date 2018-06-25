package com.arasu.vt.yts.activities;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Movie;
import com.arasu.vt.yts.pojo.MovieSuggestionsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSuggesstionsActivity extends AppCompatActivity {
    private String movieId;
    private RecyclerView movie_suggestion_recycler;
    private ProgressDialog progressDialog;
    private List<Movie> movieList=new ArrayList<Movie>();
    private MovieListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_suggesstions);
        movie_suggestion_recycler=(RecyclerView)findViewById(R.id.movie_suggestion_recycler);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        movie_suggestion_recycler.setHasFixedSize(true);
        movie_suggestion_recycler.setItemViewCacheSize(20);
        movie_suggestion_recycler.setDrawingCacheEnabled(true);
        movie_suggestion_recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        movie_suggestion_recycler.setLayoutManager(mLayoutManager);
        movie_suggestion_recycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        movie_suggestion_recycler.setItemAnimator(new DefaultItemAnimator());

        try{
            Bundle bundle=getIntent().getExtras();
            movieId=bundle.getString("movieid");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(movieId!=null){
            movieSuggestionAPI(movieId);
        }else{
            Toast.makeText(getApplicationContext(),"Movie id is 0",Toast.LENGTH_SHORT).show();
        }

    }

    private void movieSuggestionAPI(String movieId) {
        showDialog();
        movieId=movieId.replace(".0","");
        Log.d("MovieId Original : ",movieId);

        int mId=Integer.parseInt(movieId);
        Log.d("MovieId : ",movieId);
        Log.d("MovieId integer : ",""+mId);

        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<MovieSuggestionsResponse>call=apiService.getMovieSuggestions(movieId);
        call.enqueue(new Callback<MovieSuggestionsResponse>() {
            @Override
            public void onResponse(Call<MovieSuggestionsResponse> call, Response<MovieSuggestionsResponse> response) {
                dismissDialog();
                String status=response.body().getStatus();
                String status_message=response.body().getStatusMessage();
                if(status.equals("ok")){
                    long movie_suggesstions_count=response.body().getData().getMovie_count();
                    if(movie_suggesstions_count!=0){
                        movieList=response.body().getData().getMovies() ;
                        adapter=new MovieListAdapter(MovieSuggesstionsActivity.this,movieList,movie_suggestion_recycler);
                        movie_suggestion_recycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getApplicationContext(),"No Movie Suggestions!!! ",Toast.LENGTH_SHORT).show();

                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Message : "+status_message,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MovieSuggestionsResponse> call, Throwable t) {
                dismissDialog();
                if(t!=null){
                    String errorMessage=t.getMessage();
                    Toast.makeText(getApplicationContext(),"Error : "+errorMessage,Toast.LENGTH_SHORT).show();
                    Log.e("Error : ",""+t.getMessage());
                }

            }
        });

    }
    private void showDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(MovieSuggesstionsActivity.this);
            progressDialog.setMessage("please wait....");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    private void dismissDialog(){
        if(progressDialog!=null&& progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
