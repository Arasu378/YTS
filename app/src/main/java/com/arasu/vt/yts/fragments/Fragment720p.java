package com.arasu.vt.yts.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arasu.vt.yts.R;

/**
 * Created by kyros on 04-10-2017.
 */

public class Fragment720p  extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmentseven,container,false);
        return view;
    }
}
