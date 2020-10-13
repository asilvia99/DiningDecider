package com.example.diningdecider;

import java.util.ArrayList;

public class FilterData {
    int radius;
    Price price;
    ArrayList<FoodType> foodTypes;

    public FilterData(int radius, Price price, ArrayList<FoodType> foodTypes){
        this.radius = radius;
        this.price = price;
        this.foodTypes = foodTypes;
    }



}
