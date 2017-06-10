package com.bp.footballcommunity.utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by BP on 2017/6/7.
 */
public class JsonToList {
    //构造函数
    public JsonToList(){

    }

    //将InputStream格式转化为StringBuilder格式
    public static StringBuilder isTosb(InputStream is){
        String ln = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try{
            while((ln=br.readLine()) != null){
                sb.append(ln);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sb;
    }

    //JSON转换成List
    public static void jsonTolist(String strJson)throws JSONException {
        JSONObject jsonObject = new JSONObject(strJson);
        JSONArray jsonUser = jsonObject.getJSONArray("Like");
        Log.d("Util: ","getJSONArray");
        for (int i = 0; i < jsonUser.length(); i++) {

            Constant.messageId.add(i,jsonUser.getJSONObject(i).getString("messageId"));
            Constant.footballUserName.add(i,jsonUser.getJSONObject(i).getString("footballUserName"));
            Log.d("MainActivityConn:",Constant.footballUserName.get(i).toString());
            Constant.footballUserHeadImage.add(i,jsonUser.getJSONObject(i).getString("footballUserHeadImage"));
            Log.d("MainActivityConn:",Constant.footballUserHeadImage.get(i).toString());
            Constant.messageText.add(i,jsonUser.getJSONObject(i).getString("messageText"));
            Log.d("MainActivityConn:",Constant.messageText.get(i).toString());
            Constant.messageImage.add(i,jsonUser.getJSONObject(i).getString("imgStorageAddress"));
            Log.d("MainActivityConn:",Constant.messageImage.get(i).toString());
            Constant.thumbsUp.add(i,jsonUser.getJSONObject(i).getString("thumbsUp"));
            Log.d("MainActivityConn:",Constant.thumbsUp.get(i).toString());
        }
    }
}
