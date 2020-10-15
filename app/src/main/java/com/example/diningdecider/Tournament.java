package com.example.diningdecider;

import java.util.ArrayList;
import java.util.Random;
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
        ArrayList<Restaurant> eligibleRestaurants = new ArrayList<>();
//        fuck this figure it out later


    }



    public Restaurant getNewRandomRestaurant(){
        Random rand = new Random();
        int r = rand.nextInt(this.restaurants.size()-1);
        return this.restaurants.get(r);
    }


    /**
     * creates dummy data for the tournament
     */
    public void makeRestaurants(){
        ArrayList a = new ArrayList<FoodType>();
        a.add(FoodType.FastFood);
        ArrayList b = new ArrayList<FoodType>();
        b.add(FoodType.Burgers);
        ArrayList c = new ArrayList<FoodType>();
        c.add(FoodType.Mexican);
        ArrayList d = new ArrayList<FoodType>();
        ArrayList e = new ArrayList<FoodType>();
        e.add(FoodType.Pub);
        ArrayList f = new ArrayList<FoodType>();
        f.add(FoodType.Seafood);

        Restaurant chickfila = new Restaurant("Chick Fil-a", "80 Gold Star Blvd","508-459-9852",Price.LOW, a , R.drawable.chickfila);
        Restaurant chipotle = new Restaurant("Chipotle","49 Park Ave Suite 1", "774-420-7718", Price.LOW, a, R.drawable.chipotle);
        Restaurant mcdonalds = new Restaurant("McDonalds", "465 Shrewsbury St", "508-757-9996", Price.LOW, a, R.drawable.mcdonalds);
        Restaurant tacobell = new Restaurant("Taco Bell", "463 Lincoln St", "774-701-1580", Price.LOW, a, R.drawable.tacobell);
        Restaurant burgerking = new Restaurant("Burger King", "163 Madison St", "508-752-7952", Price.LOW, a, R.drawable.burgerking);
        Restaurant thefix = new Restaurant("The Fix Burger Bar", "108 Grove St", "774-823-3327", Price.MEDIUM, b, R.drawable.fix);
        Restaurant mezcal = new Restaurant("MezCal Tequila Cantina", "30 Major Taylor Blvd", "508-926-8308", Price.MEDIUM, c, R.drawable.mezcal);
        Restaurant panera = new Restaurant("Panera Bread", "120 Gold Star Blvd", "508-856-7007", Price.MEDIUM, d, R.drawable.panera);
//        Restaurant nu = new Restaurant("Nu Kitchen", "335 Chandler St", "508-926-8800", Price.MEDIUM, d, R.drawable.nu);
//        Restaurant boynton = new Restaurant("The Boynton Restaurant & Spirits", "117 Highland St", "508-756-8458", Price.MEDIUM,e, R.drawable.boynton );
//        Restaurant sole = new Restaurant("The Sole Proprietor", "118 Highland St", "508-798-3474", Price.HIGH,f, R.drawable.sole );
//        Restaurant oakbt = new Restaurant("Oak Barrel Tavern", "229 Grove St", "508-755-8047", Price.MEDIUM, e, R.drawable.oak);



        restaurants = new ArrayList<>();
        restaurants.add(chickfila);
        restaurants.add(chipotle);
        restaurants.add(mcdonalds);
        restaurants.add(tacobell);
        restaurants.add(burgerking);
        restaurants.add(thefix);
        restaurants.add(mezcal);
        restaurants.add(panera);
//        restaurants.add(nu);


        System.out.println("There are " + numTeams + " teams in the tournament");
    }

}
