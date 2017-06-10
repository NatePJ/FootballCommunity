package com.bp.footballcommunity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by BP on 2017/6/8.
 */
public class UpLoadActivity extends Activity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        try {
            upload();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     */
    public void upload() throws FileNotFoundException {

        String url = "http://192.168.137.1:8080/SSHProject/file.png";
        Log.d("downloadpicfile","1");
        AsyncHttpClient client = new AsyncHttpClient();
        Log.d("downloadpicfile","2");
        client.get(url, new FileAsyncHttpResponseHandler(this) {
            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                try {//此try-catch块中的代码将下载到的文件保存在一个目录下
                    // Context.getFilesDir()，该方法返回/data/data/[youPackageName]/files的File对象
                    FileOutputStream fileOutputStream = new FileOutputStream(
                            UpLoadActivity.this.getFilesDir().getPath() + "/file.png");
                    Log.d("downloadpicfile",UpLoadActivity.this.getFilesDir().getPath() + "/file.png");
                    FileInputStream fileInputStream = new FileInputStream(response);
                    byte[] bytes = new byte[(int) response.length()];
                    fileInputStream.read(bytes);
                    fileOutputStream.write(bytes);
                    Log.d("downloadpicfile",UpLoadActivity.this.getFilesDir().getPath() + "/file.png");
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                } catch (Exception e) {
                    Log.d("downloadpicfile","fail");
                    e.printStackTrace();
                }

                /*********do something ...... ********/
                /***************上传部分***************/
                /*********do something ...... ********/
                /*上传部分*/
                //找到上传的靶文件（此时的靶文件应该是我们刚刚下载到的文件）
//                File file = new File(UpLoadActivity.this.getFilesDir().getPath(),"file.png");
                File file = new File(UpLoadActivity.this.getFilesDir().getPath(),"file.png");
                Log.d("downloadpicfile",UpLoadActivity.this.getFilesDir().getPath());
                //封装待发送参数（以键值对形式），即靶文件，内容类型设置为multipart/form-data
                RequestParams params = new RequestParams();
                try {
                    params.put("file", file, "multipart/form-data");
                    params.put("filename","file2.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                //没错，又是它
                AsyncHttpClient uploader = new AsyncHttpClient();

                //这次是post请求，看清楚，多了第二个参数params，其实也就是被封装的靶文件啦
                //这次使用的响应体接收者是AsyncHttpResponseHandler，回调处理也简单，就是现实上传成功与否
                Log.d("uploadpicfile", "upload test");
                uploader.post("http://192.168.137.1:8080/SSHProject/uploadpicture", params,
                        new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                                Log.d("uploadpicfile", "upload success");

                            }
                            @Override
                            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                                Log.d("uploadpicfile", "upload failure");

                            }
                        });

            }
            //下载失败时回调onFailure方法
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Log.i("file", "failure" + statusCode);
            }
        });


































//        RequestParams params = new RequestParams();
//        File f = new File("/storage/emulated/0/Pictures/add_picture.png");
//        if (f.exists() && f.length() > 0) {
//            Log.d("upload", String.valueOf(f));
//            params.put("neirong", f);
//            params.put("picpic", "liucanwen");
//        }
//        client.get(url, params, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//
//            }
//        });

    }

}
