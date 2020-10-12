package com.example.diningdecider

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.google.gson.Gson

private const val request_code=0


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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tournament_chooser)

//        TODO: Get filter info from bundle, then filter restaurants to fit those criteria
//        if (savedInstanceState != null){
//            val savedScore : IntArray? = savedInstanceState.getIntArray(key_score)
//            if (savedScore != null) {
//                score.team1_score = savedScore[0]
//            }
//            if (savedScore != null) {
//                score.team2_score = savedScore[1]
//            }
//
//        }
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


        getNextRestaurants()

        rest1button.setOnClickListener{ view : View ->
            finishMatch(restaurant1)

        }
        rest2button.setOnClickListener{ view : View ->
            finishMatch(restaurant2)

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

    private fun populateRestaurantsOnScreen(){
//        var resources = getResources()
        rest1button.setImageResource(this.restaurant1.imageID)
        rest2button.setImageResource(this.restaurant2.imageID)
        rest1title.setText(this.restaurant1.name)
        rest2title.setText(this.restaurant2.name)

    }
    private fun getNextRestaurants(){
        var r1 = this.tournament.rounds.get(this.currentRound).matches.get(this.currentMatch).team1
        var r2 = this.tournament.rounds.get(this.currentRound).matches.get(this.currentMatch).team2
        this.restaurant1 = r1
        this.restaurant2 = r2

        populateRestaurantsOnScreen()

    }

}