package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class Account {
    private String UserID;
    private String TypeAccountID;
    private String Password;
    private String PhoneNo;

    public Account() {
    }

    public Account(String userID, String typeAccountID, String password, String phoneNo) {
        UserID = userID;
        TypeAccountID = typeAccountID;
        Password = password;
        PhoneNo = phoneNo;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTypeAccountID() {
        return TypeAccountID;
    }

    public void setTypeAccountID(String typeAccountID) {
        TypeAccountID = typeAccountID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }
}
