package com.gunghi.tgwing.sausage.model;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class Community {

    private String userName;
    private String profileUrl;
    private String title;
    private String description;
    private String tag;
    private int likeNum;
    private int hateNum;
    private String reviewUserName;
    private String reviewDescripton;

    public Community(String userName, String profileUrl, String title, String description, String tag, int likeNum, int hateNum, String reviewUserName, String reviewDescripton) {
        this.userName = userName;
        this.profileUrl = profileUrl;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.likeNum = likeNum;
        this.hateNum = hateNum;
        this.reviewUserName = reviewUserName;
        this.reviewDescripton = reviewDescripton;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getHateNum() {
        return hateNum;
    }

    public void setHateNum(int hateNum) {
        this.hateNum = hateNum;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public String getReviewDescripton() {
        return reviewDescripton;
    }

    public void setReviewDescripton(String reviewDescripton) {
        this.reviewDescripton = reviewDescripton;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
