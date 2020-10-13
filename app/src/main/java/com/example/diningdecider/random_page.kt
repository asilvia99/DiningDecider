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

class random_page : AppCompatActivity() {

    //Random Page Buttons
    //private lateinit var dineButton: Button
    private lateinit var newButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var tournament: Tournament
    private lateinit var resName: TextView
    private lateinit var resImage: ImageView
    private lateinit var resInfo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_page)


        //Random Screen Buttons associated with their XML IDS
        //dineButton = findViewById(R.id.button3)
        newButton = findViewById(R.id.NEWbutton)
        backButton = findViewById(R.id.backButton)
        resName = findViewById(R.id.RestaurantName)
        resImage = findViewById(R.id.RestaurantImage)
        resInfo = findViewById(R.id.info)
        tournament = Tournament(null)

        //randomize options to begin with
        randomizeOption();

//        //Random Screen - What to set the dine button to? and edit new button
//        dineButton.setOnClickListener {
//            intent = Intent(this, WinnerPage::class.java)
//            startActivity(intent)
//        }

        //randomize options after clicking
        newButton.setOnClickListener {
            randomizeOption();
        }

        backButton.setOnClickListener {
            intent = Intent(this, FilterPage::class.java)
            startActivity(intent)
        }
    }

    private fun randomizeOption(){
        var newrestaurant = tournament.getNewRandomRestaurant()

        resImage.setImageResource(newrestaurant.imageID)
        resName.setText(newrestaurant.name)
        resInfo.setText(newrestaurant.address)
    }


}