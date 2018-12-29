package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.activity.GoldActivity;
import com.example.wangyinews.wangyinews.adapter.NewsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsWebPager extends BasePager implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private RecyclerView rcvNews;
    //private ScrollView sclvNews;
   // private ImageView ivNews;
   // private ListView lvNews;
    private RadioGroup rgNews;
    private ViewPager vpNews;
    ListView listView;
    private int preCheckedId = -1;
    private int prePosition = -1;
    public NewsWebPager(Context context) {
        super(context);

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.activity_webview;
    }

    @Override
    public void initViews() {

        WebView web=(WebView) mRootView.findViewById(R.id.web);
        web.loadUrl("https://toutiao.eastday.com/?qid=qid02650&referrer=");  //头条的一个网页新闻

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setUseWideViewPort(true);//关键点
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//全
        web.setWebViewClient(new WebViewClient(){
            public boolean shouldOverideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });

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
