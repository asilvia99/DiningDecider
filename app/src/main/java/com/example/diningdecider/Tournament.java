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
        filterRestaurants(filters);

    }

    /**
     * Creates tournament with appropriate num of teams and sets up round 1
     * if the input number of restaurants is not even for a tournament
     * remove the last restaurants on the list until it is
     */
    public void prepForTournament(){
        while(!isGoodForTournament(restaurants.size())) {
            if (restaurants.size() == 0){
                break;
            }
            int i = restaurants.size() -1;
            restaurants.remove(i);
        }
        this.numTeams = restaurants.size();

        this.initializeTournament();

        System.out.println("There are " + restaurants.size() + " restarurants");
        for (Restaurant r: restaurants){
            System.out.println(r.name);
        }
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

        int size = restaurants.size();
        for(int i = 0; i < (size-1); i++) {

            Restaurant current = restaurants.get(i);

            //If there is no food type selected in filter
            if(current.distance <= filters.radius && (current.pricelevel.equals(filters.price) || filters.price.equals(Price.NONE)) && filters.foodTypes.equals(FoodType.None)) {
                eligibleRestaurants.add(current);
            }
            // if all criteria are picked
            else if(current.distance <= filters.radius && (current.pricelevel.equals(filters.price) || filters.price.equals(Price.NONE)) && filters.foodTypes.contains(current.foodTypes)) {
                eligibleRestaurants.add(current);
            }


        }
        this.restaurants = eligibleRestaurants;

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

        Restaurant chickfila = new Restaurant("Chick Fil-a", "80 Gold Star Blvd","508-459-9852",15,Price.LOW, FoodType.FastFood , R.drawable.chickfila);
        Restaurant chipotle = new Restaurant("Chipotle","49 Park Ave Suite 1", "774-420-7718", 15, Price.LOW, FoodType.FastFood, R.drawable.chipotle);
        Restaurant mcdonalds = new Restaurant("McDonalds", "465 Shrewsbury St", "508-757-9996", 15, Price.LOW, FoodType.FastFood, R.drawable.mcdonalds);
        Restaurant tacobell = new Restaurant("Taco Bell", "463 Lincoln St", "774-701-1580", 15, Price.LOW, FoodType.FastFood, R.drawable.tacobell);
        Restaurant burgerking = new Restaurant("Burger King", "163 Madison St", "508-752-7952", 15, Price.LOW, FoodType.FastFood, R.drawable.burgerking);
        Restaurant thefix = new Restaurant("The Fix Burger Bar", "108 Grove St", "774-823-3327", 15, Price.MEDIUM, FoodType.Burgers, R.drawable.fix);
        Restaurant mezcal = new Restaurant("MezCal Tequila Cantina", "30 Major Taylor Blvd", "508-926-8308", 15, Price.MEDIUM, FoodType.Mexican, R.drawable.mezcal);
        Restaurant panera = new Restaurant("Panera Bread", "120 Gold Star Blvd", "508-856-7007", 15, Price.MEDIUM, null, R.drawable.panera);
        Restaurant nu = new Restaurant("Nu Kitchen", "335 Chandler St", "508-926-8800", 15, Price.MEDIUM, null, R.drawable.nu);
        Restaurant boynton = new Restaurant("The Boynton Restaurant & Spirits", "117 Highland St", "508-756-8458", 15, Price.MEDIUM, FoodType.Pub, R.drawable.boynton );
        Restaurant sole = new Restaurant("The Sole Proprietor", "118 Highland St", "508-798-3474", 15, Price.HIGH, FoodType.Seafood, R.drawable.sole );
        Restaurant oakbt = new Restaurant("Oak Barrel Tavern", "229 Grove St", "508-755-8047", 15, Price.MEDIUM, FoodType.Pub, R.drawable.oak);
        Restaurant elpatron = new Restaurant("El Patron", "192 Harding St, Worcester, MA", "(508) 757-8000", 5, Price.MEDIUM, FoodType.Mexican, R.drawable.elpatron);
        Restaurant mexicali = new Restaurant("Mexicali Cantina Grill", "225 Shrewsbury St, Worcester, MA", "(508) 926-8811", 10, Price.MEDIUM, FoodType.Mexican, R.drawable.mexicali);
        Restaurant solofmexico = new Restaurant("Sol of Mexico", "538 Pleasant St, Worcester, MA", "(508) 756 2660", 1, Price.LOW, FoodType.Mexican, R.drawable.solofmex);
        Restaurant tacosmexico = new Restaurant("Tacos Mexico", "7 Neponset St, Worcester, MA", "(508) 579-3283", 16, Price.LOW, FoodType.Mexican, R.drawable.tacosmexico);
        Restaurant pepes = new Restaurant("Pepe's Taqueria", "645 Chandler St, Worcester, MA", "(508) 796-5796", 10, Price.MEDIUM, FoodType.Mexican, R.drawable.pepes);
        Restaurant dostacos = new Restaurant("Dos Tacos Cafe", "296 Pleasant St, Worcester, MA", "(508) 767-0075", 2, Price.LOW, FoodType.Mexican, R.drawable.dostacos);
        Restaurant moes = new Restaurant("Moes Southwest Grill", "3 Stafford St, Worcester, MA", "(508) 459-6060", 10, Price.LOW, FoodType.Mexican, R.drawable.moes);
        Restaurant wendys = new Restaurant("Wendy's", "492 Lincoln St, Worcester, MA", "(508) 852-2120", 16, Price.LOW, FoodType.FastFood, R.drawable.wendys);
        Restaurant kfc = new Restaurant("Kentucky Fried Chicken", "418 Park Ave, Worcester, MA", "(508) 755-5271", 7, Price.LOW, FoodType.FastFood, R.drawable.kfc);
        Restaurant fiveguys = new Restaurant("Five Guys", "525 Lincoln St, Worcester, MA", "(508) 853-2000", 15, Price.HIGH, FoodType.FastFood, R.drawable.fiveguys);


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
        restaurants.add(elpatron);
        restaurants.add(mexicali);
        restaurants.add(solofmexico);
        restaurants.add(tacosmexico);
        restaurants.add(pepes);
        restaurants.add(dostacos);
        restaurants.add(moes);
        restaurants.add(wendys);
        restaurants.add(kfc);
        restaurants.add(fiveguys);





        System.out.println("There are " + numTeams + " teams in the tournament");
    }

}
