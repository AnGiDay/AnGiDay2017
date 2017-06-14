package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class AccountType {
    private String UserID;
    private String UserName;

    public AccountType(String userID, String userName) {
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

    public AccountType() {
    }
}
