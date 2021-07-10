package com.example.managetournamentapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Group {
    private final boolean isKnockout;
    private final int groupSize;
    private final int gamesNumber;
    private ArrayList<Game> games = new ArrayList<>();
    private Map<Team, Integer> rankings = new HashMap<>();
    private ArrayList<LocalDate> dates;

    /**
     *the constructor of a group
     * @param isKnockout if the group contains knockout games or groups
     * @param dates an arraylist of all the game dates
     */
    public Group(boolean isKnockout , ArrayList<LocalDate> dates ) {
        this.isKnockout = isKnockout;
        this.dates = dates;
        if (isKnockout) {
            groupSize = 2;
            gamesNumber = 1;
        } else {
            groupSize = 4;
            gamesNumber = 6;
        }
    }

    /**
     * adds a new team in the group
     * @param team is the new team that will be added
     */
    public void addTeam(Team team) {
        if (team == null) {
            return;
        }
        if (rankings.keySet().size() == groupSize) {
            return;
        }
        if (rankings.containsKey(team))
            return;
        rankings.put(team, 0);
    }

    /**
     * add all the teams that will participate in the round
     * @param teams the arraylist of teams that will be added
     */
    public void addTeams(ArrayList<Team> teams) {
        rankings = new HashMap<>();
        for (Team team: teams){
            addTeam(team);
        }
        setupGames(teams);
    }

    /**
     * assign the teams to the games that will take part
     * @param teams the list of teams that will participate
     */
    public void setupGames(List<Team> teams){
        games.clear();
        games.add( new Game("",teams.get(0),teams.get(1),dates.get(0)  ));
        if (isKnockout)
            return;
        games.add( new Game("",teams.get(0),teams.get(2),dates.get(1)  ));
        games.add( new Game("",teams.get(0),teams.get(3),dates.get(2)  ));
        games.add( new Game("",teams.get(1),teams.get(2),dates.get(3)  ));
        games.add( new Game("",teams.get(1),teams.get(3),dates.get(4)  ));
        games.add( new Game("",teams.get(2),teams.get(3),dates.get(5)  ));
    }

    /**
     * returns the ranking of the team in the group
     * @param team is the team
     * @return the ranking of the team
     */
    public int getTeamRanking(Team team) {
        if (team == null) {
            return -1;
        }
        if (!rankings.containsKey(team)) {
            return -1;
        }
        return rankings.get(team);
    }

    /**
     * is used to increase the ranking of a team (basically when the team accomplishes a new win)
     * @param team the team whose ranking we will increase
     * @param points the additional points that the team collects
     */
    public void increaseTeamRanking(Team team, int points) {
        if (team == null || points < 0) {
            return;
        }
        if (!rankings.containsKey(team)) {
            return;
        }
        rankings.put(team, rankings.get(team) + points);
    }

    /**
     * resets the rankings, every teams gets zero points
     */
    public void setRankingsToZero(){
        for (Map.Entry<Team, Integer> entry : rankings.entrySet()) {
            rankings.put(entry.getKey(), 0);
        }
    }

    /**
     * check every game of the group and recalculate the rankings
     */
    public void refreshRankings(){
        setRankingsToZero();
        for (Game game : games){
            if (game.isFinished()){
                if (game.findWinner()>0){
                    increaseTeamRanking(game.getTeamA(),3);
                }else if (game.findWinner()<0){
                    increaseTeamRanking(game.getTeamB(),3);
                }else{
                    increaseTeamRanking(game.getTeamA(),1);
                    increaseTeamRanking(game.getTeamB(),1);
                }
            }
        }
    }

    /**
     * the group is considered knockout: there are two teams, one match, and one team is promoted
     * or
     * he group is considered as an actual group: there are four teams, six matches, and two teams are promoted
     * @return true if the group is knockout
     */
    public boolean isKnockout() {
        return isKnockout;
    }

    /**
     * return the all the games of the group
     * @return the arraylist of the games
     */
    public ArrayList<Game> getGames() {
        return new ArrayList<>(games);
    }

    /**
     * add a new game in the group
     * @param game that will be added
     */
    public void addGame(Game game) {
        if (game == null) {
            return;
        }
        if (games.size() == groupSize) {
            return;
        }
        if (games.contains(game))
            return;
        games.add(game);
    }

    /**
     * get the rankings of the group as a sorted set of pairs <team , ranking of team>
     * @return the map with the rankings
     */
    public Map<Team, Integer> getRankings() {
        return sortByValue(rankings);
    }

    /**
     *get a copy of the teams of the group
     * @return the teams of the group
     */
    public ArrayList<Team> getTeams() {
        return new ArrayList<>(rankings.keySet());
    }

    /**
     * find if the group is done
     * @return true if every game of the group is finished
     */
    public boolean allGamesFinished() {
        if (games.isEmpty())
            return false;
        for (Game game : games) {
            if (!game.isFinished())
                return false;
        }
        return true;
    }


    /**
     *get the teams that are considered winners, and will be promoted
     * @return the arraylist of the promoted teams
     */
    public ArrayList<Team> getGroupWinners() {
        if (!allGamesFinished())
            return null;

        Map<Team, Integer> sortedRankings = sortByValue(rankings);
        ArrayList<Team> winners = new ArrayList<>();
        ArrayList<Team> teams = new ArrayList<>(sortedRankings.keySet());
        // pick 1 winner
        if (isKnockout()) {
            winners.add(teams.get(1));
        }
        // pick 2 winners
        else {
            winners.add(teams.get(3));
            winners.add(teams.get(2));
        }
        return winners;
    }


    /**
     *sorts the rankings by points (in descending order)
     * @param hm the initial hashmap
     * @return the sorted hashmap
     */
    private static Map<Team, Integer> sortByValue(Map<Team, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Team, Integer>> list = new LinkedList<Map.Entry<Team, Integer>>(hm.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Team, Integer>>() {
            public int compare(Map.Entry<Team, Integer> o1,
                               Map.Entry<Team, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<Team, Integer> temp = new LinkedHashMap<Team, Integer>();
        for (Map.Entry<Team, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    /**
     * check the equality of two group objects
     * @param other the other group
     * @return true if this group is equal to the other
     */
    public boolean equals(Object other) {
        if (this == other){
            return true;
        }
        if (other instanceof Group) {
            Group otherGroup = (Group) other;
            if (gamesNumber == otherGroup.gamesNumber && isKnockout == otherGroup.isKnockout
                    && groupSize == otherGroup.groupSize && rankings.equals(otherGroup.rankings)
                    && games.equals(otherGroup.games) && dates.equals(otherGroup.dates))
                return true;
        }
        return false;
    }


}
