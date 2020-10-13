package com.example.diningdecider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.Gson



class MainActivity : AppCompatActivity() {
    private lateinit var tournament : Tournament
    private lateinit var restaurant1: Restaurant
    private lateinit var restaurant2: Restaurant

    private var currentRound: Int =0
    private var currentMatch:Int = 0

    private lateinit var rest1button: ImageButton
    private lateinit var rest2button: ImageButton
    private lateinit var rest1title: TextView
    private lateinit var rest2title: TextView
    private lateinit var backButton: ImageButton
    private lateinit var info1Button: ImageButton
    private lateinit var fav1Button: ImageButton
    private lateinit var info2Button: ImageButton
    private lateinit var fav2Button: ImageButton
    private lateinit var title: TextView
    private lateinit var subtitle:TextView

    //Home Page Button
    private lateinit var findButton: Button
    private lateinit var favoriteButton: ImageButton

    //Filter Page Buttons
    private lateinit var chooseForMeButton: Button
    private lateinit var helpMeButton: Button

    //Favorites Page Buttons
    private lateinit var favBackArrow: ImageButton

    //Random Page Buttons
    private lateinit var dineButton: Button
    private lateinit var newButton: Button

    //Winner Page Buttons
    private lateinit var winnerBackArrow: ImageButton
    private lateinit var finishButton: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

//        TODO: Get filter info from bundle, then filter restaurants to fit those criteria
//        var json = intent.getStringExtra("Restaurant")
//        val gson = Gson()
//        var bundle = gson.fromJson(json, Restaurant::class.java)
//        this.winner = bundle
//        TODO: after filters are applied we should have a list of restaurants, so for now we'll use this:
        tournament = Tournament()


        rest1button = findViewById(R.id.ts_img1)
        rest2button = findViewById(R.id.ts_img2)
        rest1title = findViewById(R.id.ts_name1)
        rest2title = findViewById(R.id.ts_name2)
        backButton = findViewById(R.id.ts_backbutton)
        info1Button = findViewById(R.id.ts_infoButton1)
        fav1Button = findViewById(R.id.ts_favButton1)
        info2Button = findViewById(R.id.ts_infoButton2)
        fav2Button = findViewById(R.id.ts_favButton2)
        title = findViewById(R.id.ts_title)
        subtitle = findViewById(R.id.ts_subtitle)

        //Home Screen Buttons
        favoriteButton = findViewById(R.id.favoritesButton)
        findButton = findViewById(R.id.findbutton)

        //Filter Screen Buttons
        chooseForMeButton = findViewById(R.id.button2)
        helpMeButton = findViewById(R.id.button3)

        //Favorites Screen Buttons
        favBackArrow = findViewById(R.id.favBackArrow)

        //Random Screen Buttons
        dineButton = findViewById(R.id.button3)
        newButton = findViewById(R.id.button2)

        //Winner Screen Buttons
        winnerBackArrow = findViewById(R.id.wp_backbutton)
        finishButton = findViewById(R.id.wp_backbutton)

        getNextRestaurants()


        //Home Screen
        favoriteButton.setOnClickListener {
            intent = Intent(this, FavoritePage::class.java)
            startActivity(intent)
        }

        findButton.setOnClickListener {
            intent = Intent(this, FilterPage::class.java)
            startActivity(intent)
        }


        //Filter Screen - What page is the tournament called?
        chooseForMeButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
            startActivity(intent)
        }

        helpMeButton.setOnClickListener {
            intent = Intent(this, Tournament::class.java)
            startActivity(intent)
        }

        //Favorites Screen
        favBackArrow.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //Random Screen - What to set the dine button to? and edit new button
        dineButton.setOnClickListener {
            intent = Intent(this, WinnerPage::class.java)
            startActivity(intent)
        }
        newButton.setOnClickListener {
            intent = Intent(this, random_page::class.java)
            startActivity(intent)
        }

        //Winner Screen
        winnerBackArrow.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        finishButton.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        //Allisons
        rest1button.setOnClickListener{ view : View ->
            finishMatch(restaurant1)
        }
        rest2button.setOnClickListener{ view : View ->
            finishMatch(restaurant2)
        }
        fav1Button.setOnClickListener{view: View->
            setFavorite(restaurant1, fav1Button)
        }
        fav2Button.setOnClickListener{view: View->
            setFavorite(restaurant2, fav2Button)
        }

    }


    fun finishMatch(winner:Restaurant){
        var r = this.currentRound
        var m = this.currentMatch
        this.tournament.rounds.get(r).winners.add(winner)

        if (m == this.tournament.rounds[r].matches.size -1){
            /**
             * if this is the final match in the final round,
             * show winner screen
             */
            if (r == this.tournament.rounds.size -1){
                val intent = Intent(this, WinnerPage::class.java)
                val gson = Gson()
                val json = gson.toJson(winner)
                intent.putExtra("Restaurant", json)
                startActivity(intent)
            }
            /**
             * if its the final match but there are more rounds,
             * populate and start the new round
             */
            else{
                var winners = this.tournament.rounds[r].winners
                this.currentRound++
                this.tournament.rounds[this.currentRound].populateMatches(winners)
                this.currentMatch =0;
                getNextRestaurants()

            }
        }
        /**
         * if there are more matches in this round, continue
         */
        else {
            this.currentMatch++
            getNextRestaurants()

        }
    }

    /** Sets image and text to new restaurant
     * called by getNextRestaurants
     */
    private fun populateRestaurantsOnScreen(){
        rest1button.setImageResource(this.restaurant1.imageID)
        rest2button.setImageResource(this.restaurant2.imageID)
        rest1title.setText(this.restaurant1.name)
        rest2title.setText(this.restaurant2.name)

    }

    /**
     * Gets new restaurants based on the updated round/match numbers
     */
    private fun getNextRestaurants(){
        var r1 = this.tournament.rounds.get(this.currentRound).matches.get(this.currentMatch).team1
        var r2 = this.tournament.rounds.get(this.currentRound).matches.get(this.currentMatch).team2
        this.restaurant1 = r1
        this.restaurant2 = r2

        populateRestaurantsOnScreen()

    }
    private fun setFavorite(restaurant:Restaurant, button : ImageButton){
        if (restaurant.favorite == true){
            restaurant.favorite = false
            button.setImageResource(R.drawable.heart)
        }
        else if (restaurant.favorite == false){
            restaurant.favorite = true
            button.setImageResource(R.drawable.heartfilled)
        }

    }



}