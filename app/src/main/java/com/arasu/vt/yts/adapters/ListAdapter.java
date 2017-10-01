package com.arasu.vt.yts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arasu.vt.yts.R;

import java.util.List;

/**
 * Created by Thirunavukkarasu on 01-10-2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private Context mContext;
    private List<String>movieList;

    public ListAdapter(Context mContext, List<String >movieList){
     this.movieList=movieList;
        this.mContext=mContext;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView text_title;
        public ImageView picture_movie;
        public MyViewHolder(View itemView) {
            super(itemView);
            text_title=(TextView)itemView.findViewById(R.id.text_title);
            picture_movie=(ImageView)itemView.findViewById(R.id.picture_movie);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
