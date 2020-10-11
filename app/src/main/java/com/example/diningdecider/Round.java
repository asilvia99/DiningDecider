package com.example.diningdecider;
import java.util.ArrayList;
import java.util.Scanner;

public class Round {
    ArrayList<Match> matches;
    int numMatches;
    ArrayList<Restaurant> winners;


    public Round() {
        this.matches = new ArrayList<>();
        this.winners = new ArrayList<>();
        this.numMatches =0;
    }

    /**
     * Goes through each match in the round,
     * waits for a 1 or 2 response and assigns the winner
     *
     * @param roundnumber the index of the round in tournament
     */
//    public void runRound(int roundnumber){
//        Scanner keyboard = new Scanner(System.in);
//
//        int mnum = 1;
//        System.out.println("Round " + roundnumber);
//        for (Match m: this.matches){
//            System.out.println("Match " + mnum + ": " + m.team1.name + " vs " +  m.team2.name);
//            String input = keyboard.next();
//            this.winners.add(m.getMatchWinner(input));
//        }
//
//        System.out.println("Round " + roundnumber +" Winners: ");
//        for (Restaurant r: this.winners){
//            System.out.print(r.name + ", ");
//        }
//    }

    /**
     * Creates match objects for a round with restaurants given
     * @param restaurants
     */
    public void populateMatches(ArrayList<Restaurant> restaurants){
        for (int i = 0; i < restaurants.size(); i= i+2){
            Match m = new Match(restaurants.get(i), restaurants.get(i+1));
            matches.add(m);
            numMatches++;
        }
    }
}
