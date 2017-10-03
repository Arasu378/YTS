package com.arasu.vt.yts.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.pojo.Movy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by kyros on 03-10-2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Movy>moviesList;
    public MovieListAdapter(Context mContext,ArrayList<Movy>moviesList){
        this.mContext=mContext;
        this.moviesList=moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView card_click;
        public ImageView image_movie;
        public TextView title_movie,year_title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title_movie=(TextView)itemView.findViewById(R.id.title_movie);
            year_title=(TextView)itemView.findViewById(R.id.year_title);
            image_movie=(ImageView)itemView.findViewById(R.id.image_movie);
            card_click=(CardView)itemView.findViewById(R.id.card_click);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
            Movy movy=moviesList.get(position);
        String pictureURL=movy.getMediumCoverImage();
        String title=movy.getTitle();
        double year=movy.getYear();
        String ye_ar=String.valueOf(year);
        ye_ar=ye_ar.substring(0, ye_ar.length() - 2);
        holder.year_title.setText(ye_ar);
        if(pictureURL!=null){
            Picasso.with(mContext).load(pictureURL).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    holder.image_movie.setBackground(new BitmapDrawable(bitmap) );


                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
        if(title!=null){
            holder.title_movie.setText(title);
        }

    }

    @Override
    public int getItemCount() {
        if(moviesList!=null &&moviesList.size()!=0){
            return moviesList.size();

        }else{
            return 0;

        }
    }
}
