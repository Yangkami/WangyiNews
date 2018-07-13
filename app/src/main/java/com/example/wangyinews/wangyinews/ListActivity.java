package com.example.wangyinews.wangyinews;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by silent night on 2018/7/13 0013.
 */

public class ListActivity extends AppCompatActivity {
    Context context=this;
    ListView listView;
    Handler handler;
    JsonAdapter jsonAdapter;
    String url="http://112.74.165.209:3020/selectAllNews";//url。
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_news_tag);

        listView= (ListView)findViewById(R.id.lv_news_pager);
        handler=new Handler();//获得一个handler对象，为后面的各个线程提供处理UI的依据
        new JsonThread(context, listView, url,handler).start();
    }
}