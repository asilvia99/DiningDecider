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

        System.out.println("There are " + restaurants.size() + " restaurants");
        for (Restaurant r: restaurants){
            System.out.println(r.name);
        }
    }


    /**
     * Checks if the number of restaurants is good for an even tournament
     * @param teams
     * @return
     */
    public boolean isGoodForTournament(int teams){
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



    public int getNewRandomRestaurant(){
        Random rand = new Random();
        int r = rand.nextInt(this.restaurants.size()-1);
        return r;
    }



    /**
     * creates dummy data for the tournament
     */
    public void makeRestaurants(){

        Restaurant chickfila = new Restaurant("Chick Fil-a", "80 Gold Star Blvd, Worcester, MA","508-459-9852",15,Price.LOW, FoodType.FastFood , R.drawable.chickfila);
        Restaurant chipotle = new Restaurant("Chipotle","49 Park Ave Suite 1, Worcester, MA", "774-420-7718", 15, Price.LOW, FoodType.FastFood, R.drawable.chipotle);
        Restaurant mcdonalds = new Restaurant("McDonalds", "465 Shrewsbury St, Worcester, MA", "508-757-9996", 15, Price.LOW, FoodType.FastFood, R.drawable.mcdonalds);
        Restaurant tacobell = new Restaurant("Taco Bell", "463 Lincoln St, Worcester, MA", "774-701-1580", 15, Price.LOW, FoodType.FastFood, R.drawable.tacobell);
        Restaurant burgerking = new Restaurant("Burger King", "163 Madison St, Worcester, MA", "508-752-7952", 15, Price.LOW, FoodType.FastFood, R.drawable.burgerking);
        Restaurant thefix = new Restaurant("The Fix Burger Bar", "108 Grove St, Worcester, MA", "774-823-3327", 15, Price.MEDIUM, FoodType.Burgers, R.drawable.fix);
        Restaurant mezcal = new Restaurant("MezCal Tequila Cantina", "30 Major Taylor Blvd, Worcester, MA", "508-926-8308", 15, Price.MEDIUM, FoodType.Mexican, R.drawable.mezcal);
        Restaurant panera = new Restaurant("Panera Bread", "120 Gold Star Blvd, Worcester, MA", "508-856-7007", 15, Price.MEDIUM, null, R.drawable.panera);
        Restaurant nu = new Restaurant("Nu Kitchen", "335 Chandler St, Worcester, MA", "508-926-8800", 15, Price.MEDIUM, null, R.drawable.nu);
        Restaurant boynton = new Restaurant("The Boynton Restaurant & Spirits", "117 Highland St, Worcester, MA", "508-756-8458", 15, Price.MEDIUM, FoodType.Pub, R.drawable.boynton );
        Restaurant sole = new Restaurant("The Sole Proprietor", "118 Highland St, Worcester, MA", "508-798-3474", 15, Price.HIGH, FoodType.Seafood, R.drawable.sole );
        Restaurant oakbt = new Restaurant("Oak Barrel Tavern", "229 Grove St, Worcester, MA", "508-755-8047", 15, Price.MEDIUM, FoodType.Pub, R.drawable.oak);
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
        //Chinese Food
        Restaurant wenhing = new Restaurant("Wen Hing", "106 Lincoln St, Worcester, MA", "(508) 753-6399", 12, Price.LOW, FoodType.Chinese, R.drawable.wenhing);
        Restaurant ddynasty = new Restaurant("Dragon Dynasty", "104 Highland St, Worcester, MA", "(508) 755-5588", 5, Price.LOW, FoodType.Chinese, R.drawable.ddynasty);
        Restaurant minghouse = new Restaurant("Ming House", "217 Chandler St A, Worcester, MA", "(508) 756-6888", 10, Price.MEDIUM, FoodType.Chinese, R.drawable.minghouse);
        Restaurant greatwall = new Restaurant("Great Wall", "521 Main St, Worcester, MA", "(508) 799-0803", 15, Price.MEDIUM, FoodType.Chinese, R.drawable.greatwall);
        Restaurant sakebomb = new Restaurant("Sake Bomb Bistro", "258 Park Ave, Worcester, MA", "(508) 754-2426", 20, Price.MEDIUM, FoodType.Chinese, R.drawable.sakebomb);
        Restaurant kingchef = new Restaurant("King Chef", "205 Chandler St, Worcester, MA", "(508) 767-0209", 10, Price.LOW, FoodType.Chinese, R.drawable.kingchef);

        //Breakfast Food
        Restaurant dennys = new Restaurant("Denny's", "494 Lincoln St, Worcester, MA", "(508) 852-8448", 20, Price.LOW, FoodType.Breakfast, R.drawable.dennys);
        Restaurant dunks = new Restaurant("Dunkin'", "104 Belmont St, Worcester, MA", "(508) 756-3275", 5, Price.LOW, FoodType.Breakfast, R.drawable.dunks);
        Restaurant bean = new Restaurant("Bean Counter Bakery Cafe", "113 Highland St, Worcester, MA", "(508) 749-5700", 5, Price.MEDIUM, FoodType.Breakfast, R.drawable.bean);
        Restaurant alteas = new Restaurant("Altea's Eatery", "259 Park Ave, Worcester, MA", "(508) 767-1639", 5, Price.MEDIUM, FoodType.Breakfast, R.drawable.alteas);
        Restaurant bagel = new Restaurant("Bagel Time", "194 Park Ave, Worcester, MA", "(508) 798-0440", 5, Price.LOW, FoodType.Breakfast, R.drawable.bagel);
        Restaurant ralphs = new Restaurant("Ralph's Diner", "148 Grove St, Worcester, MA", "(508) 753-9543", 5, Price.MEDIUM, FoodType.Breakfast, R.drawable.ralphs);


        Restaurant redcrab = new Restaurant("Red Crab Juicy Seafood", "1269 Main St, Worcester, MA", "(508) 755-8188", 16, Price.HIGH, FoodType.Seafood, R.drawable.redcrab);
        Restaurant herbies = new Restaurant("Herbie's", "1028 Southbridge St, Worcester, MA", "(508) 757-5083", 10, Price.MEDIUM, FoodType.Seafood, R.drawable.herbies);
        Restaurant philips = new Restaurant("Philips' E&N Seafood", "264 Grafton St, Worcester, MA", "(508) 345-7235", 2, Price.HIGH, FoodType.Seafood, R.drawable.philips);
        Restaurant eggroll = new Restaurant("Eggroll Lady and Fish Shack", "609 West Boylston St, Worcester, MA", "(508) 755-4451", 10, Price.LOW, FoodType.Seafood, R.drawable.egroll);
        Restaurant hook = new Restaurant("Hook & Reel Cajun Seafood and Bar", "539 Lincoln St, Worcester, MA", "(508) 854-2999", 16, Price.HIGH, FoodType.Seafood, R.drawable.hook);
        Restaurant foley = new Restaurant("Foley & Son Fish and Chips", "274 Plantation St, Worcester, MA", "(508) 756-6492", 7, Price.LOW, FoodType.Seafood, R.drawable.foley);
        Restaurant baba = new Restaurant("Baba Sushi", "309 Park Ave, Worcester, MA", "(508) 752-8822", 15, Price.HIGH, FoodType.Seafood, R.drawable.baba);


        Restaurant quinns = new Restaurant("Quinns Irish Pub", "715 W Boylston St, Worcester, MA", "(508) 459-2025", 16, Price.MEDIUM, FoodType.Pub, R.drawable.quinns);
        Restaurant hangover = new Restaurant("The Hangover Pub", "102 Green St, Worcester, MA", "(508) 459-1511", 10, Price.MEDIUM, FoodType.Pub, R.drawable.hangover);
        Restaurant funky = new Restaurant("Funky Murphy's", "305 Shrewsbury St, Worcester, MA", "(508) 753-2995", 2, Price.HIGH, FoodType.Pub, R.drawable.funky);
        Restaurant gallaghers = new Restaurant("Gallaghers", "97 West Boylston St, Worcester, MA", "(508) 755-4451", 10, Price.LOW, FoodType.Pub, R.drawable.gall);
        Restaurant leits = new Restaurant("Leitrum's Pub", "265 Park Ave, Worcester, MA", "(508) 854-2999", 16, Price.LOW, FoodType.Pub, R.drawable.leits);
        Restaurant oconnors = new Restaurant("O'Connor's Restaurant and Bar", "1160 W Boylston St, Worcester, MA", "(508) 853-0789", 7, Price.LOW, FoodType.Pub, R.drawable.ocon);



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
        restaurants.add(wenhing);
        restaurants.add(ddynasty);
        restaurants.add(minghouse);
        restaurants.add(greatwall);
        restaurants.add(sakebomb);
        restaurants.add(kingchef);
        restaurants.add(dennys);
        restaurants.add(dunks);
        restaurants.add(boynton);
        restaurants.add(sole);
        restaurants.add(oakbt);
        restaurants.add(alteas);
        restaurants.add(bean);
        restaurants.add(bagel);
        restaurants.add(ralphs);



        restaurants.add(redcrab);
        restaurants.add(herbies);
        restaurants.add(philips);
        restaurants.add(eggroll);
        restaurants.add(hook);
        restaurants.add(foley);
        restaurants.add(baba);

        restaurants.add(quinns);
        restaurants.add(hangover);
        restaurants.add(oconnors);
        restaurants.add(leits);
        restaurants.add(funky);
        restaurants.add(gallaghers);



        System.out.println("There are " + numTeams + " teams in the tournament");
    }

}
