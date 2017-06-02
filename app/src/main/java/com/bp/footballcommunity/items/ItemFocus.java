package com.bp.footballcommunity.items;

/**
 * Created by BP on 2017/5/31.
 */
public class ItemFocus {
    private static final String TAG = "ItemFocus";
    private int newsType;
    private int newsId;
    private int styleType;
    private String text;
    private int imageSource;
    private int imageComment;
    private int imageLike;

    /**
     * getter
     * @return
     */
    public int getNewsType() {
        return newsType;
    }

    public int getNewsId() {
        return newsId;
    }

    public int getStyleType() {
        return styleType;
    }

    public String getText() {
        return text;
    }

    public int getImageSource() {
        return imageSource;
    }

    public int getImageComment() {
        return imageComment;
    }

    public int getImageLike() {
        return imageLike;
    }

    /**
     * setter
     * @param newsType
     */
    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public void setStyleType(int styleType) {
        this.styleType = styleType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public void setImageComment(int imageComment) {
        this.imageComment = imageComment;
    }

    public void setImageLike(int imageLike) {
        this.imageLike = imageLike;
    }
}
