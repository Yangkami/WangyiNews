package com.example.wangyinews.wangyinews.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.wangyinews.wangyinews.R;
import com.example.wangyinews.wangyinews.pager.BasePager;
import com.example.wangyinews.wangyinews.pager.MinePager;
import com.example.wangyinews.wangyinews.adapter.MainPagerAdapter;
import com.example.wangyinews.wangyinews.pager.LivePager;
import com.example.wangyinews.wangyinews.pager.NewsPager;
import com.example.wangyinews.wangyinews.pager.VideoPager;
import com.example.wangyinews.wangyinews.video.fragments.VideoRecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private  static final String TAG="";
    private ViewPager vpMain;
    private RadioGroup rgMain;

//    Context context=this;
//    ListView listView;
//    Handler handler;
//    String url="http://112.74.165.209:3020/selectAllNews";//url。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"-----");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpMain=findViewById(R.id.vp_main);
        rgMain=findViewById(R.id.rg_main);

        rgMain.setOnCheckedChangeListener(this);
        vpMain.setOnPageChangeListener(this);



        //rgMain.setOnCheckedChangeListener(this);
//        listView= (ListView)findViewById(R.id.lv_news_pager);
//        handler=new Handler();//获得一个handler对象，为后面的各个线程提供处理UI的依据
//        new JsonThread(context, listView, url,handler).start();

        initData();

    }
    private void initData(){
        List<BasePager> pagers=new ArrayList<>();
        pagers.add(new NewsPager(this));
        pagers.add(new VideoPager(this));
        pagers.add(new LivePager(this));
        pagers.add(new MinePager(this));

        //适配器
        MainPagerAdapter mAdapter = new MainPagerAdapter(pagers);
        //配置适配器

        vpMain.setAdapter(mAdapter);

        pagers.get(0).initData(null);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int index = -1;
        switch (checkedId){
            case R.id.btn_news:
                index =0;
                break;
            case R.id.btn_video:
                index =1;
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, new VideoRecyclerViewFragment())
                        .commit();
                break;
            case R.id.btn_live:
                index =2;
                break;

            case R.id.btn_mine:
                index =3;
                break;
        }
        Log.i(TAG,"---index=" +index);
        if(index>=0){
            vpMain.setCurrentItem(index);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.w(TAG,"----------onPageScrolled"+position);

    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG,"----------posotoion"+position);
        int resid=rgMain.getChildAt(position).getId();
        rgMain.check(resid);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
