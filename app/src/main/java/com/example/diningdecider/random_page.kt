package com.example.diningdecider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class random_page : AppCompatActivity() {

    //Random Page Buttons
    //private lateinit var dineButton: Button
    private lateinit var newButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_page)


        //Random Screen Buttons
        //dineButton = findViewById(R.id.button3)
        newButton = findViewById(R.id.button2)

//        //Random Screen - What to set the dine button to? and edit new button
//        dineButton.setOnClickListener {
//            intent = Intent(this, WinnerPage::class.java)
//            startActivity(intent)
//        }

        newButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
            startActivity(intent)
        }
    }



}