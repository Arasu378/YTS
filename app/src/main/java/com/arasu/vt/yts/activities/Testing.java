package com.arasu.vt.yts.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.adapters.MovieListAdapter;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.interfaces.POJOInterface;
import com.arasu.vt.yts.pojo.Movy;
import com.arasu.vt.yts.pojo.RootObject;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
    }


}
