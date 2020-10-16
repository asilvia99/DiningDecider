package com.example.diningdecider

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

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
    private lateinit var indianCheckBox: CheckBox
    private lateinit var mexicanCheckBox: CheckBox
    private lateinit var thaiCheckBox: CheckBox
    private lateinit var backButton : ImageButton


    private var radius: Int = 10
    private var price : Price = Price.NONE
    private var foodTypes : ArrayList<FoodType> = arrayListOf()
    private var filterData : FilterData = FilterData(radius, price, foodTypes)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

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
        indianCheckBox = findViewById(R.id.indianCheckBox)
        mexicanCheckBox = findViewById(R.id.mexicanCheckBox)
        thaiCheckBox = findViewById(R.id.thaiCheckBox)

        distanceTextView = findViewById(R.id.distance_textView)
        distanceTextView.setText("Distance: 10")

        backButton.setOnClickListener{
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        // Filter for only getting restaurants with low price
        price1Button.setOnClickListener {
            price = Price.LOW
        }

        // Filter for only getting restaurants with medium price
        price2Button.setOnClickListener {
            price = Price.MEDIUM
        }

        // Filter for only getting restaurants with high price
        price3Button.setOnClickListener {
            price = Price.HIGH
        }

        fastfoodCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.FastFood)
            }
            else {
                foodTypes.remove(FoodType.FastFood)
            }
        }

        indianCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.Indian)
            }
            else {
                foodTypes.remove(FoodType.Indian)
            }
        }

        mexicanCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.Mexican)
            }
            else {
                foodTypes.remove(FoodType.Mexican)
            }
        }

        breakfastCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.Breakfast)
            }
            else {
                foodTypes.remove(FoodType.Breakfast)
            }
        }

        chineseCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.Chinese)
            }
            else {
                foodTypes.remove(FoodType.Chinese)
            }
        }

        thaiCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                foodTypes.add(FoodType.Thai)
            }
            else {
                foodTypes.remove(FoodType.Thai)
            }
        }


        //Filter Screen - What page is the tournament called?
        chooseForMeButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
//            var filter =  getFilterPreferences
//            val gson = Gson()
//            val json = gson.toJson(filter)
//            intent.putExtra("Filters", json)
            startActivity(intent)
        }

        helpMeButton.setOnClickListener {
            filterData.radius = radius
            filterData.price = price
            filterData.foodTypes = foodTypes
            Log.d(TAG, "Here it is ${filterData.radius} ${filterData.price} ${filterData.foodTypes}")
            intent = Intent(this, TournamentActivity::class.java)
//            var filter =  getFilterPreferences
//            val gson = Gson()
//            val json = gson.toJson(filter)
//            intent.putExtra("Filters", json)
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
            radius = progress
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
            // called when the user first touches the SeekBar
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
            // called after the user finishes moving the SeekBar
        }
    }

    /**
     * Gets user preferences based on buttons clicked
     */
    fun getFilterPreferences(){
        // return a FilterData object here
    }
}