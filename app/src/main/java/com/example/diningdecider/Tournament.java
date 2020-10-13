package com.example.diningdecider;

import java.util.ArrayList;
import java.util.Scanner;

public class Tournament {
    ArrayList<Restaurant> restaurants;
    int numTeams;
    double numRounds;
    ArrayList<Round> rounds;

    /**
     * Should take in list of filters and filter the existing restaurants
     * but for now, we're using "makeRestaurants"
     */
    public Tournament(FilterData filters){
        makeRestaurants();

    }

    /**
     * Creates tournament with appropriate num of teams and sets up round 1
     * if the input number of restaurants is not even for a tournament
     * remove the last restaurants on the list until it is
     */
    public void prepForTournament(){
        while(!isGoodForTournament(restaurants.size())) {
            int i = restaurants.size() -1;
            restaurants.remove(i);
        }
        this.restaurants = restaurants;
        this.numTeams = restaurants.size();

        this.initializeTournament();
    }


    /**
     * Checks if the number of restaurants is good for an even tournament
     * @param teams
     * @return
     */
    private boolean isGoodForTournament(int teams){
//        logbase2(#ofteams) must be equal to a whole number
//        for example, 4, 8, or 16 are appropriate #s of teams
//        math.log uses base e, so dividing by log(base e) of 2 will give u
//        log (base 2) of teams
        this.numRounds = Math.log(teams)/Math.log(2);
        if (numRounds *10%10 == 0){
            return true;
        }
        else return false;
    }


    /**
     * Creates round objects and populates first round with matches
     */
    public void initializeTournament(){
        this.rounds = new ArrayList<Round>();
        for (int i = 1; i <= (int) numRounds; i++){
            this.rounds.add(new Round());
        }
        this.rounds.get(0).populateMatches(this.restaurants);
    }



    public void filterRestaurants(FilterData filters){
        for (Restaurant r: this.restaurants){
            if (r.pricelevel != filters.price){
                this.restaurants.remove(r);
                break;
            }
//            for (FoodType f:filters.foodTypes){
//                Boolean match = false;
//                if (f == r.)
//            }
        }

    }


    /**
     * creates dummy data for the tournament
     */
    public void makeRestaurants(){
        ArrayList a = new ArrayList<FoodType>();
        a.add(FoodType.FastFood);
        ArrayList b = new ArrayList<FoodType>();
        a.add(FoodType.Burgers);
        ArrayList c = new ArrayList<FoodType>();
        a.add(FoodType.Mexican);
        ArrayList d = new ArrayList<FoodType>();

        Restaurant chickfila = new Restaurant("Chick Fil-a", "1 st",1,Price.LOW, a , R.drawable.chickfila);
        Restaurant chipotle = new Restaurant("Chipotle","1 st", 2, Price.LOW, a, R.drawable.chipotle);
        Restaurant mcdonalds = new Restaurant("McDonalds", "1 st", 3, Price.LOW, a, R.drawable.mcdonalds);
        Restaurant tacobell = new Restaurant("Taco Bell", "1 st", 4, Price.LOW, a, R.drawable.tacobell);
        Restaurant burgerking = new Restaurant("Burger King", "1 st", 5, Price.LOW, a, R.drawable.burgerking);
        Restaurant thefix = new Restaurant("The Fix Burger Bar", "1st", 6, Price.MEDIUM, b, R.drawable.fix);
        Restaurant mezcal = new Restaurant("MezCal Cantina", "1 st", 7, Price.MEDIUM, c, R.drawable.mezcal);
        Restaurant panera = new Restaurant("Panera Bread", "1 st", 8, Price.MEDIUM, d, R.drawable.panera);
        Restaurant nu = new Restaurant("Nu Kitchen", "1st", 9, Price.MEDIUM, d, R.drawable.panera);



        restaurants = new ArrayList<>();
        restaurants.add(chickfila);
        restaurants.add(chipotle);
        restaurants.add(mcdonalds);
        restaurants.add(tacobell);
        restaurants.add(burgerking);
        restaurants.add(thefix);
        restaurants.add(mezcal);
        restaurants.add(panera);
        restaurants.add(nu);


        System.out.println("There are " + numTeams + " teams in the tournament");
    }

}
