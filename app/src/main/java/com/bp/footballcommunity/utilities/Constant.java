package com.bp.footballcommunity.utilities;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BP on 2017/5/31.
 */
public class Constant {

    // 访问的URL
    public static String strHost = new String("http://192.168.137.1:8080/SSHProject/");
    public static String picHost = new String("http://192.168.137.1:8080/SSHProject/pic/");
    public static String spaceHost = new String("http://192.168.137.1:8080/SSHProject/space/");
    public static String strAct1 = new String("queryalluser");
    public static String strAct2 = new String("uploadthumbsup");
    public static String strAct3 = new String("uploadpicture");
    public static String strURL = strHost + strAct1;
    public static String URLThumbsUp = strHost + strAct2;
    public static String URLPicture = strHost + strAct3;


    public static final int HEAD = 0;
    public static final int BODYTEXT = 1;
    public static final int BODYIMAGE = 2;
    public static final int BUTTON = 3;

    public static final int TEXT = 1;
    public static final int IMAGE = 2;
    public static final int TEXTANDIMAGE = 3;

    // 消息类型
    public static final int msg_connect = 1;
    public static final int msg_error = 2;
    public static final int msg_con_start = 3;
    public static final int msg_con_stop = 4;

    // 列表
    public static List<String> messageId = new ArrayList();
    public static List<String> footballUserName = new ArrayList();
    public static List<String> footballUserHeadImage = new ArrayList();
    public static List<String> messageText = new ArrayList();
    public static List<String> messageImage = new ArrayList();
    public static List<String> thumbsUp = new ArrayList();

    public static List<Bitmap> headBitmap = new ArrayList();
    public static List<Bitmap> bodyBitmap = new ArrayList();

}
