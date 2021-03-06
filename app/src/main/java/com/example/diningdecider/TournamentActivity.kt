package com.example.diningdecider

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.Gson

val FavArrayList = ArrayList<Restaurant>()

class TournamentActivity : AppCompatActivity() {
    private lateinit var tournament : Tournament
    private var favsList: ArrayList<Restaurant> = ArrayList()

    private lateinit var filters: FilterData
    private lateinit var restaurant1: Restaurant
    private lateinit var restaurant2: Restaurant

    private var currentRound: Int =0
    private var currentMatch:Int = 0

    private lateinit var rest1button: ImageButton
    private lateinit var rest2button: ImageButton
    private lateinit var rest1title: TextView
    private lateinit var rest2title: TextView
    private lateinit var backButton: ImageButton
    private lateinit var fav1Button: ImageButton
    private lateinit var fav2Button: ImageButton
    private lateinit var title: TextView
    private lateinit var subtitle:TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tournament_chooser)

        /**
         * Get filters from intent extra
         */
        var json = intent.getStringExtra("Filters")
        val gson = Gson()
        var bundle = gson.fromJson(json, FilterData::class.java)
        this.filters = bundle
        /**
         * pass filters received from filters page into the tournament so it can filter the restaurants
         */
        tournament = Tournament(this.filters)
        tournament.prepForTournament()


        rest1button = findViewById(R.id.ts_img1)
        rest2button = findViewById(R.id.ts_img2)
        rest1title = findViewById(R.id.ts_name1)
        rest2title = findViewById(R.id.ts_name2)
        backButton = findViewById(R.id.ts_backbutton)
        fav1Button = findViewById(R.id.ts_favButton1)
        fav2Button = findViewById(R.id.ts_favButton2)
        title = findViewById(R.id.ts_title)
        subtitle = findViewById(R.id.ts_subtitle)



        getNextRestaurants()


        //Allisons
        rest1button.setOnClickListener{ view : View ->
            finishMatch(restaurant1)
        }
        rest2button.setOnClickListener{ view : View ->
            finishMatch(restaurant2)
        }
        fav1Button.setOnClickListener{view: View->
            setFavorite(restaurant1, fav1Button)
            FavArrayList.add(restaurant1)

        }
        fav2Button.setOnClickListener{view: View->
            setFavorite(restaurant2, fav2Button)
            FavArrayList.add(restaurant2)

        }
        backButton.setOnClickListener {
            intent = Intent(this, HomePage::class.java)
            startActivity(intent)
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
//                saveFavorites()
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
        setInitFavButtons(restaurant1, fav1Button)
        setInitFavButtons(restaurant2, fav2Button)

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
            if (favsList.contains(restaurant)){
                favsList.remove(restaurant)
            }
            button.setImageResource(R.drawable.heart)

        }
        else if (restaurant.favorite == false){
            restaurant.favorite = true
            favsList.add(restaurant)
            button.setImageResource(R.drawable.heartfilled)
        }

    }
    private fun setInitFavButtons(restaurant: Restaurant, button: ImageButton){
        if (restaurant.favorite == true){
            button.setImageResource(R.drawable.heartfilled)
        }
        else if (restaurant.favorite == false){
            button.setImageResource(R.drawable.heart)
        }
    }

    private fun saveFavorites(){
        var sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        var gson = Gson()
        var json = gson.toJson(favsList)
        editor.putString("favs list", json)
        editor.apply()
    }





}