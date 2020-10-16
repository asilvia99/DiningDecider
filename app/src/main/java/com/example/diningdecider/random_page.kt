package com.example.diningdecider

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson

class random_page : AppCompatActivity() {

    //Random Page Buttons
    //private lateinit var dineButton: Button
    private lateinit var newButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var tournament: Tournament
    private lateinit var resName: TextView
    private lateinit var resImage: ImageView
    private lateinit var resInfo: TextView
    private lateinit var filters : FilterData
    private lateinit var restaurant: Restaurant
    private var resIndex: Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_page)

        var json = intent.getStringExtra("Filters")
        val gson = Gson()
        var bundle = gson.fromJson(json, FilterData::class.java)
        this.filters = bundle
        tournament = Tournament(filters)

        newButton = findViewById(R.id.NEWbutton)
        backButton = findViewById(R.id.backButton)
        resName = findViewById(R.id.RestaurantName)
        resImage = findViewById(R.id.RestaurantImage)
        resInfo = findViewById(R.id.info)

        //randomize options to begin with
        resIndex = tournament.getNewRandomRestaurant()
        restaurant = tournament.restaurants.get(resIndex)

        set()

        //randomize options after clicking
        newButton.setOnClickListener {
            setNext();
        }

        backButton.setOnClickListener {
            intent = Intent(this, FilterPage::class.java)
            startActivity(intent)
        }
    }





    private fun setNext(){

        if (resIndex == tournament.restaurants.size -1){
            resIndex = 0;
            restaurant = tournament.restaurants[resIndex]
        }
        else{
            resIndex++
            restaurant = tournament.restaurants[resIndex]
        }
        set()
    }
    //lets send it
    private fun set(){
        resImage.setImageResource(restaurant.imageID)
        resName.setText(restaurant.name)
        resInfo.setText(restaurant.address)
    }




}