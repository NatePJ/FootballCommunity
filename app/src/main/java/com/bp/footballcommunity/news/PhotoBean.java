package com.bp.footballcommunity.news;

import android.graphics.Bitmap;

/**
 * Created by BP on 2017/6/3.
 */
public class PhotoBean {
    //路径
    private String path;
    //是否选择
    private boolean isSelect;
    //位图转换
    private Bitmap bitmap;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
