package com.example.khach.myrestaurant.Entity;

/**
 * Created by khach on 09/06/2017.
 */

public class Food {
    String  name;
    String fee;
    int  image;
    float rate;

    public Food() {
    }

    public Food(String name, String fee, int image, float rate) {

        this.name = name;
        this.fee = fee;
        this.image = image;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
