package com.example.diningdecider;

public class Match {
    Restaurant team1;
    Restaurant team2;
    Restaurant winner;

    public Match(Restaurant team1, Restaurant team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    /**
     * If 1 is entered return team 1
     * If 2 is entered return team 2
     * @param input
     * @return
     */
//    public Restaurant getMatchWinner(String input){
//        if (input.equals("1")){
//            return this.team1;
//        }
//        if (input.equals("2")){
//            return this.team2;
//        }
//        else return null;
//    }
}
