//package com.bp.footballcommunity.threads;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Message;
//import android.util.Log;
//
//import com.bp.footballcommunity.fragments.FocusFragment;
//import com.bp.footballcommunity.utilities.Constant;
//import com.bp.footballcommunity.utilities.JsonToList;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.io.InputStream;
//
///**
// * Created by BP on 2017/6/7.
// */
//public class FocusThread extends Thread{
//    private Message msg;
//    private Bundle bdl;
//    private Context mContext;
//
//    public FocusThread(){
//    }
//    public void run(){
//        InputStream resIS;
//        StringBuilder resSB;
//        String resStr;
//
//        resIS = getIS(Constant.strURL);
//        if (resIS == null){
//            return;
//        }else{
//            resSB = JsonToList.isTosb(resIS);
//            resStr = resSB.toString();
//            try{
//                JsonToList.jsonTolist(resStr);
//                sendMessage(Constant.msg_connect,"");
//            }catch (Exception e){
//                e.printStackTrace();
//                sendMessage(Constant.msg_error,e.toString());
//            }
//        }
//    }
//
//    private InputStream getIS(String url) {
//        InputStream is = null;
//        try{
//            //取得默认HttpClien实例
//            HttpClient hc = new DefaultHttpClient();
//            //连接到服务器
//            HttpResponse hr = hc.execute(new HttpGet(url));
//            //获取数据内容
//            is = hr.getEntity().getContent();
//        }catch(Exception e){
//            e.printStackTrace();
//            sendMessage(Constant.msg_error,e.toString());
//        }
//        Log.d("testconn:","2.8");
//        return is;
//    }
//    //发送消息用于更新UI
//    public void sendMessage(int x, String s){
//        msg = new Message();
//        bdl = new Bundle();
//        bdl.putString("1",s);
//        msg.what = x;
//        msg.setData(bdl);
//        messageHandler.sendMessage(msg);
//
//    }
//}
