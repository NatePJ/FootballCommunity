package com.bp.footballcommunity.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bp.footballcommunity.R;
import com.bp.footballcommunity.items.ItemAboutFootball;
import com.bp.footballcommunity.news.NewsBitmap;
import com.bp.footballcommunity.news.NewsFocus;
import com.bp.footballcommunity.utilities.Constant;
import com.bp.footballcommunity.utilities.JsonToList;
import com.bp.footballcommunity.utilities.NewsToItemFocus;
import com.bp.footballcommunity.utilities.UploadUtility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by BP on 2017/6/4.
 */
public class AboutFootballFragment extends ListFragment {

    private static final String TAG = "AboutFootballFragment";
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String DIALOG_DATE = "date";

    private GetPictureThread mGetPictureThread;
    private AboutFootballThread mAboutFootballThread;
    private AboutFootballAdapter mAboutFootballAdapter;
    private ArrayList<ItemAboutFootball> mItemAboutFootballs= new ArrayList();

    private ListView mListView;
//    private ImageView mImageView;
//    private TextView mTextView;
//    private TextView mDetailView;
//    private TextView mDetailView2;
//    private TextView mDetailView3;
//    private TextView mDetailView4;
//    private Button mButton;


    public static AboutFootballFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AboutFootballFragment aboutFootballFragment = new AboutFootballFragment();
        aboutFootballFragment.setArguments(args);
        return aboutFootballFragment;
    }
    public AboutFootballFragment(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 开启线程获取数据
         * 开启线程
         */
        mAboutFootballThread = new AboutFootballThread();
        mAboutFootballThread.start();
        /**
         * 设置适配器
         */
        //TODO 设置适配器
        mAboutFootballAdapter = new AboutFootballAdapter(mItemAboutFootballs);
//        mListView = (ListView)findViewById(R.id.)
        setListAdapter(mAboutFootballAdapter);
    }


    /**
     * 监听器
     */
    @Override
    public void onListItemClick(ListView l,View v,int position,long id){
        ItemAboutFootball i = (ItemAboutFootball)(getListAdapter()).getItem(position);
        Log.d("listitem", i.getAboutId() + "was clicked");
        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * 适配器
     */
    private class AboutFootballAdapter extends ArrayAdapter<ItemAboutFootball>{
        private ArrayList<ItemAboutFootball> itemAboutFootballs;
        private static final String isJoinJudgeY = "1";
        private static final String isJoinJudgeN = "0";
        public  AboutFootballAdapter(ArrayList<ItemAboutFootball> itemAboutFootballs){
            super(getActivity(), 0, itemAboutFootballs);
        }
        public void updateAboutFootballAdapter(ArrayList<ItemAboutFootball> itemAboutFootballs){
            this.itemAboutFootballs = itemAboutFootballs;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_about_football, null);
            }

            ItemAboutFootball itemAboutFootball = getItem(position);

            Button mButton = (Button)convertView.findViewById(R.id.add_about_football);;
            String itemjudge = itemAboutFootball.getIsJoin();
            if(itemjudge.equals(isJoinJudgeY)){

                mButton.setBackgroundColor(555555);
                mButton.setClickable(false);
            }

            mButton.setTag(R.id.tag_first,itemAboutFootball.getAboutId());
            mButton.setTag(R.id.tag_second,itemAboutFootball.getIsJoin());

            ImageView mImageView = (ImageView)convertView.findViewById(R.id.about_football_head_image);
            mImageView.setImageBitmap(itemAboutFootball.getUserHeadImage());

            TextView mTextView = (TextView)convertView.findViewById(R.id.about_football_head_name);
            mTextView.setText(itemAboutFootball.getUserName());

            TextView mDetailView = (TextView)convertView.findViewById(R.id.about_football_detail);
            mDetailView.setText(itemAboutFootball.getCity());

            TextView mDetailView2 = (TextView)convertView.findViewById(R.id.about_football_detail2);
            mDetailView2.setText(itemAboutFootball.getPlayground());

            TextView mDetailView3 = (TextView)convertView.findViewById(R.id.about_football_detail3);
            mDetailView3.setText(itemAboutFootball.getAboutTime());

            TextView mDetailView4 = (TextView)convertView.findViewById(R.id.about_football_detail4);
            mDetailView4.setText(itemAboutFootball.getCurrPeople()+"/"+itemAboutFootball.getMaxPeople());



            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getTag(R.id.tag_second).equals(isJoinJudgeN)) {
                        String tagpos = (String) view.getTag(R.id.tag_first);
                        UploadUtility.joinAboutFootball(tagpos);
                        Toast.makeText(getActivity(), String.valueOf(tagpos), Toast.LENGTH_SHORT).show();
                        view.setBackgroundColor(555555);
                        view.setClickable(false);
                    }
                }
            });
            return convertView;
        }
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
                    for(int i = 0;i < Constant.aboutHeadBitmap.size();i++){
                        mItemAboutFootballs.add(new ItemAboutFootball(
                                Constant.aboutId.get(i),
                                Constant.aboutFootballUserName.get(i),
                                Constant.aboutHeadBitmap.get(i),
                                Constant.aboutCurrPeople.get(i),
                                Constant.aboutMaxPeople.get(i),
                                Constant.aboutTime.get(i),
                                Constant.aboutPlayground.get(i),
                                Constant.aboutCity.get(i),
                                Constant.aboutIsJoin.get(i))
                        );
                    }
//                    JsonToList.deleteAboutArrauList();
                    /**
                     * 设置适配器
                     */
//                    mAboutFootballAdapter.updateFocusAdapter(getActivity(), mItemFocuses);
//                    mAboutFootballAdapter.setAdapter(focusAdapter);
                    mAboutFootballAdapter.updateAboutFootballAdapter(mItemAboutFootballs);
                    setListAdapter(mAboutFootballAdapter);
                    break;
                case  Constant.msg_error:
                    Log.d("MainActivityConn:","pic_error");
                    break;
            }
            super.handleMessage(msg);
        }

    };

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
            for(int i = 0; i < Constant.aboutFootballUserName.size();i++) {
                Constant.aboutHeadBitmap.add(getBitmapFromServer(Constant.aboutUserHeadImage.get(i).toString()));
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
    public class AboutFootballThread extends Thread{
        private Message msg;
        private Bundle bdl;

        public void run(){
            InputStream resIS;
            StringBuilder resSB;
            String resStr;

            resIS = getIS(Constant.aboutURL);
            if (resIS == null){
                return;
            }else{
                resSB = JsonToList.aboutisTosb(resIS);
                resStr = resSB.toString();
                try{
                    JsonToList.aboutjsonTolist(resStr);
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
}
