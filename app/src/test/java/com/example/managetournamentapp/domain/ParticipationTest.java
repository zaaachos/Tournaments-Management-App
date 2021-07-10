package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipationTest {
    private Credentials credentials,credentials2,credentials3;

    private Player player,player2,player3;
    private Team team,team2;
    private Tournament tournament,tournament2,tournament3,tournament4;
    private Participation participation,participation2,participation3,participation4,participation5,participation6,participation7,participation8;

    private ArrayList<LocalDate> dates;
    private ArrayList<LocalDate> dates2;


    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        credentials = new Credentials("sakis7", "123");
        credentials2 = new Credentials("johnny","1222");
        credentials3 = new Credentials("patpat","1222");
        player = new Player("sakis", "rouvas","Athens",  "69000000", "aa@aa.aa", LocalDate.parse("2000-01-01"), credentials);
        player2 = new Player("john", "koukos","Athens",  "69000000", "aa@aa.aa", LocalDate.parse("2000-01-01"), credentials2);
        player3 = new Player("patrick", "starman", "Athens", "69000000", "aa@aa.aa", LocalDate.parse("2000-01-01"), credentials3);

        dates = new ArrayList<>();
        dates2 = new ArrayList<>();

        for (int i = 1; i < 64; i++) {
            dates.add(LocalDate.now());
        }
        for (int i = 1; i < 64; i++) {
            dates2.add(LocalDate.now());
        }

        tournament = new Tournament("TOURNOUA1", LocalDate.parse("2021-08-01"), LocalDate.parse("2021-08-31"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates);

        tournament2 =  new Tournament("TOURNOUA150", LocalDate.parse("2021-05-09"), LocalDate.parse("2021-05-31"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates2);
        tournament3 =  new Tournament("TOURNOUA220", LocalDate.parse("2021-05-09"), LocalDate.parse("2021-05-30"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates2);
        tournament4 =  new Tournament("TOURNOUA2fff20", LocalDate.parse("2021-05-09"), null, "ATHENS", (new Sport("Basketball5v5")),
                32, AgeDivision.K15, dates2);


        team = new Team("Celtic", new Sport("Basketball3v3"), AgeDivision.K100, player, "green");
        team2 = new Team("Blues", new Sport("Basketball3v3"), AgeDivision.K100, player2, "yellow");

        participation = new Participation(tournament, team);
        participation2 = new Participation(tournament, team2);
        participation3 = new Participation(tournament2,team);
        participation4 = new Participation(tournament3,team2);
        participation5 = new Participation(tournament4,team);
        participation6 = null;
        participation7 = new Participation();
        participation8 = participation;
    }


    /**
     * test if two participations are in the same tournament
     */
    @Test
    public void inSameTournamentTest() {
        Assert.assertTrue(participation.inSameTournament(participation2));
        Assert.assertFalse(participation.inSameTournament(participation3));
        Assert.assertFalse(participation.inSameTournament(participation6));

    }

    /**
     * test if two participations are simultaneous
     */
    @Test
    public void isSimultaneousTest(){
        Assert.assertTrue(participation3.isSimultaneous(participation4));
        Assert.assertFalse(participation.isSimultaneous(participation4));


    }


    /**
     * test if the participations are currently happening
     */
    @Test
    public void isRunningTest(){
        Assert.assertFalse(participation3.isRunning());
        Assert.assertFalse(participation.isRunning());
        participation5.setFinishDate(LocalDate.parse("2021-05-19"));
        Assert.assertFalse(participation5.isRunning());
    }

    /**
     * test if the participations have finished
     */
    @Test
    public void isPastTest(){
        Assert.assertFalse(participation2.isPast());
        Assert.assertFalse(participation.isPast());
    }

    /**
     * test the getters
     */
    @Test
    public void gettersTest(){
        Assert.assertTrue(participation.getTeam() == participation3.getTeam());
        Assert.assertFalse(participation.getTournament() == participation4.getTournament());
        Assert.assertTrue(participation.getStartDate() == participation2.getStartDate());
        Assert.assertTrue(participation.getFinishDate() == participation.getFinishDate());
        Assert.assertNull(participation7.getFinishDate());
    }

    /**
     * test the getters
     */
    @Test
    public void settersTest(){
        participation3.setStartDate(LocalDate.parse("2021-05-10"));
        Assert.assertFalse(tournament2.getStartDate().equals(participation3.getStartDate()));
        participation3.setTournament(tournament3);
        Assert.assertFalse(participation3.equals(tournament2));
        participation3.setFinishDate(LocalDate.parse("2021-05-14"));
        Assert.assertEquals("2021-05-14",participation3.getFinishDate().toString());
        participation3.setTeam(team2);
        Assert.assertNotEquals(team,participation3.getTeam());
    }

    /**
     * test the toString method
     */
    @Test
    public void toStringTest(){
        Assert.assertEquals(participation.toString(),"Participation{" +
                "startDate=" + participation.getStartDate() +
                ", finishDate=" + participation.getFinishDate() +
                '}');
    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals(){
        Assert.assertEquals(participation, participation8);
        participation2.setTeam(team);
        Assert.assertEquals(participation,participation2);
        participation4.setTeam(team);
        participation4.setTournament(tournament);
        Assert.assertEquals(participation,participation4);
    }

    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals(){
        Assert.assertNotEquals(participation,participation2);
        Assert.assertNotEquals(participation5,participation);
        Assert.assertNotEquals(participation3,participation6);
    }

}