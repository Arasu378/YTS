package com.arasu.vt.yts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.model.FragmentModel;
import com.arasu.vt.yts.pojo.Torrent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kyros on 04-10-2017.
 */

public class Fragment1080p extends Fragment {
    private LinearLayout subtitle_linear;
    private TextView size_1080p,pic_resolution,language_text,mpa_rating_text,fps,runtime_text,seeds_peers;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragmentthousand,container,false);
        seeds_peers=(TextView)view.findViewById(R.id.seeds_peers);
        runtime_text=(TextView)view.findViewById(R.id.runtime_text);
        fps=(TextView)view.findViewById(R.id.fps);
        mpa_rating_text=(TextView)view.findViewById(R.id.mpa_rating_text);
        language_text=(TextView)view.findViewById(R.id.language_text);
        pic_resolution=(TextView)view.findViewById(R.id.pic_resolution);
        size_1080p=(TextView)view.findViewById(R.id.size_1080p);
        subtitle_linear=(LinearLayout)view.findViewById(R.id.subtitle_linear);

        String list= FragmentModel.getHolder().getTorrentZlist();
        String language=FragmentModel.getHolder().getLanguage();
        if(language!=null){
            language_text.setText(language);
        }
        String mpa_rating=FragmentModel.getHolder().getMpa_rating();
        if(mpa_rating!=null){
            mpa_rating_text.setText(mpa_rating);
        }
        String  runtime=FragmentModel.getHolder().getRuntime();
        String Rtime=runtime+" minutes.";
        runtime_text.setText(Rtime);

        if(list!=null){
            Log.d("torrentz: ",""+list);
            Gson gson=new Gson();
            Type type=new TypeToken<List<Torrent>>(){}.getType();
            List<Torrent> torrentList=null;
            torrentList=gson.fromJson(list,type);
            Log.d("torrentz"," list size "+torrentList.size());
            if(torrentList!=null&&torrentList.size()!=0){
                if(torrentList.size()!=1){
                    String url=torrentList.get(1).getUrl();
                    double peers=torrentList.get(1).getPeers();
                    double seeds=torrentList.get(1).getSeeds();
                    String SEEDS=String.valueOf(seeds);
                    SEEDS=SEEDS.substring(0,SEEDS.length()-2);
                    String PEERS=String.valueOf(peers);
                    PEERS=PEERS.substring(0,PEERS.length()-2);
                    String seedspeer="S/P "+SEEDS+" / "+PEERS;
                    seeds_peers.setText(seedspeer);
                    String size=torrentList.get(1).getSize();
                    if(size!=null){
                        size_1080p.setText(size);
                    }
                    String quality=torrentList.get(1).getQuality();
                    if(quality!=null&&quality.equals("1080p")){
                        pic_resolution.setText("1920*1080");
                    }else{
                        if(quality!=null){
                            pic_resolution.setText(quality);

                        }
                    }
                    String hash=torrentList.get(1).getHash();
                }else{
                    Toast.makeText(getContext(),"No 1080 avaliable",Toast.LENGTH_SHORT).show();
                }

            }
            subtitle_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
        return view;
    }
}
