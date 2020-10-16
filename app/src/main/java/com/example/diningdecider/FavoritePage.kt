package com.example.diningdecider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritePage : AppCompatActivity() {

    //Favorites Page Buttons
    private lateinit var favBackArrow: ImageButton
    private lateinit var favoritesText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorites)


        //Favorites Screen Buttons
        favBackArrow = findViewById(R.id.favBackArrow)
        favoritesText = findViewById(R.id.list_view)

        loadData()
        //Favorites Screen
        favBackArrow.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

    private fun loadData(){
        var sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        var gson = Gson()
        var json = sharedPreferences.getString("favs list", null)
        val type = TypeToken.getParameterized(ArrayList::class.java, Restaurant::class.java).type
        var favsList = gson.fromJson<ArrayList<Restaurant>>(json, type)

        if (favsList != null){
//          populate favorites
            favoritesText.setText(favsList[0].name)
            System.out.println(favsList[0].name + "is a favorite")
//            val adapter = ArrayAdapter(this, android.R.layout.activity_list_item, favsList)
//            favoritesText.adapter = adapter
        }
    }
}