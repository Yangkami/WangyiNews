package com.example.wangyinews.wangyinews;

import android.graphics.drawable.Drawable;

public class NewsContent {
    private Drawable view;
    private  String tx;

    public NewsContent(String tx, Drawable view) {
        this.tx = tx;
        this.view = view;
    }


    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public Drawable getView() {
        return view;
    }

    public void setView(Drawable view) {
        this.view = view;
    }


}
