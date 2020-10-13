package com.example.diningdecider

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity



class HomePage : AppCompatActivity() {

    //Home Page Button
    private lateinit var findButton: Button
    private lateinit var favoriteButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)


        //Home Screen Buttons
        favoriteButton = findViewById(R.id.favoritesButton)
        findButton = findViewById(R.id.findbutton)

        //Home Screen
        favoriteButton.setOnClickListener {
            intent = Intent(this, FavoritePage::class.java)
            startActivity(intent)
        }

        findButton.setOnClickListener {
            intent = Intent(this, FilterPage::class.java)
            startActivity(intent)
        }
    }
}