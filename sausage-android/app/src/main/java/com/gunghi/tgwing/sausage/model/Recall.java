package com.gunghi.tgwing.sausage.model;

/**
 * Created by joyeongje on 2017. 8. 27..
 */

public class Recall {

    private String company;
    private String date;
    private String imageUrl;
    private String name;
    private String reason;

    public Recall(){

    }

    public Recall(String company, String date, String imageUrl, String name, String reason) {
        this.company = company;
        this.date = date;
        this.imageUrl = imageUrl;
        this.name = name;
        this.reason = reason;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
