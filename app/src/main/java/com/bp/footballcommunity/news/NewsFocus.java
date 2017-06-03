package com.bp.footballcommunity.news;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Objects;

/**
 * Created by BP on 2017/5/31.
 */
public class NewsFocus {
    private static final String TAG = "NewsFocus";
    private int headImageId = 0;
    private String userName = null;
    private String footballTimeText = null;
    private int footballTimeImageId = 0;
    private int newsType = 0;
    private Bitmap bitmap;

    public NewsFocus(int headImageId,String userName,String footballTimeText,int footballTimeImageId,Bitmap bitmap){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.footballTimeImageId = footballTimeImageId;
        this.bitmap = bitmap;
        Log.d(TAG,"1.1");
    }
    public NewsFocus(int headImageId,String userName,String footballTimeText,Bitmap bitmap){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.bitmap = bitmap;
        Log.d(TAG,"1.2");
    }
    public NewsFocus(int headImageId,String userName,int footballTimeImageId,Bitmap bitmap){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeImageId = footballTimeImageId;
        this.bitmap = bitmap;
        Log.d(TAG,"1.3");
    }

    /**
     * 不联网构造方法
     */

    public NewsFocus(int headImageId,String userName,String footballTimeText,int footballTimeImageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.footballTimeImageId = footballTimeImageId;
        Log.d(TAG,"1.1");
    }
    public NewsFocus(int headImageId,String userName,String footballTimeText){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        Log.d(TAG,"1.2");
    }
    public NewsFocus(int headImageId,String userName,int footballTimeImageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeImageId = footballTimeImageId;
        Log.d(TAG,"1.3");
    }
    /**
     * getter
     * @return
     */
    public int getHeadImageId() {
        return headImageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFootballTimeText() {
        return footballTimeText;
    }

    public int getFootballTimeImageId() {
        return footballTimeImageId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * setter
     * @param headImageId
     */
    public void setHeadImageId(int headImageId) {
        this.headImageId = headImageId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFootballTimeText(String footballTimeText) {
        this.footballTimeText = footballTimeText;
    }

    public void setFootballTimeImageId(int footballTimeImageId) {
        this.footballTimeImageId = footballTimeImageId;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    // 确定是什么类型
    public int getNewsType(){
        if((footballTimeText != null) && footballTimeImageId == 0){
            this.newsType = 1;
        }else if(footballTimeText==null&&footballTimeImageId!=0){
            this.newsType = 2;
        }else if(footballTimeText != null&&footballTimeImageId!=0){
            this.newsType = 3;
        }
        return newsType;
    }
}
