package com.arasu.vt.yts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.pojo.ParentalGuides;

import java.util.List;

/**
 * Created by kyros on 20-10-2017.
 */

public class ParentalAdapter extends RecyclerView.Adapter<ParentalAdapter.ParentalViewHolder> {
    private Context mContext;
    private List<ParentalGuides>parentalGuidesList;
    public ParentalAdapter(Context mContext,List<ParentalGuides>parentalGuidesList){
        this.mContext=mContext;
        this.parentalGuidesList=parentalGuidesList;
    }
    public class ParentalViewHolder extends RecyclerView.ViewHolder{
        TextView title_parental_guide,text_parental_guide;
        public ParentalViewHolder(View itemView) {
            super(itemView);
            text_parental_guide=(TextView)itemView.findViewById(R.id.text_parental_guide);
            title_parental_guide=(TextView)itemView.findViewById(R.id.title_parental_guide);
        }
    }
    @Override
    public ParentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_parental_item,parent,false);
        return new ParentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParentalViewHolder holder, int position) {
        ParentalGuides parentalGuides=parentalGuidesList.get(position);
        String type=parentalGuides.getType();
        String parental_guide_text=parentalGuides.getParental_guide_text();
        if(type!=null){
            holder.title_parental_guide.setText(type);
        }
        if(parental_guide_text!=null){
            holder.text_parental_guide.setText(parental_guide_text);
        }

    }

    @Override
    public int getItemCount() {
        if(parentalGuidesList!=null&&parentalGuidesList.size()!=0){
            return parentalGuidesList.size();
        }else{
            return 0;

        }
    }
}
