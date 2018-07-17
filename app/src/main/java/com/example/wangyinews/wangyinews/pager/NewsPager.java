package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.adapter.NewsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsPager extends BasePager implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RecyclerView rcvNews;
    //private ScrollView sclvNews;
   // private ImageView ivNews;
   // private ListView lvNews;
    private RadioGroup rgNews;
    private ViewPager vpNews;
    ListView listView;
    private int preCheckedId = -1;
    private int prePosition = -1;
    public NewsPager(Context context) {

        super(context);
    }

    @Override
    protected int loadLayoutById() {
        return R.layout.pager_news;
    }

    @Override
    public void initViews() {

        rgNews = mRootView.findViewById(R.id.rg_news_pager);
        vpNews=mRootView.findViewById(R.id.vp_news_pager);
        rgNews.setOnCheckedChangeListener(this);
        //sclvNews=mRootView.findViewById(R.id.sclv_news_pager);
        //ivNews=mRootView.findViewById(R.id.iv_news_pager);
        //lvNews=mRootView.findViewById(R.id.lv_news_pager);

        String [] tags = mContext.getResources().getStringArray(R.array.news_tags);
        List<BaseNewsPager> pagers=new ArrayList<>();
        RadioButton rbtn=null;
        LayoutInflater lf = LayoutInflater.from(mContext);
        final int count = tags.length;
        for (int i=0;i<count;i++){
            //tags.add("标签"+i);
            //Log.i(TAG,"---tags-------------"+tags[i]);
            rbtn = (RadioButton) lf.inflate(R.layout.rbtn_news_pager, null);
            rbtn.setText(tags[i]);
           // rbtn.setText("标签"+i);
            rbtn.setId(i);
            if (i == 0) {
                rbtn.setChecked(true);
            }
            rgNews.addView(rbtn);
            pagers.add(new BaseNewsPager(mContext));
        }
        NewsPagerAdapter mAdapter=new NewsPagerAdapter(pagers);
        vpNews.setAdapter(mAdapter);

    }

    @Override
    public void initData(Object data) {
       // tvCenter.setText("NewsPager");
        Log.i(TAG,"-----NewsPager----initData-------------");


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (preCheckedId == checkedId) {
            return;
        }

        Log.i(TAG, "--------onCheckedChanged--------checkedId = " + checkedId);
        preCheckedId = checkedId;
        int position = checkedId;
        if (position >= 0) {
            vpNews.setCurrentItem(checkedId);
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prePosition == position) {
            return;
        }

        Log.i(TAG, "--------onPageSelected--------position = " + position);
        prePosition = position;
        //pagers.get(position).initData(position);
        rgNews.check(rgNews.getChildAt(position).getId());

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }



}
