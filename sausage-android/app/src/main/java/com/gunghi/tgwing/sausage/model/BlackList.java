package com.gunghi.tgwing.sausage.model;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class BlackList {

    private String name;
    private String linkURL;
    private String description;
    private int drawable;

    public BlackList() {

    }

    public BlackList(String name, String linkURL, int drawable, String description) {
        this.drawable = drawable;
        this.name = name;
        this.linkURL = linkURL;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
