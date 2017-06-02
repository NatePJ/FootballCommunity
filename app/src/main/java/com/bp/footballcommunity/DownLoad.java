package com.bp.footballcommunity;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by BP on 2017/6/1.
 */
public class DownLoad {
    private static final String TAG = "DownLoad";
    private Handler mHandler;

    public DownLoad(Handler handler){
        this.mHandler = handler;
    }

    //创建线程池
    private Executor threadPool = Executors.newFixedThreadPool(3);

    static class DownLoadRunnable implements Runnable{

        private String fileName;
        private String url;
        private long start;
        private long end;
        private Handler mHandler;
        public DownLoadRunnable(String url,String fileName,long star,long end,Handler handler){
            this.fileName = fileName;
            this.url = url;
            this.start = star;
            this.end = end;
            this.mHandler = handler;
        }
        @Override
        public void run() {
            try{
                Log.d(TAG,"2.1");
                URL httpUrl = new URL(url);
                Log.d(TAG,"2.2");
                HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
                Log.d(TAG,"2.3");
                //超时
                conn.setReadTimeout(5000);
                Log.d(TAG,"2.4");
                //指定长度
                conn.setRequestProperty("Range","bytes="+start+"-"+end);
                Log.d(TAG,"2.5");
                conn.setRequestMethod("GET");
                Log.d(TAG,"2.6");
                //向本地文件编写
                File file = new File(fileName);
                Log.d(TAG,file.toString());
                RandomAccessFile access = new RandomAccessFile(file, "rwd");//可读写
                Log.d(TAG, "2.6.2");



                Log.d(TAG, "2.7");
                //指定区域读写
                access.seek(start);
                Log.d(TAG,"2.8");
                //读取数据
                //取到流
                InputStream in = conn.getInputStream();
                Log.d(TAG,"2.9");
                //缓冲区
                byte[] b=new byte[1024*4];
                Log.d(TAG,"2.10");
                //开始读取数据，不等于-1
                int len=0;
                while((len = in.read(b))!=-1){
                    Log.d(TAG,"2.11");
                    //从0开始读取len得擦汗给你都
                    access.write(b,0,len);
                }
                //关闭
                Log.d(TAG,"2.12");
                if(access!=null){
                    Log.d(TAG,"2.13");
                    access.close();
                    Log.d(TAG,"2.14");
                }
                if(in!=null){
                    Log.d(TAG,"2.15");
                    in.close();
                    Log.d(TAG,"2.16");
                }
                Log.d(TAG,"2");
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
                Log.d(TAG,"3");
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void downLoadFile(String url) throws IOException {
        try{
            Log.d(TAG,"4");
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            //超时
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            //下载图片长度
            int count = conn.getContentLength();
            //每一个线程下载总体长度三分之一
            /**
             * 11/3 = 3……2
             * 第一个线程 0-2
             * 第二个线程 3-5
             * 第三个线程 6-10
             */
            int block = count/3;
            //取到文件地址
            String fileName = getFileName(url);
            //id创建路径
            File parent = Environment.getExternalStorageDirectory();
            File fileDownLoad = new File(parent,fileName);
            //开启三个线程
            for(int i = 0; i < 3; i++){
                Log.d(TAG,"5");
                long start = i * block;
                long end = (i+1)*block-1;
                if(i==2){
                    end = count;
                }
                DownLoadRunnable runnable = new DownLoadRunnable(url,fileDownLoad.getAbsolutePath(),start,end,mHandler);
                threadPool.execute(runnable);
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //取出 url 后缀名
    public String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }
}