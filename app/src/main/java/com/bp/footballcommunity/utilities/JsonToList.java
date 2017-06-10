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
        Log.d("NoMessage","istosb");
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

    /**
     * 获取约球信息
     * @throws JSONException
     */

    //将InputStream格式转化为StringBuilder格式
    public static StringBuilder aboutisTosb(InputStream is){
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
    public static void aboutjsonTolist(String strJson)throws JSONException {
        JSONObject jsonObject = new JSONObject(strJson);
        JSONArray jsonUser = jsonObject.getJSONArray("Like");
        Log.d("Util: ","getJSONArray");
        for (int i = 0; i < jsonUser.length(); i++) {

            Constant.aboutId.add(i,jsonUser.getJSONObject(i).getString("aboutFootballId"));
            Log.d("aboutMessage:",Constant.aboutId.get(i).toString());
            Constant.aboutFootballUserName.add(i,jsonUser.getJSONObject(i).getString("footballUsername"));
            Log.d("aboutMessage:",Constant.aboutFootballUserName.get(i).toString());
            Constant.aboutCity.add(i,jsonUser.getJSONObject(i).getString("city"));
            Log.d("aboutMessage:",Constant.aboutCity.get(i).toString());
            Constant.aboutPlayground.add(i,jsonUser.getJSONObject(i).getString("playgroundName"));
            Log.d("aboutMessage:",Constant.aboutPlayground.get(i).toString());
            Constant.aboutCurrPeople.add(i,jsonUser.getJSONObject(i).getString("currPeople"));
            Log.d("aboutMessage:",Constant.aboutCurrPeople.get(i).toString());
            Constant.aboutMaxPeople.add(i,jsonUser.getJSONObject(i).getString("maxPeople"));
            Log.d("aboutMessage:",Constant.aboutMaxPeople.get(i).toString());
            Constant.aboutTime.add(i,jsonUser.getJSONObject(i).getString("aboutTime"));
            Log.d("aboutMessage:",Constant.aboutTime.get(i).toString());
            Constant.aboutUserHeadImage.add(i,jsonUser.getJSONObject(i).getString("footballUserheadImage"));
            Log.d("aboutMessage:",Constant.aboutUserHeadImage.get(i).toString());
            Constant.aboutIsJoin.add(i,jsonUser.getJSONObject(i).getString("isJoin"));
            Log.d("aboutMessage:",Constant.aboutIsJoin.get(i).toString());
        }
    }

    public static void deleteFocusArryList(){
        Constant.messageId.clear();
        Constant.footballUserName.clear();
        Constant.footballUserHeadImage.clear();
        Constant.messageText.clear();
        Constant.messageImage.clear();
        Constant.thumbsUp.clear();
        Constant.headBitmap.clear();
        Constant.bodyBitmap.clear();
    }

    public static void deleteAboutArrauList(){
        Constant.aboutId.clear();
        Constant.aboutFootballUserName.clear();
        Constant.aboutCity.clear();
        Constant.aboutPlayground.clear();
        Constant.aboutCurrPeople.clear();
        Constant.aboutMaxPeople.clear();
        Constant.aboutTime.clear();
        Constant.aboutUserHeadImage.clear();
        Constant.aboutIsJoin.clear();
        Constant.aboutHeadBitmap.clear();
    }
}
