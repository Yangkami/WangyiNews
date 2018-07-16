package com.example.wangyinews.wangyinews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyinews.wangyinews.Bean.LiveBean;
import com.example.wangyinews.wangyinews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DEEP BLUE on 2018/3/30.
 */

public class TextImageAdapter extends BaseAdapter {
    private Context context;
    //private String[] text;
    //private int[] images;
    private List<LiveBean> list = new ArrayList<>();
    public TextImageAdapter(Context context, List<LiveBean> list){
        this.context=context;
        this.list=list;
    }

    public int getViewTypeCount() {
        return 2;
    }

    @Override

    /*public TextImageAdapter(Context context,String[] texts,int[] images){
            this.context=context;
            this.text=texts;
            this.images=images;
        }*/
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //Content con=list.get(i);
        int type=getItemViewType(i);
        ViewHolder1 viewHolder1=new ViewHolder1();

                view = LayoutInflater.from(context).inflate(R.layout.video_intent, null);
                viewHolder1.tv1 = view.findViewById(R.id.tv1);
                viewHolder1.im1 = view.findViewById(R.id.im1);

                LiveBean c = list.get(i);

                viewHolder1.tv1.setText(c.getTx());
                viewHolder1.im1.setImageDrawable(c.getView());

            /*textView.setText(text[i]);
            imageView.setImageResource(images[i]);*/

        return view;
    }

    class  ViewHolder1{
        public TextView tv1;
        public ImageView im1;
    }

}
