package com.example.diningdecider;

import java.util.ArrayList;

public class FilterData {
    int radius;
    Price price;
    ArrayList<FoodType> foodTypes;


    public FilterData(){
        this.radius = 10;
        this.price = Price.NONE;
        this.foodTypes = new ArrayList<FoodType>();
    }



}
