package com.example.diningdecider

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FavoritePage : AppCompatActivity() {

    //Favorites Page Buttons
    private lateinit var favBackArrow: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites)

        //Favorites Screen Buttons
        favBackArrow = findViewById(R.id.favBackArrow)

        //Favorites Screen
        favBackArrow.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}