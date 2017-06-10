package com.bp.footballcommunity.utilities;

import android.util.Log;

/**
 * Created by BP on 2017/6/9.
 */
public class ChangeItemClick {
    private static final String TAG = "ChangeItemClick";
    private static final String itemJudgeY = "1";
    private static final String itemJudgeN = "0";
    public static String changeThumbsUpItemText(String itemText){
        String newItemText = itemText;
        switch(itemText){
            case itemJudgeY:
                newItemText = "0";
                break;
            case itemJudgeN:
                newItemText = "1";
                break;
        }
        return newItemText;
    }
}
