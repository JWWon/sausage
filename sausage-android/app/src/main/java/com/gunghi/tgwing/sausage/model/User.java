package com.gunghi.tgwing.sausage.model;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

public class User {

    public static String staticUserToken = null;
    private static User currentUser = new User();

    public User(String uid, String name, String email, String photoUrl, String token) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
        this.token = token;
    }

    public static User getInstance() {
        return currentUser;
    }

    public static void setInstance(User user) {
        currentUser = user;
    }

    private String uid;
    private String name;
    private String email;
    private String photoUrl;
    private String token;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}

