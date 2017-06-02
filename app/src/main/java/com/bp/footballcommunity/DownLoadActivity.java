package com.bp.footballcommunity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by BP on 2017/6/1.
 */
public class DownLoadActivity extends Activity{
    private static final String TAG = "DownLoadActivity";
    private Button mButton;
    private ImageView mTextView;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        mButton = (Button)findViewById(R.id.button_download);
        mTextView = (ImageView)findViewById(R.id.imagedpwnload);
        Log.d(TAG,"1.4");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"1.3");
                new Thread(){
                    @Override
                    public void run(){
                        DownLoad load = new DownLoad(handle);
                        try {
                            Log.d(TAG,"1.1");
                            load.downLoadFile("http://10.250.62.4:8080/SSHProject/pic/comment.png");
                            Log.d(TAG,"1.2");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });
    }

    private Handler handle = new Handler(){
        public void handleMessage(android.os.Message msg){
            int result = msg.what;
            count+=result;
            if(count==3){
//                mTextView.setText("download succes!");
            }
        }
    };
}
