package com.example.diningdecider;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TournamentsTest {

    ArrayList<FoodType> testtypes = new ArrayList<>();

    FilterData testfilter = new FilterData();
    Tournament testTournament = new Tournament(testfilter);

    @Test
    public void testMakeTournament() {
        testTournament.makeRestaurants();
        int restaurntsSize = testTournament.restaurants.size();

        assertEquals(restaurntsSize, 47);
    }

    @Test
    public void testFilterTournament() {
        testtypes.add(FoodType.FastFood);
        testfilter.radius = 20;
        testfilter.price = Price.LOW;
        testfilter.foodTypes = testtypes;
        testTournament.makeRestaurants();
//        System.out.println(testTournament.restaurants.size());
        testTournament.filterRestaurants(testfilter);
//        System.out.println(testTournament.restaurants.size());
        assertEquals(testTournament.restaurants.size(), 7);
    }

    @Test
    public void testIsGoodForTournament() {
        testTournament.makeRestaurants();
        boolean check = testTournament.isGoodForTournament(testTournament.restaurants.size());
        boolean check2 = testTournament.isGoodForTournament(1);
        assertTrue(String.valueOf(check), true);
        assertFalse(String.valueOf(check2), false);
    }

    @Test
    public void testIsNotGoodForTournament() {
        testTournament.makeRestaurants();
        boolean check2 = testTournament.isGoodForTournament(1);
        assertFalse(String.valueOf(check2), false);
    }

    @Test
    public void testInitializeTournamnet() {
        testTournament.makeRestaurants();
        testTournament.prepForTournament();
//        testTournament.numRounds = 8;
//        testTournament.initializeTournament();
//        System.out.println(testTournament.rounds.get(1));
        assertNotEquals(testTournament.rounds.get(0), null);
    }




}
