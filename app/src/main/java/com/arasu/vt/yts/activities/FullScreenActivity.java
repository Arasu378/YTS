package com.arasu.vt.yts.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.yts.R;
import com.arasu.vt.yts.model.FragmentModel;
import com.arasu.vt.yts.pojo.Torrent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class FullScreenActivity extends AppCompatActivity {
    private TextView lbl_count;
    private ViewPager viewPager;
    private ArrayList<String > listPicture=null;
    private int selectedPosition=0;
    private MyViewPageAdapter myViewPageAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        lbl_count=(TextView)findViewById(R.id.lbl_count);
        String pictureList= FragmentModel.getHolder().getLargePictureList();
        if(pictureList!=null){
            Gson gson=new Gson();
            Type type=new TypeToken<ArrayList<String>>(){}.getType();
            listPicture=gson.fromJson(pictureList,type);
            myViewPageAdapter = new MyViewPageAdapter(FullScreenActivity.this);
            viewPager.setAdapter(myViewPageAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            setCurrentItem(selectedPosition);
            myViewPageAdapter.notifyDataSetChanged();
        }



    }
    private void showProgressDialog(){
            if(progressDialog==null){
                progressDialog=new ProgressDialog(FullScreenActivity.this);
                progressDialog.setMessage("please wait!...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
            }
            progressDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissProgressDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
    }

    private void dismissProgressDialog(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }

    }
    ViewPager.OnPageChangeListener viewPagerPageChangeListener=new ViewPager.SimpleOnPageChangeListener(){
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //  displayMetaInfo(position);
        }

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };
    public void setCurrentItem(int currentItem) {
        viewPager.setCurrentItem(currentItem,false);
        displayMetaInfo(selectedPosition);
    }
    private void displayMetaInfo(int selectedPosition) {
        lbl_count.setText((selectedPosition+1)+" of "+listPicture.size());

    }
    public class MyViewPageAdapter extends PagerAdapter{
        private LayoutInflater layoutInflater;
        private Context mContext;
        public MyViewPageAdapter(Context mContext){
            this.mContext=mContext;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
          layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.full_screen_item,container,false);
            final ImageViewTouch imgDisplay=(ImageViewTouch)view.findViewById(R.id.imgDisplay);
            String picUrl=listPicture.get(position);
            if(picUrl!=null){
                picUrl=picUrl.replace("yts.ag","yts.unblocked.re");
                showProgressDialog();
                Picasso.with(getApplicationContext()).load(picUrl).into(imgDisplay, new Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(mContext,"Success!",Toast.LENGTH_SHORT).show();
                       dismissProgressDialog();

                    }

                    @Override
                    public void onError() {
                        Toast.makeText(mContext,"failure!",Toast.LENGTH_SHORT).show();
                        dismissProgressDialog();

                    }
                });
                Log.d("URL","url picture : "+picUrl);
            }

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return listPicture.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==((View)object);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
