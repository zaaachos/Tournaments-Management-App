package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GroupTest {
    ArrayList<LocalDate> dates;
    Team teamA , teamB;
    Group group, groupB;
    Game game;
    Player player;
    LocalDate date;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        Credentials credentials = new Credentials("sakis7","123");
        dates = new ArrayList<>();
        dates.add(LocalDate.parse("2020-10-10"));
        group = new Group(true, dates);
        player = new Player( "sakis", "rouvas" ,"Athens",  "69000000" , "aa@aa.aa", LocalDate.parse("2000-01-01") , credentials);
        date = LocalDate.parse("2022-01-01");
        teamA = new Team("Celtic", new Sport("Volleyball3v3"), AgeDivision.K100 ,player,"green" );
        teamB = new Team("Barca", new Sport("Volleyball3v3"), AgeDivision.K100 ,player, "red");
        game = new Game("Oaka", teamA , teamB, date );
    }

    /**
     * test adding teams
     */
    @Test
    public void addTeams()  {
        group.addTeam(teamA);
        group.addTeam(null);
        Assert.assertEquals(group.getTeams().size() , 1);
        group.addTeam(teamA);
        Assert.assertEquals(group.getTeams().size() , 1);
        group.addTeam(teamB);
        Assert.assertEquals(group.getTeams().size() , 2);
        group.addTeam(new Team());
        Assert.assertEquals(group.getTeams().size() , 2);

    }

    /**
     * test adding games
     */
    @Test
    public void addGames()  {
        group.addGame(null);
        Assert.assertEquals(group.getGames().size() , 0);
        game.setFinished(true);
        group.addGame(game);
        Assert.assertEquals(group.getGames().size() , 1);
        group.addGame(game);
        Assert.assertEquals(group.getGames().size() , 1);
        Assert.assertTrue(group.allGamesFinished());

        game.setFinished(false);
        Assert.assertFalse(group.allGamesFinished());

    }

    /**
     * test the rankings
     */
    @Test
    public void testRankings()  {
        game.setScoreA(2);
        game.setScoreB(0);
        game.setFinished(true);
        group.addTeam(teamA);
        group.addTeam(teamB);
        group.increaseTeamRanking(teamA,3);
        group.addGame(game);
        Assert.assertEquals(group.getGroupWinners().get(0) , teamA);

        Map<Team, Integer> rankings = new HashMap<>();
        rankings.put(teamA,3);
        rankings.put(teamB,0);
        Assert.assertEquals(group.getRankings() , rankings);

        Assert.assertEquals(group.getTeamRanking(teamA) , 3);
        Assert.assertEquals(group.getTeamRanking(teamB) , 0);
    }


    /**
     * test the equalities
     */
    @Test
    public void testEquals()  {
        groupB = new Group(true, dates);
        Assert.assertTrue(group.equals(groupB));
    }


    /**
     * test the setup of a group
     */
    @Test
    public void setupTest(){
        Team teamC = new Team("Osfp", new Sport("Volleyball3v3"), AgeDivision.K100 ,player,"green" );
        Team teamD = new Team("Aek", new Sport("Volleyball3v3"), AgeDivision.K100 ,player, "red");
        ArrayList<Team> teams = new ArrayList<>();
        teams.add(teamA);
        teams.add(teamB);
        teams.add(teamC);
        teams.add(teamD);

        dates = new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
            dates.add(LocalDate.parse("2020-01-01") );
        }
        group = new Group(false,dates);
        group.addTeams(teams);

        Assert.assertEquals(group.getGames().get(0), new Game("",teamA,teamB,LocalDate.parse("2020-01-01")));
        Assert.assertEquals(group.getGames().get(1),  new Game("",teamA,teamC,LocalDate.parse("2020-01-01")));
        Assert.assertEquals(group.getGames().get(2),  new Game("",teamA,teamD,LocalDate.parse("2020-01-01")));
        Assert.assertEquals(group.getGames().get(3),  new Game("",teamB,teamC,LocalDate.parse("2020-01-01")));
        Assert.assertEquals(group.getGames().get(4),  new Game("",teamB,teamD,LocalDate.parse("2020-01-01")));
        Assert.assertEquals(group.getGames().get(5),  new Game("",teamC,teamD,LocalDate.parse("2020-01-01")));
    }




}