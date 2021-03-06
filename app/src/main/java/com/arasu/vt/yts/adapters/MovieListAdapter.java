package com.arasu.vt.yts.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.activities.MovieDetailsActivity;
import com.arasu.vt.yts.clients.ApiClient;
import com.arasu.vt.yts.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kyros on 03-10-2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private Context mContext;
    private List<Movie> moviesList;
    private  int visibleThreshold=5;
    private int lastVisibleItem,totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListerner;
    public MovieListAdapter(Context mContext,List<Movie>moviesList,RecyclerView recyclerView){
        this.mContext=mContext;
        this.moviesList=moviesList;
       if(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager){
           final LinearLayoutManager staggeredGridLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
           recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
               @Override
               public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                   super.onScrolled(recyclerView, dx, dy);
                   totalItemCount=staggeredGridLayoutManager.getItemCount();
                   lastVisibleItem=staggeredGridLayoutManager.findLastVisibleItemPosition();
                   if(!loading&&totalItemCount<=(lastVisibleItem+visibleThreshold)){
                       if(onLoadMoreListerner!=null){
                           onLoadMoreListerner.onLoadMore();
                       }
                       loading=true;
                   }
               }
           });
       }

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
            final Movie movy=moviesList.get(position);
        String pictureURL=movy.getMediumCoverImage();
        String title=movy.getTitle();
        double year=movy.getYear();
        String ye_ar=String.valueOf(year);
        ye_ar=ye_ar.substring(0, ye_ar.length() - 2);
        holder.year_title.setText(ye_ar);
        final double movieId=movy.getId();

        if(pictureURL!=null){
            pictureURL=pictureURL.replace("https://yts.am", ApiClient.CONSTANT_IMAGE_URL);
            Picasso.get().load(pictureURL).into(holder.image_movie);
        }
        if(title!=null){
            holder.title_movie.setText(title);
        }
        holder.card_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("movieid",movieId);
                mContext.startActivity(intent);
            }
        });

    }

   public void setLoad(){
       loading=false;
   }
   public void setOnLoadMoreListerner(OnLoadMoreListener onLoadMoreListerner){
       this.onLoadMoreListerner=onLoadMoreListerner;
   }
   public interface OnLoadMoreListener{
        void  onLoadMore();
    }
    public void setLoaded(){
        loading=false;
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
