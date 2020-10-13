package com.example.diningdecider

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson

class WinnerPage : AppCompatActivity() {

    private lateinit var winner :Restaurant
    private lateinit var resImg : ImageView
    private lateinit var resName : TextView
    private lateinit var backButton: ImageButton
    private lateinit var callButton: ImageButton
    private lateinit var directionsButton: ImageButton
    private lateinit var finishButton:ImageButton

    //Winner Page Buttons
    private lateinit var winnerBackArrow: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_page)
        var json = intent.getStringExtra("Restaurant")
        val gson = Gson()
        var bundle = gson.fromJson(json, Restaurant::class.java)
        this.winner = bundle
        resName = findViewById(R.id.wp_name)
        resImg = findViewById(R.id.wp_img)
        resImg.setImageResource(winner.imageID)
        resName.setText(winner.name)


        //Winner Screen Buttons
        winnerBackArrow = findViewById(R.id.wp_backbutton)
        finishButton = findViewById(R.id.wp_backbutton)

        //Winner Screen
        winnerBackArrow.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        finishButton.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

    }
}