package com.arasu.vt.yts.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.arasu.vt.yts.model.FragmentModel;
import com.arasu.vt.yts.pojo.Cast;
import com.arasu.vt.yts.pojo.MovieDetailResponse;
import com.arasu.vt.yts.pojo.Torrent;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE = 1;
    private double movieId;
    private String MovieIdString=null;
    private TextView movie_title,movie_year,movie_genres,movie_likes,movie_imdb,movie_description,movie_downloaded_times,movie_upload_date,parental_guide_text,movie_suggestion_text;
    private ImageView movie_poster,medium_image_1,medium_image_2,medium_image_3;
    private Button button_720p,button_1080p,imdb_chrome;
    private RecyclerView cast_recycler;
    private ProgressDialog progressDialog;
    private CastAdapter adapter;
    private List<Cast>castList=new ArrayList<Cast>();
    private LinearLayout background_poster_image;
    private YouTubeThumbnailView thumbnail_youtube;
    private String movieTitle=null;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


     // initCollapsingToolbar();
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(MovieDetailsActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(MovieDetailsActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MovieDetailsActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)&&ActivityCompat.shouldShowRequestPermissionRationale(MovieDetailsActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(MovieDetailsActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        try{
            Bundle bundle=getIntent().getExtras();
            movieId=bundle.getDouble("movieid");
            MovieIdString=String.valueOf(movieId);
            Log.d("MovieIdString: ",MovieIdString);

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
        imdb_chrome=(Button)findViewById(R.id.imdb_chrome);
        cast_recycler=(RecyclerView)findViewById(R.id.cast_recycler);
        movie_title=(TextView)findViewById(R.id.movie_title);
        movie_year=(TextView)findViewById(R.id.movie_year);
        movie_genres=(TextView)findViewById(R.id.movie_genres);
        movie_likes=(TextView)findViewById(R.id.movie_likes);
        movie_imdb=(TextView)findViewById(R.id.movie_imdb);
        movie_description=(TextView)findViewById(R.id.movie_description);
        movie_downloaded_times=(TextView)findViewById(R.id.movie_downloaded_times);
        movie_upload_date=(TextView)findViewById(R.id.movie_upload_date);
        parental_guide_text=(TextView)findViewById(R.id.parental_guide_text);
        movie_suggestion_text=(TextView)findViewById(R.id.movie_suggestion_text);

        getMovieDetailsMethod(MovieIdString);

        thumbnail_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        parental_guide_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieDetailsActivity.this,ParentalGuideActivity.class);
                intent.putExtra("movieid",movieId);
                intent.putExtra("title",movieTitle);
                startActivity(intent);

            }
        });
        movie_suggestion_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MovieId original : ",""+movieId);
                Intent intent=new Intent(MovieDetailsActivity.this,MovieSuggesstionsActivity.class);
                intent.putExtra("movieid",MovieIdString);
                startActivity(intent);

            }
        });


    }




    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_WRITE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(),"Please allow read,write permission for storage",Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
    private void getMovieDetailsMethod(String movieIdString) {
        showDialog();
        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<MovieDetailResponse>call=apiService.getMovieDetails(movieIdString,true,true);
        call.enqueue(new Callback<MovieDetailResponse>() {
            @Override
            public void onResponse(Call<MovieDetailResponse> call, final Response<MovieDetailResponse> response) {
                dismissDialog();
                Log.d("YTSDET : : ", new Gson().toJson(response.body()));
                final String status=response.body().getStatus();
                String statusMessage=response.body().getStatusMessage();
                if(status.equals("ok")){
                    castList.clear();
                    String medium_picture=response.body().getData().getMovie().getMediumCoverImage();

                    if(medium_picture!=null){
                        Picasso.get().load(medium_picture).into(movie_poster);
                    }
                    final String imdbCode=response.body().getData().getMovie().getImdbCode();
                    imdb_chrome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openImdb(imdbCode);

                        }
                    });
                    String title=response.body().getData().getMovie().getTitle();
                    movieTitle=title;
                    if(title!=null){
                        movie_title.setText(title);

                    }
                    String background_image=response.body().getData().getMovie().getBackgroundImage();
                    if(background_image!=null){
                       // background_image=background_image.replace("https://yts.ag",ApiClient.BASE_URL);

//                        Picasso.with(MovieDetailsActivity.this).load(background_image).into(new Target() {
//                            @Override
//                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                             //   background_poster_image.setBackground(new BitmapDrawable(bitmap));
//                            }
//
//                            @Override
//                            public void onBitmapFailed(Drawable errorDrawable) {
//
//                            }
//
//                            @Override
//                            public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                            }
//                        });
                    }
                    String year=response.body().getData().getMovie().getYear();
                    String years=String.valueOf(year);
                    if(years!=null){
                        movie_year.setText(years);
                    }
                    List<String>genres=response.body().getData().getMovie().getGenres();
                    if (genres != null) {
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
                    }


                    String movieLikes=response.body().getData().getMovie().getLikeCount();
                    String likes=String.valueOf(movieLikes);
                    if(likes!=null){
                        movie_likes.setText(likes);
                    }
                    String imdb=response.body().getData().getMovie().getRating();
                    String imdbString=String.valueOf(imdb);
                    if(imdbString!=null){
                        movie_imdb.setText(imdbString);
                    }
                    String movieDescription=response.body().getData().getMovie().getDescriptionFull();
                    if(movieDescription!=null){
                        movie_description.setText(movieDescription);
                    }
                    String movieDownloadedTimes=response.body().getData().getMovie().getDownloadCount();
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
                    String  medium_screenshot_1=response.body().getData().getMovie().getMediumScreenshot1Image();
                    String medium_screenshot_2=response.body().getData().getMovie().getMediumScreenshot2Image();
                    String medium_screenshot_3=response.body().getData().getMovie().getMediumScreenshot3Image();
                    String large_screenshot_1=response.body().getData().getMovie().getLargeScreenshot1Image();
                    String large_screenshot_2=response.body().getData().getMovie().getLargeScreenshot2Image();
                    String large_screenshot_3=response.body().getData().getMovie().getLargeScreenshot3Image();
                    ArrayList<String>largePictuelist=new ArrayList<String>();
                    largePictuelist.add(0,large_screenshot_1);
                    largePictuelist.add(1,large_screenshot_2);
                    largePictuelist.add(2,large_screenshot_3);
                    Gson gSon=new Gson();
                    String pictureList=gSon.toJson(largePictuelist);
                    Log.d("MovieDetails",""+pictureList);
                    FragmentModel.getHolder().setLargePictureList(pictureList);


                    if(medium_screenshot_1!=null){

                        Picasso.get().load(medium_screenshot_1).into(medium_image_1);
                    }
                    if(medium_screenshot_2!=null){

                        Picasso.get().load(medium_screenshot_2).into(medium_image_2);
                    } if(medium_screenshot_3!=null){

                        Picasso.get().load(medium_screenshot_3).into(medium_image_3);
                    }
                        medium_image_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent=new Intent(MovieDetailsActivity.this,FullScreenActivity.class);
                                startActivity(intent);
                            }
                        });
                    medium_image_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MovieDetailsActivity.this,FullScreenActivity.class);
                            startActivity(intent);
                        }
                    });
                    medium_image_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MovieDetailsActivity.this,FullScreenActivity.class);
                            startActivity(intent);
                        }
                    });
                    final String youtubeThumbnail=response.body().getData().getMovie().getYtTrailerCode();
                    if(youtubeThumbnail!=null){
                        String finalvalue="https://img.youtube.com/vi/"+youtubeThumbnail+"/hqdefault.jpg";
                        Picasso.get().load(finalvalue).into(thumbnail_youtube);
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
                    List<Torrent>torrentzList=response.body().getData().getMovie().getTorrents();

                        Gson gson=new Gson();
                        String torrentzString=gson.toJson(torrentzList);
                        Log.d("torrentz: ",""+torrentzString);
                    FragmentModel.getHolder().setTorrentZlist(torrentzString);
                    String language=response.body().getData().getMovie().getLanguage();
                    FragmentModel.getHolder().setLanguage(language);
                    String mpa_rating=response.body().getData().getMovie().getMpaRating();
                    FragmentModel.getHolder().setMpa_rating(mpa_rating);
                    String runtime=response.body().getData().getMovie().getRuntime();
                    FragmentModel.getHolder().setRuntime(runtime);
                    button_720p.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Torrent>torrentzList=response.body().getData().getMovie().getTorrents();
                            if(torrentzList!=null &&torrentzList.size()!=0){
                                String url=torrentzList.get(0).getUrl();
                                download720p(url,response.body().getData().getMovie().getTitle(),720);

                            }

                        }


                    });
                    button_1080p.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Torrent>torrentzList=response.body().getData().getMovie().getTorrents();
                            if(torrentzList!=null &&torrentzList.size()!=0){
                                String url=torrentzList.get(1).getUrl();
                                download720p(url,response.body().getData().getMovie().getTitle(),1080);

                            }
                        }
                    });


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
                        try{
                            adapter.notifyDataSetChanged();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                }else{
                    Log.e("Error: ",""+statusMessage);
                    Toast.makeText(getApplicationContext(),"Error : "+statusMessage,Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
                dismissDialog();
                Toast.makeText(getApplicationContext(),"" + t.getMessage(),Toast.LENGTH_SHORT).show();

                try{
                    Log.e("Error: ",""+t.getMessage());
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



    private void download720p(String url, final String title, final int value) {
        showDialog();
      POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        Call<ResponseBody>call=apiService.downloadFile(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissDialog();
                if(response.isSuccessful()){
                    boolean writtenToDisk=writeResponseBodyToDisk(response.body(),title,value);
                    if(writtenToDisk){
                        openFolder();

                    }else{
                        Toast.makeText(getApplicationContext(),"Movie not downloaded!",Toast.LENGTH_SHORT).show();
                    }
                    Log.d("WriteExternal: ","file download was a success? "+writtenToDisk);
                }else{
                    Log.e("Error : ","server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dismissDialog();
                Log.e("Error : ",""+t.getMessage());
            }
        });
    }

    private void openFolder() {
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to open the folder ?");
        alertDialogBuilder.setPositiveButton("Open", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory() + "/YTS/");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(selectedUri, "resource/folder");

                if (intent.resolveActivityInfo(getPackageManager(), 0) != null){
                    startActivity(intent);
                }
                else{
                    Log.d("Url : ",""+selectedUri.toString());
                    Toast.makeText(getApplicationContext(),"There is no folder!",Toast.LENGTH_SHORT).show();

                }

            }
        });
        alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
alertDialog=alertDialogBuilder.create();
        alertDialog.show();

    }
    private void dismissAlertDialog(){
        if(alertDialog!=null && alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body,String title,int resolution) {
        try{
            String picresolution=null;
            if(resolution==720){
                picresolution="_720p";
            }else if(resolution==1080){
                picresolution="_1080p";

            }
            File newFolder = new File(Environment.getExternalStorageDirectory(), "YTS");
            if (!newFolder.exists()) {
                newFolder.mkdir();
            }
            File torrentFile=new File(newFolder,File.separator+title+picresolution+".torrent");
            InputStream inputStream=null;
            OutputStream outputStream=null;
            try{
                byte[] fileReader=new byte[4096];
                long fileSize=body.contentLength();
                long filesSizeDownloaded=0;
                inputStream=body.byteStream();
                outputStream=new FileOutputStream(torrentFile);
                while (true){
                    int read=inputStream.read(fileReader);
                    if(read==-1){
                        break;
                    }
                    outputStream.write(fileReader,0,read);
                    filesSizeDownloaded+=read;
                    Log.d("TAG","file download: "+filesSizeDownloaded+" of "+fileSize);

                }
                outputStream.flush();
                return true;
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return false;
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
        dismissAlertDialog();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissDialog();
        dismissAlertDialog();
    }
    private void openImdb(String imdbI){
        if(imdbI!=null){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.imdb.com/title/"+imdbI));
            startActivity(browserIntent);
        }else{
            Toast.makeText(getApplicationContext(),"No pages in IMDB.",Toast.LENGTH_SHORT).show();
        }
    }
}
