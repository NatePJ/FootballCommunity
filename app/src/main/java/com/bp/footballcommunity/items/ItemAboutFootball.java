package com.bp.footballcommunity.items;

import android.graphics.Bitmap;

/**
 * Created by BP on 2017/6/10.
 */
public class ItemAboutFootball {
    private String aboutId;
    private String userName;
    private Bitmap userHeadImage;
    private String currPeople;
    private String maxPeople;
    private String aboutTime;
    private String playground;
    private String city;
    private String isJoin;

    public ItemAboutFootball(String aboutId,
                             String userName,
                             Bitmap userHeadImage,
                             String currPeople,
                             String maxPeople,
                             String aboutTime,
                             String playground,
                             String city,
                             String isJoin){
        this.aboutId = aboutId;
        this.userName = userName;
        this.userHeadImage = userHeadImage;
        this.currPeople = currPeople;
        this.maxPeople = maxPeople;
        this.aboutTime = aboutTime;
        this.playground = playground;
        this.city = city;
        this.isJoin = isJoin;
    }

    public String getAboutId() {
        return aboutId;
    }

    public void setAboutId(String aboutId) {
        this.aboutId = aboutId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bitmap getUserHeadImage() {
        return userHeadImage;
    }

    public void setUserHeadImage(Bitmap userHeadImage) {
        this.userHeadImage = userHeadImage;
    }

    public String getCurrPeople() {
        return currPeople;
    }

    public void setCurrPeople(String currPeople) {
        this.currPeople = currPeople;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getAboutTime() {
        return aboutTime;
    }

    public void setAboutTime(String aboutTime) {
        this.aboutTime = aboutTime;
    }

    public String getPlayground() {
        return playground;
    }

    public void setPlayground(String playground) {
        this.playground = playground;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsJoin() {
        return isJoin;
    }

    public void setIsJoin(String isJoin) {
        this.isJoin = isJoin;
    }
}
