package com.example.wangyinews.wangyinews.adapter;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 主页页面切换的适配器
 * Created by panshq on 2017/3/3.
 */
public class GuidePagerAdapter extends PagerAdapter {

    private static final String TAG = "GuidePagerAdapter";

    private List<View> views;

    public GuidePagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        Log.i(TAG,"-------isViewFromObject-----------");
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        Log.i(TAG,"-------getCount-----------");
        if(views==null){
            return 0;
        }
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        Log.i(TAG,"-------destroyItem-----------position = " + position);
        container.removeView(views.get(position));

    }

    @Override
    public int getItemPosition(Object object) {
        Log.i(TAG,"-------getItemPosition-----------");
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(TAG,"-------instantiateItem-----------position = " + position);
        View view = views.get(position);
        container.addView(view);
        return view;
    }
}
