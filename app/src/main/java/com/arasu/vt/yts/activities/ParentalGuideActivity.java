package com.arasu.vt.yts.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.ParentalAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Data;
import com.arasu.vt.yts.pojo.ParentalGuides;
import com.arasu.vt.yts.pojo.ParentalResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kyros on 20-10-2017.
 */

public class ParentalGuideActivity extends AppCompatActivity {
    private double movieId;
    private RecyclerView recycler_parental;
    private ParentalAdapter adapter;
    private List<ParentalGuides>parentalGuidesList=new ArrayList<ParentalGuides>();
    private ProgressDialog progressDialog;
    private String movieTitle=null;
    private TextView title_movie_parental;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parental_layout);
        recycler_parental=(RecyclerView)findViewById(R.id.recycler_parental);
        title_movie_parental=(TextView)findViewById(R.id.title_movie_parental);
        try{
            Bundle bundle=getIntent().getExtras();
            movieId=bundle.getDouble("movieid");
            movieTitle=bundle.getString("title");
            if(movieTitle!=null){
                title_movie_parental.setText(movieTitle);
            }else{
                title_movie_parental.setText("Parental Guide");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(movieId!=0){
            callParentalApi();
        }else{
            Toast.makeText(getApplicationContext(),"Movie id is empty!",Toast.LENGTH_SHORT).show();
        }

    }

    private void callParentalApi() {
        showDialog();
        POJOInterface apiService= ApiClient.getRetrofit().create(POJOInterface.class);
        String mId=String.valueOf(movieId);
        mId=mId.replace(".0","");
        Log.d("movie Id ",""+mId);
        Call<ParentalResponse>call=apiService.getParentalDetails(mId);
        call.enqueue(new Callback<ParentalResponse>() {
            @Override
            public void onResponse(Call<ParentalResponse> call, Response<ParentalResponse> response) {
                dismissDialog();
                Log.d("response: ",""+response.body().toString());
                String status=response.body().getStatus();
                String status_message=response.body().getStatusMessage();
                if(status.equals("ok")){
                     int  parental_guide_count=response.body().getData().getParental_guide_count();
                    if(parental_guide_count!=0){
                        parentalGuidesList=response.body().getData().getParental_guides();
                        adapter=new ParentalAdapter(ParentalGuideActivity.this,parentalGuidesList);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                        recycler_parental.setLayoutManager(layoutManager);
                        recycler_parental.setItemAnimator(new DefaultItemAnimator());
                        recycler_parental.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getApplicationContext(),"No parental guide found!",Toast.LENGTH_SHORT).show();

                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Status: "+status_message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParentalResponse> call, Throwable t) {
                dismissDialog();

                if(t!=null){
                    String message=t.getMessage();
                    Toast.makeText(getApplicationContext(),"Error : "+message,Toast.LENGTH_SHORT).show();
                    Log.e("Error : ",""+t.getMessage());
                }
            }
        });
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }


    }
    private void showDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(ParentalGuideActivity.this);
            progressDialog.setMessage("Please Wait!!");
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(true);

        }
        progressDialog.show();
    }
    private void dismissDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
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
}
