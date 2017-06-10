package com.bp.footballcommunity.utilities;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by BP on 2017/6/9.
 */
public class UploadUtility {

    private static final String TAG = "UploadUtility";

    /**
     * 上传足球时刻
     * @param phototPath
     * @param editMessage
     */
    public static void uploadFootballMessage(String phototPath,String editMessage){

        File file = new File(phototPath);
        String fileName = file.getName();
        Log.d(TAG, fileName);
        //封装待发送参数（以键值对形式），即靶文件，内容类型设置为multipart/form-data
        RequestParams params = new RequestParams();
        try {
            params.put("file", file, "multipart/form-data");
            params.put("footballMessageText",editMessage);
            params.put("filename",fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AsyncHttpClient uploader = new AsyncHttpClient();

        //这次是post请求，看清楚，多了第二个参数params，其实也就是被封装的靶文件啦
        //这次使用的响应体接收者是AsyncHttpResponseHandler，回调处理也简单，就是现实上传成功与否
        uploader.post(Constant.URLPicture, params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                        Log.d(TAG, "upload success");
                    }
                    @Override
                    public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                        Log.d(TAG, "upload failure");

                    }
                });
    }

    /**
     * 点赞
     * @param messageId
     */
    public static void uploadThumbsUp(String messageId,String thumbsUp){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("messageId", messageId);
        params.put("thumbsUp", thumbsUp);
        client.get(Constant.URLThumbsUp, params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }

    /**
     *
     * @param aboutPlayground
     * @param aboutTime
     * @param aboutMaxPeople
     */
    public static void uploadAboutMessage(String aboutPlayground,String aboutTime,String aboutMaxPeople){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("aboutPlayground", aboutPlayground);
        params.put("aboutTime", aboutTime);
        params.put("aboutMaxpeople", aboutMaxPeople);
        client.post(Constant.URLAboutMessage, params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }

    public static void joinAboutFootball(String messageId){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("messageId", messageId);
        client.get(Constant.URLJoinAbout, params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }
}
