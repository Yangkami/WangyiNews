package com.example.wangyinews.wangyinews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {
    public List<NewsContent> list=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            list.add(new NewsContent("俄罗斯世界杯", getResources().getDrawable(R.drawable.new1)));
            list.add(new NewsContent("今日热点", getResources().getDrawable(R.drawable.new2)));
            list.add(new NewsContent("热点", getResources().getDrawable(R.drawable.new3)));
            list.add(new NewsContent("网易新闻", getResources().getDrawable(R.drawable.new4)));
            list.add(new NewsContent("新闻", getResources().getDrawable(R.drawable.new5)));
        setContentView(R.layout.video_intent_list);
        listView= findViewById(R.id.list);
        TextImageAdapter textImageAdapter = new TextImageAdapter(this,list);
        listView.setAdapter(textImageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"跳转至直播间！",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(VideoListActivity.this,LocalVideoActivity.class);//跳转
                startActivity(intent);
            }
        });
    }
}
