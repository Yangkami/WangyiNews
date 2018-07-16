package com.example.wangyinews.wangyinews.adapter;

/**
 * Created by silent night on 2018/7/13 0013.
 */
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.wangyinews.wangyinews.Bean.NewsBean;

import static android.content.ContentValues.TAG;

/**
 * Created by CAPTON on 2016/11/25.
 */
//访问目标网址，得到json数据，保存List<NewsBean>数据，等待传入JsonAdapter
public class JsonThread extends Thread {
    Context context;
    //Context context;
    ListView listView;
    String url;
    Handler handler;//关键参数 整个小项目中的核心之一，会在JsonThread和JsonAdapter，ImageThread中传递，用于更新UI界面
    List<NewsBean> newsBeans;
    JsonAdapter jsonAdapter;

    public JsonThread(Context context, ListView listView, String url, Handler handler ) {
        this.context=context;
        this.listView=listView;
        this.url=url;
        this.handler=handler;
    }
    //从String中解析所需数据，如name，age，url，将他们装入Student中，再将Student逐条加入List<NewsBean>中
    private List<NewsBean> getStudents(String data){
        Log.i(TAG, "-------getStudents----------- "+data);
        List<NewsBean> newsBeans =new ArrayList<NewsBean>();
        try {
           // JSONObject object=new JSONObject(data);

            JSONArray jsonArray = new JSONArray(data);
            //if(object.getInt("data")==1){
                //JSONArray jsonArray=object.getJSONArray("newsBeans");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject studentObject= (JSONObject) jsonArray.get(i);
                    NewsBean new1 =new NewsBean();
                    new1.image_id=studentObject.getString("image_id");
                    //System.out.println(new1.image_id);
                    new1.news_id=studentObject.getString("news_id");
                    //System.out.println(new1.news_id);
                    new1.image_path=studentObject.getString("image_path");
                   // System.out.println(new1.image_path);

                    new1.news_kind=studentObject.getString("news_kind");
                    new1.news_title=studentObject.getString("news_title");
                    new1.user_id=studentObject.getString("user_id");
                    new1.good_num=studentObject.getString("good_num");
                    new1.see_num=studentObject.getString("see_num");
                    new1.comment_num=studentObject.getString("comment_num");
                    new1.news_submit_time=studentObject.getString("news_submit_time");
                    new1.news_content=studentObject.getString("news_content");

                    newsBeans.add(new1);
                }
           // }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsBeans;
    }

    @Override
    public void run() {

        //从网络中获取数据，转换为String类型
        StringBuffer result=new StringBuffer();
        try {
            URL Url=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) Url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            InputStream inputStream=connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=bufferedReader.readLine())!=null){
                result.append(line);
            }
            System.out.println(result);
            newsBeans =getStudents(result.toString());//调用解析方法
            inputStream.close();
            bufferedReader.close();

            handler.post(new Runnable() {
                @Override
                public void run() {

                    jsonAdapter=new JsonAdapter(context,handler, newsBeans);

                    //传递关键参数Newspager上下文对象context，Newspager主线程的handler对象,处理好的List<NewsBean>对象
                    listView.setAdapter(jsonAdapter);
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}