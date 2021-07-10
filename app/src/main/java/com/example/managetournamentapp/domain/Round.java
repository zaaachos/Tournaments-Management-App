package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Round {

    private int teamsNumber,teamsPerGroup;
    private boolean isKnockout;
    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<LocalDate> dates;
    private ArrayList<Team> teams;

    /**
     * the full constructor of the round
     * @param teamsNumber the number of teams in the round
     * @param isKnockout true if the round is knockout (not a group stage)
     * @param dates the arraylist of the dates for each game
     */
    public Round(int teamsNumber, boolean isKnockout , ArrayList<LocalDate> dates) {
        this.teamsNumber = teamsNumber;
        this.isKnockout = isKnockout;
        this.dates = dates;
        initGroups();
    }

    /**
     * initialize the groups of the round,
     * assign a list of game dates to each group
     */
    public void initGroups(){
        teamsPerGroup = 4;
        if (isKnockout){
            teamsPerGroup = 2;
        }
        int groupsNumber = teamsNumber / teamsPerGroup;
        int groupMatches = dates.size()/groupsNumber;
        int firstIndex = 0;
        int lastIndex = groupMatches;
        for (int i=0; i< groupsNumber ; i++){
            groups.add(new Group(isKnockout, new ArrayList<>(dates.subList(firstIndex, lastIndex)) ));
            firstIndex = lastIndex;
            lastIndex += groupMatches;
        }
    }

    /**
     *  assign the teams to each group
     * @param teams the arraylist of the teams in this round
     */
    public void setup(ArrayList<Team> teams){
        this.teams = teams;
        int firstIndex = 0;
        int lastIndex = teamsPerGroup;
        for (int i=0; i< groups.size() ; i++){
            groups.get(i).addTeams( new ArrayList<>(teams.subList(firstIndex,lastIndex)) );
            firstIndex = lastIndex;
            lastIndex += teamsPerGroup;
        }
    }

    /**
     * find if all the games in this round are completed
     * @return true of the round is finished
     */
    public boolean allGamesFinished() {
        for (Group group : groups) {
            if (!group.allGamesFinished())
                return false;
        }
        return true;
    }

    /**
     * get the teams that participate in this round
     * @return the arraylist of the teams in this round
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * find which teams are promoted (winners of this round)
     * there is 1 winner if the round is knockout, or else
     * 2 winners if the round is a "group stage"
     * @return the arraylist of the winners in this round
     */
    public ArrayList<Team> getRoundWinners(){
        ArrayList<Team> winners = new ArrayList<>();
        if (!allGamesFinished()){
            return winners;
        }
        for (Group group : groups) {
            group.refreshRankings();
            winners.addAll( group.getGroupWinners() );
        }
        return winners;
    }

    /**
     * get all the dates for the games of the round
     * @return the date for each game of this round
     */
    public ArrayList<LocalDate> getDates() {
        return new ArrayList<>(dates);
    }

    /**
     * get the groups of this round
     * if this is a knockout round, each group consists
     * of 2 teams, else each groups consists of 4 teams
     * @return get the groups of this round
     */
    public ArrayList<Group> getGroups() {
        return new ArrayList<>(groups);
    }

    /**
     *  find how many teams are playing in this round
     * @return the number of teams that participate in this round
     */
    public int getTeamsNumber() {
        return teamsNumber;
    }

    /**
     *find how many teams participate each group of this round
     * if this is a knockout round, each group consists
     * of 2 teams, else each groups consists of 4 teams
     * @return the number of teams in a group
     */
    public int getTeamsPerGroup() {
        return teamsPerGroup;
    }


    /**
     *check if two rounds are equal
     * @param other is the other round
     * @return if this round is equal to the other round
     */
    public boolean equals(Object other) {
        if (this == other){
            return true;
        }
        if (other instanceof Round) {
            Round otherRound = (Round) other;
            if (teamsNumber == otherRound.teamsNumber && teamsPerGroup == otherRound.teamsPerGroup
                    && groups.equals(otherRound.groups) && isKnockout == otherRound.isKnockout
                        && dates.equals(otherRound.dates))
                return true;
        }
        return false;
    }

    /**
     * the string representation of the round
     * @return the basic info of the round to string
     */
    @Override
    public String toString() {
        return "Round{" +
                "teamsNumber=" + teamsNumber +
                '}';
    }

}
