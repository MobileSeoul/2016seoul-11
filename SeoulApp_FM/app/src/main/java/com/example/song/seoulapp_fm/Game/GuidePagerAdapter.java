package com.example.song.seoulapp_fm.Game;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.song.seoulapp_fm.R;

/**
 * Created by song on 2016-10-11.
 * AR Guide - viewpager adapter
 */
public class GuidePagerAdapter extends PagerAdapter {

    private Context context;
    private int[] guideImages;

    public GuidePagerAdapter(Context context) {
        this.context = context;
        guideImages = new int[]{
                R.drawable.ar_guide_1,
                R.drawable.ar_guide_2
        };
    }

    @Override
    public int getCount() {
        return guideImages.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(guideImages[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((ImageView) object);
    }
}