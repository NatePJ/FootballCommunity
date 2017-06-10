package com.bp.footballcommunity.utilities;

import android.util.Log;

import com.bp.footballcommunity.R;
import com.bp.footballcommunity.items.ItemFocus;
import com.bp.footballcommunity.news.NewsFocus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BP on 2017/5/31.
 */
public class NewsToItemFocus {
    private static final String TAG = "NewsToItemFocus";
    public static ArrayList<ItemFocus> newsToItemFocus(ArrayList<NewsFocus> list){
        ArrayList<ItemFocus> items = new ArrayList<ItemFocus>();
        Log.d(TAG,"2.5");
        for(NewsFocus newsFocus : list){
            items.add(createHead(newsFocus));
            if(newsFocus.getNewsType() == Constant.TEXTANDIMAGE){
                Log.d(TAG,"TEXTANDIMAGE");
            }
            if(Constant.TEXT == newsFocus.getNewsType()){
                items.add(createBodyText(newsFocus));
            }else if(Constant.IMAGE == newsFocus.getNewsType()){
                items.add(creatBodyImage(newsFocus));
            }else{
                items.add(createBodyText(newsFocus));
                items.add(creatBodyImage(newsFocus));
            }
            items.add(createButtons(newsFocus));
        }
        return items;
    }


    /**
     * 创建用户信息头栏
     * @param newsFocus
     * @return
     */
    private static ItemFocus createHead(NewsFocus newsFocus){
        //定义返回的Item
        ItemFocus headItem = new ItemFocus();

        //设置消息类型为文本或图片
        headItem.setNewsType(newsFocus.getNewsType());
        headItem.setMessageId(newsFocus.getMessageId());
        //设置显示位置
        headItem.setStyleType(Constant.HEAD);
        //设置显示值
        headItem.setBitmap(newsFocus.getHeadImageId());
        headItem.setText(newsFocus.getUserName());
        Log.d(TAG,"2.1");
        //返回Item
        return headItem;
    }

    /**
     * 创建消息文本
     * @param newsFocus
     * @return
     */
    private static ItemFocus createBodyText(NewsFocus newsFocus){
        //定义返回的Item
        ItemFocus bodyTextItem = new ItemFocus();

        //设置消息类型为文本或图片
        bodyTextItem.setNewsType(newsFocus.getNewsType());
        bodyTextItem.setMessageId(newsFocus.getMessageId());
        //设置显示位置
        bodyTextItem.setStyleType(Constant.BODYTEXT);
        //设置显示值
        bodyTextItem.setText(newsFocus.getFootballTimeText());
        Log.d(TAG,"2.2");
        //返回Item
        return bodyTextItem;
    }

    /**
     * 创建消息图片
     * @param newsFocus
     * @return
     */
    private static ItemFocus creatBodyImage(NewsFocus newsFocus){
        //定义返回Item
        ItemFocus bodyImageItem = new ItemFocus();

        //设置消息类型
        bodyImageItem.setNewsType(newsFocus.getNewsType());
        bodyImageItem.setMessageId(newsFocus.getMessageId());
        //设置显示位置
        bodyImageItem.setStyleType(Constant.BODYIMAGE);
        //设置显示值
        bodyImageItem.setBitmap(newsFocus.getFootballTimeImageId());
        Log.d(TAG,"2.3");
        //返回Item
        return bodyImageItem;
    }

    /**
     * 设置评论点赞
     * @param newsFocus
     * @return
     */
    private static ItemFocus createButtons(NewsFocus newsFocus){
        //定义返回Item
        ItemFocus buttonItem = new ItemFocus();

        //设置消息类型
        buttonItem.setNewsType(newsFocus.getNewsType());
        buttonItem.setMessageId(newsFocus.getMessageId());
        //设置显示位置
        buttonItem.setStyleType(Constant.BUTTON);
        //设置显示值
        buttonItem.setImageComment(R.drawable.comment);
        buttonItem.setText(newsFocus.getThumbsUp());
        Log.d(TAG,"2.4");
        //返回Item
        return buttonItem;
    }
}
