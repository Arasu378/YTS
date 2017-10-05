package com.arasu.vt.yts.activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.CastAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.fragments.Fragment1080p;
import com.arasu.vt.yts.fragments.Fragment720p;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Cast;
import com.arasu.vt.yts.pojo.Movie;
import com.arasu.vt.yts.pojo.MovieDetailResponse;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    private double movieId;
    private String MovieIdString=null;
    private TextView movie_title,movie_year,movie_genres,movie_likes,movie_imdb,movie_description,movie_downloaded_times,movie_upload_date;
    private ImageView movie_poster,medium_image_1,medium_image_2,medium_image_3;
    private Button button_720p,button_1080p;
    private RecyclerView cast_recycler;
    private ProgressDialog progressDialog;
    private CastAdapter adapter;
    private ArrayList<Cast>castList=new ArrayList<Cast>();
    private LinearLayout background_poster_image;
    private YouTubeThumbnailView thumbnail_youtube;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        try{
            Bundle bundle=getIntent().getExtras();
            movieId=bundle.getDouble("movieid");
            MovieIdString=String.valueOf(movieId);
            MovieIdString=MovieIdString.substring(0,MovieIdString.length()-2);
        }catch (Exception e){
            e.printStackTrace();
        }
        thumbnail_youtube=(YouTubeThumbnailView)findViewById(R.id.thumbnail_youtube);
        background_poster_image=(LinearLayout)findViewById(R.id.background_poster_image);
        movie_poster=(ImageView)findViewById(R.id.movie_poster);
        medium_image_1=(ImageView)findViewById(R.id.medium_image_1);
        medium_image_2=(ImageView)findViewById(R.id.medium_image_2);
        medium_image_3=(ImageView)findViewById(R.id.medium_image_3);
        button_720p=(Button)findViewById(R.id.button_720p);
        button_1080p=(Button)findViewById(R.id.button_1080p);
        cast_recycler=(RecyclerView)findViewById(R.id.cast_recycler);
        movie_title=(TextView)findViewById(R.id.movie_title);
        movie_year=(TextView)findViewById(R.id.movie_year);
        movie_genres=(TextView)findViewById(R.id.movie_genres);
        movie_likes=(TextView)findViewById(R.id.movie_likes);
        movie_imdb=(TextView)findViewById(R.id.movie_imdb);
        movie_description=(TextView)findViewById(R.id.movie_description);
        movie_downloaded_times=(TextView)findViewById(R.id.movie_downloaded_times);
        movie_upload_date=(TextView)findViewById(R.id.movie_upload_date);
        getMovieDetailsMethod(MovieIdString);
        button_720p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"button_720p",Toast.LENGTH_SHORT).show();

            }
        });
        button_1080p.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"button_1080p",Toast.LENGTH_SHORT).show();

                    }
                });
        thumbnail_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void getMovieDetailsMethod(String movieIdString) {
        showDialog();
        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<MovieDetailResponse>call=apiService.getMovieDetails(movieIdString,true,true);
        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                dismissDialog();
                String status=response.body().getStatus();
                String statusMessage=response.body().getStatusMessage();
                if(status.equals("ok")){
                    castList.clear();
                    String medium_picture=response.body().getData().getMovie().getMediumCoverImage();
                    if(medium_picture!=null){
                        Picasso.with(MovieDetailsActivity.this).load(medium_picture).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                movie_poster.setBackground(new BitmapDrawable(bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
                    }
                    String title=response.body().getData().getMovie().getTitle();
                    if(title!=null){
                        movie_title.setText(title);
                    }
                    String background_image=response.body().getData().getMovie().getBackgroundImage();
                    if(background_image!=null){
                        Picasso.with(MovieDetailsActivity.this).load(background_image).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                background_poster_image.setBackground(new BitmapDrawable(bitmap));
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
                    }
                    int year=response.body().getData().getMovie().getYear();
                    String years=String.valueOf(year);
                    if(years!=null){
                        movie_year.setText(years);
                    }
                    ArrayList<String>genres=response.body().getData().getMovie().getGenres();
                    StringBuilder builder = new StringBuilder();
                    for(int i=0; i<genres.size();i++){
                        int finalsize=i+1;
                        String value=genres.get(i)+" / ";
                        if(genres.size()==finalsize){
                            value=genres.get(i);
                        }
                        builder.append(value );
                    }
                    movie_genres.setText(builder.toString());

                    int movieLikes=response.body().getData().getMovie().getLikeCount();
                    String likes=String.valueOf(movieLikes);
                    if(likes!=null){
                        movie_likes.setText(likes);
                    }
                    double imdb=response.body().getData().getMovie().getRating();
                    String imdbString=String.valueOf(imdb);
                    if(imdbString!=null){
                        movie_imdb.setText(imdbString);
                    }
                    String movieDescription=response.body().getData().getMovie().getDescriptionFull();
                    if(movieDescription!=null){
                        movie_description.setText(movieDescription);
                    }
                    int movieDownloadedTimes=response.body().getData().getMovie().getDownloadCount();
                    String value="Downloaded "+movieDownloadedTimes+" times";
                    movie_downloaded_times.setText(value);
                    String uploaded=response.body().getData().getMovie().getDateUploaded();
                    if(uploaded!=null){
                        movie_upload_date.setText(uploaded);

                    }
                    castList=response.body().getData().getMovie().getCast();
                    if(castList!=null && castList.size()!=0){
                        adapter=new CastAdapter(MovieDetailsActivity.this,castList);
                        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                        cast_recycler.setLayoutManager(layoutManager);
                        cast_recycler.setItemAnimator(new DefaultItemAnimator());
                        cast_recycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getApplicationContext(),"Cast size is zero! ",Toast.LENGTH_SHORT).show();

                    }
                    String  medium_screenshot_1=response.body().getData().getMovie().getMediumScreenshotImage1();
                    String medium_screenshot_2=response.body().getData().getMovie().getMediumScreenshotImage2();
                    String medium_screenshot_3=response.body().getData().getMovie().getMediumScreenshotImage3();
                    if(medium_screenshot_1!=null){
                        Picasso.with(MovieDetailsActivity.this).load(medium_screenshot_1).into(medium_image_1);
                    }
                    if(medium_screenshot_2!=null){
                        Picasso.with(MovieDetailsActivity.this).load(medium_screenshot_2).into(medium_image_2);
                    } if(medium_screenshot_3!=null){
                        Picasso.with(MovieDetailsActivity.this).load(medium_screenshot_3).into(medium_image_3);
                    }

                    final String youtubeThumbnail=response.body().getData().getMovie().getYtTrailerCode();
                    if(youtubeThumbnail!=null){
                        String finalvalue="https://img.youtube.com/vi/"+youtubeThumbnail+"/hqdefault.jpg";
                        Picasso.with(MovieDetailsActivity.this).load(finalvalue).into(thumbnail_youtube);
                    }
                    thumbnail_youtube.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(youtubeThumbnail!=null){
                                watchYoutubeVideo(MovieDetailsActivity.this,youtubeThumbnail);

                            }else{
                                Toast.makeText(getApplicationContext(),"No youtube trailers found!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Bundle bundle= new Bundle();
                  //  bundle.putString("dealerid",dealerid);
                   // bundle.putString("Time",time);
                    Fragment720p frag=new Fragment720p();
                    frag.setArguments(bundle);
                    android.support.v4.app.FragmentTransaction fragmentTrans=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTrans.replace(R.id.container,frag);

                    //  fragmentTrans.addToBackStack(null);
                    fragmentTrans.commit();
                //    fragmentTrans.detach(frag);


                }else{
                    Log.e("Error: ",""+statusMessage);
                    Toast.makeText(getApplicationContext(),"Error : "+statusMessage,Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                dismissDialog();
                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();

                try{
                    Log.e("Errror: ",""+t.getMessage());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);

        radioGroup1.check(R.id.picture_720);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                switch (checkedId)
                {
                    case R.id.picture_720:
                        Bundle bundle= new Bundle();
                      //  bundle.putString("dealerid",dealerid);
                      //  bundle.putString("Time",time);
                        Fragment720p frag=new Fragment720p();
                        frag.setArguments(bundle);
                        android.support.v4.app.FragmentTransaction fragmentTrans=
                                getSupportFragmentManager().beginTransaction();
                        fragmentTrans.replace(R.id.container,frag);

                        //  fragmentTrans.addToBackStack(null);
                        fragmentTrans.commit();
                    //    fragmentTrans.detach(frag);
                        break;
                    case R.id.picture_1080:
                        Bundle bundle1= new Bundle();
                      //  bundle1.putString("dealerid",dealerid);
                       // bundle1.putString("Time",time);
                        Fragment1080p fragment=new Fragment1080p();
                        fragment.setArguments(bundle1);
                        android.support.v4.app.FragmentTransaction fragmentTransaction=
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,fragment);
                        // fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                      //  fragmentTransaction.detach(fragment);
                        break;

                    default:
                        break;
                }
            }
        });
    }
    private  void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
    private void showDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(MovieDetailsActivity.this);

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

}
