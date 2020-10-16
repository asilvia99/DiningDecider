package com.example.diningdecider;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Restaurant {
    String name;
    String address;
    String phonenumber;
    int distance;
    Price pricelevel;
    FoodType foodTypes;
    public int imageID;
    Boolean favorite;
    Boolean pastWinner;

    public Restaurant(String name, String address, String phonenumber, int distance, Price pricelevel, FoodType foodTypes, int imageID) {
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.distance = distance;
        this.pricelevel = pricelevel;
        this.foodTypes = foodTypes;
        this.imageID = imageID;
        this.favorite = false;
        this.pastWinner = false;
    }
}
