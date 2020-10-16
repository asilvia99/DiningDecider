package com.example.diningdecider

import android.R.attr.button
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

private const val TAG = "FilterPage"


class FilterPage : AppCompatActivity() {

    //Filter Page Buttons
    private lateinit var distanceTextView: TextView
    private lateinit var distanceSeekBar: SeekBar
    private lateinit var chooseForMeButton: Button
    private lateinit var helpMeButton: Button
    private lateinit var price1Button : ImageButton
    private lateinit var price2Button : ImageButton
    private lateinit var price3Button : ImageButton
    private lateinit var fastfoodCheckBox: CheckBox
    private lateinit var breakfastCheckBox: CheckBox
    private lateinit var chineseCheckBox: CheckBox
    private lateinit var pubCheckBox: CheckBox
    private lateinit var mexicanCheckBox: CheckBox
    private lateinit var seafoodCheckBox: CheckBox
    private lateinit var backButton : ImageButton



    private lateinit var filterData : FilterData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        filterData = FilterData()

        //Filter Screen Buttons
        chooseForMeButton = findViewById(R.id.randomButton)
        helpMeButton = findViewById(R.id.tournamentButton)
        backButton = findViewById(R.id.backImageButton)
        distanceSeekBar = findViewById(R.id.distanceSeekBar)
        distanceSeekBar.setOnSeekBarChangeListener(seekBarChangeListener)
        price1Button = findViewById(R.id.price1ImageButton)
        price2Button = findViewById(R.id.price2ImageButton)
        price3Button = findViewById(R.id.price3ImageButton)
        fastfoodCheckBox = findViewById(R.id.fastfoodCheckBox)
        breakfastCheckBox = findViewById(R.id.breakfastCheckBox)
        chineseCheckBox = findViewById(R.id.chineseCheckBox)
        pubCheckBox = findViewById(R.id.pubCheckBox)
        mexicanCheckBox = findViewById(R.id.mexicanCheckBox)
        seafoodCheckBox = findViewById(R.id.seafoodCheckBox)

        distanceTextView = findViewById(R.id.distance_textView)
        distanceTextView.setText("Distance: 10")
        fillAllMoneyButtons()

        backButton.setOnClickListener{
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        // Filter for only getting restaurants with low price
        price1Button.setOnClickListener {
            filterData.price = Price.LOW
            highlightMoneyButtons(price1Button)
        }

        // Filter for only getting restaurants with medium price
        price2Button.setOnClickListener {
            filterData.price = Price.MEDIUM
            highlightMoneyButtons(price2Button)
        }

        // Filter for only getting restaurants with high price
        price3Button.setOnClickListener {
            filterData.price = Price.HIGH
            highlightMoneyButtons(price3Button)
        }

        fastfoodCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.FastFood)
            }
            else {
                filterData.foodTypes.remove(FoodType.FastFood)
            }
        }

        pubCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.Pub)
            }
            else {
                filterData.foodTypes.remove(FoodType.Pub)
            }
        }

        mexicanCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.Mexican)
            }
            else {
                filterData.foodTypes.remove(FoodType.Mexican)
            }
        }

        breakfastCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.Breakfast)
            }
            else {
                filterData.foodTypes.remove(FoodType.Breakfast)
            }
        }

        chineseCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.Chinese)
            }
            else {
                filterData.foodTypes.remove(FoodType.Chinese)
            }
        }

        seafoodCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                filterData.foodTypes.add(FoodType.Seafood)
            }
            else {
                filterData.foodTypes.remove(FoodType.Seafood)
            }
        }


        //Filter Screen - What page is the tournament called?
        chooseForMeButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
            val gson = Gson()
            val json = gson.toJson(filterData)
            intent.putExtra("Filters", json)
            startActivity(intent)
        }

        helpMeButton.setOnClickListener {

            Log.d(TAG, "Here it is ${filterData.radius} ${filterData.price} ${filterData.foodTypes}")
            intent = Intent(this, TournamentActivity::class.java)
            val gson = Gson()
            val json = gson.toJson(filterData)
            intent.putExtra("Filters", json)
            startActivity(intent)
        }



    }

    var seekBarChangeListener: SeekBar.OnSeekBarChangeListener = object :
        SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar,
            progress: Int,
            fromUser: Boolean
        ) {
            // updated continuously as the user slides the thumb
            distanceTextView.setText("Distance: $progress")
            filterData.radius = progress
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // called when the user first touches the SeekBar
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // called after the user finishes moving the SeekBar
        }
    }


    private fun highlightMoneyButtons(highlightButton: ImageButton){
       fillAllMoneyButtons()
        highlightButton.setBackgroundColor(Color.parseColor("#AFAFAF"))
    }
    private fun fillAllMoneyButtons(){
        price1Button.setBackgroundColor(Color.parseColor("#E0E0E0"))
        price2Button.setBackgroundColor(Color.parseColor("#E0E0E0"))
        price3Button.setBackgroundColor(Color.parseColor("#E0E0E0"))
    }

    /**
     * Gets user preferences based on buttons clicked
     */
    fun getFilterPreferences(){
        // return a FilterData object here
    }
}