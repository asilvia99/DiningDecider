package com.example.diningdecider

import android.content.Intent
import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_random_page.*

class WinnerPage : AppCompatActivity() {

    private lateinit var winner :Restaurant
    private lateinit var resImg : ImageView
    private lateinit var resName : TextView
    private lateinit var backButton: ImageButton
    private lateinit var favButton: ImageButton
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
        favButton = findViewById(R.id.wp_favButton1)



        resImg.setImageResource(winner.imageID)
        resName.setText(winner.name)
        setInitFavButtons()




        //Winner Screen Buttons
        winnerBackArrow = findViewById(R.id.wp_backbutton)
        finishButton = findViewById(R.id.wp_finishbutton)
        callButton = findViewById(R.id.wp_phonebutton)
        directionsButton = findViewById(R.id.wp_directionbutton)

        //Directions Button
        directionsButton.setOnClickListener {
            val address = winner.address
            val gmmIntentUri =
                Uri.parse("geo:42.2743,71.8081?q=$address")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        //Phone Button
        callButton.setOnClickListener {
            val phoneNumber = winner.phonenumber
            intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        //Winner Screen
        winnerBackArrow.setOnClickListener {
            intent = Intent(this, FilterPage::class.java)
            startActivity(intent)
        }
        finishButton.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        favButton.setOnClickListener{
            setFavorite()
        }


    }
    private fun setFavorite(){
        if (winner.favorite == true){
            winner.favorite = false
            favButton.setImageResource(R.drawable.heart)
        }
        else if (winner.favorite == false){
            winner.favorite = true
            favButton.setImageResource(R.drawable.heartfilled)
        }

    }
    private fun setInitFavButtons(){
        if (winner.favorite == true){
            favButton.setImageResource(R.drawable.heartfilled)
        }
        else if (winner.favorite == false){
            favButton.setImageResource(R.drawable.heart)
        }
    }
}