package com.example.diningdecider

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class FavoritePage : AppCompatActivity() {

    val arrayList = ArrayList<Restaurant>()
    //Favorites Page Buttons
    private lateinit var favBackArrow: ImageButton
    private lateinit var favRecyclerView: RecyclerView
    private lateinit var favAdapter: Adapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

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