package com.example.diningdecider

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
            startActivity(intent)
        }

        helpMeButton.setOnClickListener {
            intent = Intent(this, TournamentActivity::class.java)
            startActivity(intent)
        }
    }
}