package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class RatingDetail {
    private String UserID;
    private  String ResID;
    private String Comment;
    private float Rating;
    ///Like = 1 to save Like list of user
    private int Like;

    public RatingDetail(String userID, String resID, String comment, float rating, int like) {
        UserID = userID;
        ResID = resID;
        Comment = comment;
        Rating = rating;
        Like = like;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        ResID = resID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public float getRating() {
        return Rating;
    }

    public void setRating(float rating) {
        Rating = rating;
    }

    public int getLike() {
        return Like;
    }

    public void setLike(int like) {
        Like = like;
    }
}
