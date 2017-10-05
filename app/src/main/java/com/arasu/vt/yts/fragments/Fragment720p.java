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

public class Fragment720p  extends Fragment{
    private LinearLayout subtitle_720p;
    private TextView filesize,resolution_720p,language_720p,mpa_rating_720p,fps720,runtime_720p,seedsandpeers;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentseven,container,false);
        seedsandpeers=(TextView)view.findViewById(R.id.seedsandpeers);
        runtime_720p=(TextView)view.findViewById(R.id.runtime_720p);
        fps720=(TextView)view.findViewById(R.id.fps720);
        mpa_rating_720p=(TextView)view.findViewById(R.id.mpa_rating_720p);
        language_720p=(TextView)view.findViewById(R.id.language_720p);
        resolution_720p=(TextView)view.findViewById(R.id.resolution_720p);
        filesize=(TextView)view.findViewById(R.id.filesize);
        subtitle_720p=(LinearLayout)view.findViewById(R.id.subtitle_720p);
        String list= FragmentModel.getHolder().getTorrentZlist();
        String language=FragmentModel.getHolder().getLanguage();
        if(language!=null){
            language_720p.setText(language);
        }
        String mpa_rating=FragmentModel.getHolder().getMpa_rating();
        if(mpa_rating!=null){
            mpa_rating_720p.setText(mpa_rating);
        }
        int  runtime=FragmentModel.getHolder().getRuntime();
        String Rtime=runtime+" minutes.";
        runtime_720p.setText(Rtime);

        if(list!=null){
            Log.d("torrentz: ",""+list);
            Gson gson=new Gson();
            Type type=new TypeToken<List<Torrent>>(){}.getType();
            List<Torrent> torrentList=null;
            torrentList=gson.fromJson(list,type);
            Log.d("torrentz"," list size "+torrentList.size());
            if(torrentList!=null&&torrentList.size()!=0){
                String url=torrentList.get(0).getUrl();
                double peers=torrentList.get(0).getPeers();
                double seeds=torrentList.get(0).getSeeds();
                String SEEDS=String.valueOf(seeds);
                SEEDS=SEEDS.substring(0,SEEDS.length()-2);
                String PEERS=String.valueOf(peers);
                PEERS=PEERS.substring(0,PEERS.length()-2);
                String seedspeer="S/P "+SEEDS+" / "+PEERS;
                seedsandpeers.setText(seedspeer);
                String size=torrentList.get(0).getSize();
                if(size!=null){
                    filesize.setText(size);
                }
                String quality=torrentList.get(0).getQuality();
                if(quality!=null&&quality.equals("720p")){
                    resolution_720p.setText("1280*720");
                }else{
                    if(quality!=null){
                        resolution_720p.setText(quality);

                    }
                }
                String hash=torrentList.get(0).getHash();
            }
        }
        subtitle_720p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}
