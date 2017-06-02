//package com.bp.footballcommunity.threads;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//
//import com.bp.footballcommunity.activities.ParentActivity;
//import com.bp.footballcommunity.fragments.FocusFragment;
//import com.bp.footballcommunity.news.NewsBitmap;
//import com.bp.footballcommunity.utilities.Constant;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by BP on 2017/6/3.
// */
//public class GetPictureThread extends Thread{
//    /**
//     * 消息变量
//     */
//    private Message msg;
//    private Bundle bdl;
//    private Context mContext;
//    private NewsBitmap mNewsBitmap = new NewsBitmap();
//
//    /**
//     * 构造方法
//     */
//    public GetPictureThread(Context context){
//        this.mContext = context;
//    }
//
//    /**
//     * run方法
//     */
//    public void run(){
//        mNewsBitmap.setBitmap(getBitmapFromServer("http://10.249.33.137:8080/SSHProject/pic/like_be_touch.png"));
//        sendMessage(Constant.msg_connect,"");
//    }
//
//    public static Bitmap getBitmapFromServer(String imagePath) {
//        Log.d("testconn:","21");
//        HttpGet get = new HttpGet(imagePath);
//        HttpClient client = new DefaultHttpClient();
//        Bitmap pic = null;
//        Log.d("testconn:","22");
//        try {
//            HttpResponse response = client.execute(get);
//            HttpEntity entity = response.getEntity();
//            Log.d("testconn:","23");
//            InputStream is = entity.getContent();
//
//            pic = BitmapFactory.decodeStream(is);   // 关键是这句代码
//            Log.d("testconn:","24");
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Log.d("testconn:","25");
//        return pic;
//    }
//
//    //发送消息用于更新UI
//    public void sendMessage(int x, String s){
//        msg = new Message();
//        bdl = new Bundle();
//        bdl.putString("1",s);
//        msg.what = x;
//        msg.setData(bdl);
//        .sendMessage(msg);
//    }
//}
