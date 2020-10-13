package com.example.diningdecider

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class FilterPage : AppCompatActivity() {

    //Filter Page Buttons
    private lateinit var chooseForMeButton: Button
    private lateinit var helpMeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        //Filter Screen Buttons
        chooseForMeButton = findViewById(R.id.button2)
        helpMeButton = findViewById(R.id.button3)


        //Filter Screen - What page is the tournament called?
        chooseForMeButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
//            var filter =  getFilterPreferences
//            val gson = Gson()
//            val json = gson.toJson(filter)
//            intent.putExtra("Filters", json)
            startActivity(intent)
        }

        helpMeButton.setOnClickListener {
            intent = Intent(this, TournamentActivity::class.java)
//            var filter =  getFilterPreferences
//            val gson = Gson()
//            val json = gson.toJson(filter)
//            intent.putExtra("Filters", json)
            startActivity(intent)
        }
    }

    /**
     * Gets user preferences based on buttons clicked
     */
    fun getFilterPreferences(){
        // return a FilterData object here
    }
}