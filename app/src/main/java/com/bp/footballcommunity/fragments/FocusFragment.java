package com.bp.footballcommunity.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bp.footballcommunity.R;
import com.bp.footballcommunity.adapters.FocusAdapter;
import com.bp.footballcommunity.items.ItemFocus;
import com.bp.footballcommunity.news.NewsBitmap;
import com.bp.footballcommunity.news.NewsFocus;
//import com.bp.footballcommunity.threads.GetPictureThread;
//import com.bp.footballcommunity.threads.FocusThread;
import com.bp.footballcommunity.utilities.Constant;
import com.bp.footballcommunity.utilities.JsonToList;
import com.bp.footballcommunity.utilities.NewsToItemFocus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by BP on 2017/6/2.
 */
public class FocusFragment extends Fragment {
    private static final String TAG = "FocusFragment";
    public static final String ARG_PAGE = "ARG_PAGE";

    private View v = null;// 缓存Fragment view
    private int mPage;
    public int test = 1;
    //控件是否已经初始化
    private boolean isCreateView = false;
    //是否已经加载过数据
    private boolean isLoadData = false;

    private static GetPictureThread mGetPictureThread;
//    public Handler mainHandler;
    public FocusThread mFocusThread;
    /**
     * 定义
     */
    private static RecyclerView mRecyclerView;
    private static ArrayList<ItemFocus> mItemFocuses;
    private static ArrayList<NewsFocus> mNewsFocuses = new ArrayList<NewsFocus>();

    /**
     * pageView实例化
     * @return
     */
    public static FocusFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FocusFragment focusFragment = new FocusFragment();
        focusFragment.setArguments(args);
        return focusFragment;
    }
    public FocusFragment(){

    }

    /**
     * 界面初始化
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 通过该方法生成了该视图的布局
     * 返回布局给activity
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        if(v==null) {
            v = inflater.inflate(R.layout.fragment_focus, parent, false);
            /**
             * 实例化组件
             */
            mRecyclerView = (RecyclerView) v.findViewById(R.id.focus_recyclerview);
            isCreateView = true;
            /**
             * 开启线程
             */
//            mGetPictureThread = new GetPictureThread(getActivity());
//            mGetPictureThread.start();
            mFocusThread = new FocusThread();
            mFocusThread.start();

//            mNewsFocuses.add(new NewsFocus(R.drawable.headimage3, "李知恩", "아름다운 리 사은", R.drawable.body_focus_image_test));
//            mNewsFocuses.add(new NewsFocus(R.drawable.headimage4, "最速", R.drawable.body_focus_image_test2));
//            mNewsFocuses.add(new NewsFocus(R.drawable.headimage5, "hello", "yes we are"));
            /**
             * 初始化列表
             */
            mItemFocuses = NewsToItemFocus.newsToItemFocus(mNewsFocuses);
            /**
             * 设置适配器
             */
            FocusAdapter focusAdapter = new FocusAdapter(getActivity(), mItemFocuses);
            mRecyclerView.setAdapter(focusAdapter);

            /**
             * 布局管理器
             */
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            layoutManager.scrollToPosition(0);
            mRecyclerView.setLayoutManager(layoutManager);
        }
        if(parent != null){
            parent.removeView(v);
        }
        return v;
    }

    public Handler messageHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.msg_connect:
                    Log.d("MainActivityConn:","msg_success");
                    int i = 0;
                    mGetPictureThread = new GetPictureThread();
                    mGetPictureThread.start();
                    break;
                case Constant.msg_error:
                    Log.d("MainActivityConn:","msg_error");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    public Handler picHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constant.msg_connect:
                    /**
                     * 初始化列表
                     */
                    for(int i = 0;i < Constant.headBitmap.size();i++){
                        mNewsFocuses.add(new NewsFocus(
                                Constant.headBitmap.get(i),
                                Constant.footballUserName.get(i),
                                Constant.messageText.get(i),
                                Constant.bodyBitmap.get(i),
                                Constant.thumbsUp.get(i)));
                    }
                    mItemFocuses = NewsToItemFocus.newsToItemFocus(mNewsFocuses);
                    /**
                     * 设置适配器
                     */
                    FocusAdapter focusAdapter = new FocusAdapter(getActivity(), mItemFocuses);
                    mRecyclerView.setAdapter(focusAdapter);

                    /**
                     * 布局管理器
                     */
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    layoutManager.scrollToPosition(0);
                    mRecyclerView.setLayoutManager(layoutManager);
                    Log.d("MainActivityConn:","pic_success");
                    break;
                case  Constant.msg_error:
                    Log.d("MainActivityConn:","pic_error");
                    break;
            }
            super.handleMessage(msg);
        }

    };
//    public Handler getHandler(){
//        return this.picHandler;
//    }

    public class GetPictureThread extends Thread{
        /**
         * 消息变量
         */
        private Message msg;
        private Bundle bdl;
        private int position;
        private Context mContext;
        private NewsBitmap mNewsBitmap = new NewsBitmap();

        /**
         * 构造方法
         */
        public GetPictureThread(){
        }

        /**
         * run方法
         */
        public void run(){
            for(int i = 0; i < Constant.footballUserName.size();i++) {
                Constant.headBitmap.add(getBitmapFromServer(Constant.footballUserHeadImage.get(i).toString()));
                Constant.bodyBitmap.add(getBitmapFromServer(Constant.messageImage.get(i).toString()));
            }
            sendMessage(Constant.msg_connect,"");
        }

        public Bitmap getBitmapFromServer(String imagePath) {
            HttpGet get = new HttpGet(Constant.picHost + imagePath);
            HttpClient client = new DefaultHttpClient();
            Bitmap pic = null;
            try {
                HttpResponse response = client.execute(get);
                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();
                pic = BitmapFactory.decodeStream(is);   // 关键是这句代码
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //
            return pic;
        }

        //发送消息用于更新UI
        public void sendMessage(int x, String s){
            msg = new Message();
            bdl = new Bundle();
            bdl.putString("1",s);
            msg.what = x;
            msg.setData(bdl);
            picHandler.sendMessage(msg);
        }
    }


    /**
     * FocusThread
     */
    public class FocusThread extends Thread{
        private Message msg;
        private Bundle bdl;

        public void run(){
            InputStream resIS;
            StringBuilder resSB;
            String resStr;

            resIS = getIS(Constant.strURL);
            if (resIS == null){
                return;
            }else{
                resSB = JsonToList.isTosb(resIS);
                resStr = resSB.toString();
                try{
                    JsonToList.jsonTolist(resStr);
                    sendMessage(Constant.msg_connect,"");
                }catch (Exception e){
                    e.printStackTrace();
                    sendMessage(Constant.msg_error,e.toString());
                }
            }
        }

        private InputStream getIS(String url) {
            InputStream is = null;
            try{
                //取得默认HttpClien实例
                HttpClient hc = new DefaultHttpClient();
                //连接到服务器
                HttpResponse hr = hc.execute(new HttpGet(url));
                //获取数据内容
                is = hr.getEntity().getContent();
            }catch(Exception e){
                e.printStackTrace();
                sendMessage(Constant.msg_error,e.toString());
            }
            Log.d("testconn:","2.8");
            return is;
        }
        //发送消息用于更新UI
        public void sendMessage(int x, String s){
            msg = new Message();
            bdl = new Bundle();
            bdl.putString("1",s);
            msg.what = x;
            msg.setData(bdl);
            messageHandler.sendMessage(msg);

        }
    }
    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
    }
}
