package com.arasu.vt.yts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.pojo.Cast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kyros on 04-10-2017.
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private Context mContext;
    private ArrayList<Cast>castList;
    public CastAdapter(Context mContext, ArrayList<Cast>castList){
        this.mContext=mContext;
        this.castList=castList;
    }
    public class CastViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView cast_image;
        public TextView name_cast;
        public LinearLayout linear_click;

        public CastViewHolder(View itemView) {
            super(itemView);
            name_cast=(TextView)itemView.findViewById(R.id.name_cast);
            cast_image=(CircleImageView)itemView.findViewById(R.id.cast_image);
            linear_click=(LinearLayout)itemView.findViewById(R.id.linear_click);
        }
    }
    @Override
    public CastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cast_item,parent,false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        Cast cast=castList.get(position);
        String name=cast.getName();
        String character_name=cast.getCharacterName();
        String image=cast.getUrlSmallImage();
        final String imdb_code=cast.getImdbCode();
        String nameText=name+" as "+character_name;
        if(nameText!=null){
            holder.name_cast.setText(nameText);
        }
        if(image!=null){
            Picasso.with(mContext).load(image).into(holder.cast_image);
        }
        holder.linear_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),"IMDB : "+imdb_code,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(castList!=null){
            return castList.size();
        }else{
            return 0;

        }
    }
}
