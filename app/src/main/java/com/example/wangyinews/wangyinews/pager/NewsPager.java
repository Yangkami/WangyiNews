package com.example.wangyinews.wangyinews.pager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wangyinews.wangyinews.CoreApplication;
import com.example.wangyinews.wangyinews.JsonAdapter;
import com.example.wangyinews.wangyinews.JsonThread;
import com.example.wangyinews.wangyinews.ListActivity;
import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.activity.GoldActivity;
import com.example.wangyinews.wangyinews.adapter.NewsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsPager extends BasePager implements RadioGroup.OnCheckedChangeListener {
    private RecyclerView rcvNews;
    //private ScrollView sclvNews;
   // private ImageView ivNews;
   // private ListView lvNews;
    private RadioGroup rgNews;
    private ViewPager vpNews;
    ListView listView;

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
        for (int i=0;i<=count;i++){
            //tags.add("标签"+i);
            rbtn = (RadioButton) lf.inflate(R.layout.rbtn_news_pager, null);
            //rbtn.setText(tags[i]);
            rbtn.setText("标签"+i);
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

        //handler=new Handler();//获得一个handler对象，为后面的各个线程提供处理UI的依据
        Log.i(TAG, "-----initData-----------------listView = "+ listView+", mContext = "+ mContext);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.i(TAG, "--------onCheckedChanged--------index = " + checkedId);
        int position = checkedId;
        if (position >= 0) {
            vpNews.setCurrentItem(checkedId);
        }
    }


    public class ItemSpace extends RecyclerView.ItemDecoration{
        private int space;
        private int space2;

        public ItemSpace(int space,int space2){

            this.space = space;
            this.space2 = space2;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = space;
            outRect.top = space2;

        }
    }

}
