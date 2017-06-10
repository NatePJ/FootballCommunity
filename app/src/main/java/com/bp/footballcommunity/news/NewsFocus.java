package com.bp.footballcommunity.news;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Objects;

/**
 * Created by BP on 2017/5/31.
 */
public class NewsFocus {
    private static final String TAG = "NewsFocus";
    private String messageId = null;
    private Bitmap headImageId = null;
    private String userName = null;
    private String footballTimeText = null;
    private Bitmap footballTimeImageId = null;
    private int newsType = 0;
    private String thumbsUp;


    public NewsFocus(Bitmap headImageId,String userName,String footballTimeText,Bitmap footballTimeImageId,String thumbsUp,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.footballTimeImageId = footballTimeImageId;
        this.thumbsUp = thumbsUp;
        this.messageId = messageId;
        Log.d(TAG,"1.1");
    }
    public NewsFocus(Bitmap headImageId,String userName,String footballTimeText,String thumbsUp,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.thumbsUp = thumbsUp;
        this.messageId = messageId;
        Log.d(TAG,"1.2");
    }
    public NewsFocus(Bitmap headImageId,String userName,Bitmap footballTimeImageId,String thumbsUp,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeImageId = footballTimeImageId;
        this.thumbsUp = thumbsUp;
        this.messageId = messageId;
        Log.d(TAG,"1.3");
    }

    /**
     * 不联网构造方法
     */

    public NewsFocus(Bitmap headImageId,String userName,String footballTimeText,Bitmap footballTimeImageId,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.footballTimeImageId = footballTimeImageId;
        this.messageId = messageId;
        Log.d(TAG,"1.1");
    }
    public NewsFocus(Bitmap headImageId,String userName,String footballTimeText,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeText = footballTimeText;
        this.messageId = messageId;
        Log.d(TAG,"1.2");
    }
    public NewsFocus(Bitmap headImageId,String userName,Bitmap footballTimeImageId,String messageId){
        this.headImageId = headImageId;
        this.userName = userName;
        this.footballTimeImageId = footballTimeImageId;
        this.messageId = messageId;
        Log.d(TAG,"1.3");
    }



    /**
     * getter
     * @return
     */

    public String getMessageId() {
        return messageId;
    }

    public Bitmap getHeadImageId() {
        return headImageId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFootballTimeText() {
        return footballTimeText;
    }

    public Bitmap getFootballTimeImageId() {
        return footballTimeImageId;
    }

    public String getThumbsUp(){
        return thumbsUp;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * setter
     * @param headImageId
     */


    public void setHeadImageId(Bitmap headImageId) {
        this.headImageId = headImageId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFootballTimeText(String footballTimeText) {
        this.footballTimeText = footballTimeText;
    }

    public void setFootballTimeImageId(Bitmap footballTimeImageId) {
        this.footballTimeImageId = footballTimeImageId;
    }

    public void setThumbsUp(String bitmap) {
        this.thumbsUp = bitmap;
    }

    // 确定是什么类型
    public int getNewsType(){
        if((footballTimeText != null) && footballTimeImageId == null){
            this.newsType = 1;
        }else if(footballTimeText==null&&footballTimeImageId!=null){
            this.newsType = 2;
        }else if(footballTimeText != null&&footballTimeImageId!=null){
            this.newsType = 3;
        }
        return newsType;
    }
}
