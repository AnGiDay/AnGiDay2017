package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class User {
    private String UserID;
    private String UserName;

    public User(String userID, String userName) {
        UserID = userID;
        UserName = userName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
