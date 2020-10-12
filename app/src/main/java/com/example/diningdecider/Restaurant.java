package com.example.diningdecider;

import java.util.ArrayList;

public class Restaurant {
    String name;
    String address;
    int phonenumber;
    Price pricelevel;
    ArrayList<FoodType> foodTypes;
    public int imageID;
    Boolean favorite;
    Boolean pastWinner;

    public Restaurant(String name, String address, int phonenumber, Price pricelevel, ArrayList<FoodType> foodTypes, int imageID){
        this.name = name;
        this.address = address;
        this.phonenumber = phonenumber;
        this.pricelevel = pricelevel;
        this.foodTypes = foodTypes;
        this.favorite = false;
        this.pastWinner = false;
    }
}
