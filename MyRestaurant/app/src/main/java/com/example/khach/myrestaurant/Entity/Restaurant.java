package com.example.khach.myrestaurant.Entity;

/**
 * Created by ThanhHai on 6/13/2017.
 */

public class Restaurant {
    private String ResID;
    private String ResName;
    private float Latitude;
    private float Longitude;
    private int TotalSpace;
    private int FreeSpace;
    private String image; //to save link image;
    private float AverageRating;
    private int NumberOfRating;
    private String Description;

    public Restaurant(String resID, String resName, float latitude, float longitude, int totalSpace, int freeSpace, String image, float averageRating, int numberOfRating, String description) {
        ResID = resID;
        ResName = resName;
        Latitude = latitude;
        Longitude = longitude;
        TotalSpace = totalSpace;
        FreeSpace = freeSpace;
        this.image = image;
        AverageRating = averageRating;
        NumberOfRating = numberOfRating;
        Description = description;
    }

    public Restaurant() {
    }

    public String getResID() {
        return ResID;
    }

    public void setResID(String resID) {
        this.ResID = resID;
    }

    public String getResName() {
        return ResName;
    }

    public void setResName(String resName) {
        this.ResName = resName;
    }

    public float getLatitude() {
        return Latitude;
    }

    public void setLatitude(float latitude) {
        this.Latitude = latitude;
    }

    public float getLongitude() {
        return Longitude;
    }

    public void setLongitude(float longitude) {
        this.Longitude = longitude;
    }

    public int getTotalSpace() {
        return TotalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.TotalSpace = totalSpace;
    }

    public int getFreeSpace() {
        return FreeSpace;
    }

    public void setFreeSpace(int freeSpace) {
        this.FreeSpace = freeSpace;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(float averageRating) {
        this.AverageRating = averageRating;
    }

    public int getNumberOfRating() {
        return NumberOfRating;
    }

    public void setNumberOfRating(int numberOfRating) {
        this.NumberOfRating = numberOfRating;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
