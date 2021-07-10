package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentTest {

    private Tournament tournament;
    private Tournament tournament2;
    private Tournament tournament3;
    private Tournament tournament4;
    private Team team;
    private Player player;
    private ArrayList<LocalDate> dates;
    private ArrayList<LocalDate> dates2;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        dates = new ArrayList<>();
        dates2 = new ArrayList<>();
        for (int i = 1; i < 64; i++) {
            dates.add(LocalDate.now());
        }
        for (int i = 1; i < 64; i++) {
            dates2.add(LocalDate.now());
        }

        Credentials credentials = new Credentials("sakis7", "123");
        player = new Player("sakis", "rouvas","Athens",  "69000000", "aa@aa.aa", LocalDate.parse("2007-01-01"), credentials);
        team = new Team("Celtic", (new Sport("Basketball3v3")), AgeDivision.K15, player, "green");
        tournament = new Tournament("TOURNOUA1", LocalDate.parse("2030-05-10"), LocalDate.parse("2030-05-29"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates);
        tournament2 = new Tournament("TOURNOUA1", LocalDate.parse("2030-05-10"), LocalDate.parse("2030-05-29"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates);
        tournament3 = new Tournament("TOURNOUA2", LocalDate.parse("2021-07-01"), LocalDate.parse("2021-07-31"), "NAUPLIO", (new Sport("Basketball3v3")),
                32, AgeDivision.K18, dates2);
        tournament4 = new Tournament();


    }

    /**
     * test the toString method
     */
    @Test
    public void testPrint() {

        Assert.assertEquals(tournament2.toString(), tournament.toString());

    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals() {


        tournament.setDescription("TOURNOUA1111");

        player.addSportInterested(new Sport("Basketball3v3"));
        team.addPlayer(player);
        tournament.addParticipation(new Participation(tournament, team));
        tournament2.setDescription("TOURNOUA1111");
        tournament2.addParticipation(new Participation(tournament2, team));
        Assert.assertEquals(tournament2, tournament);

    }

    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals() {

        Assert.assertNotEquals(tournament2, tournament3);
        Assert.assertNotEquals(tournament3, tournament);
        Assert.assertNotEquals(tournament4, tournament);

    }

    /**
     * Tester for removing Participations
     */
    @Test
    public void testRemoveParticipation() {
        player.addSportInterested(new Sport("Basketball3v3"));
        team.addPlayer(player);
        Participation part = new Participation(tournament, team);
        tournament.addParticipation(part);
        tournament.removeParticipation(part);
        Assert.assertTrue(!tournament.getParticipations().contains(part));


    }

    /**
     * test the max teams number
     */
    @Test
    public void testGetMAX_TEAMS_NUMBER() {

        Assert.assertEquals(tournament2.getMAX_TEAMS_NUMBER(), tournament.getMAX_TEAMS_NUMBER());
    }

    /**
     * test the initialization of the rounds
     */
    @Test
    public void testInitRounds() {

        Assert.assertEquals(tournament2.getRounds(), tournament.getRounds());
    }

    /**
     * test if the game is running
     */
    @Test
    public void testIsRunning() {

        tournament.setStartDate(LocalDate.parse("2030-06-02"));
        tournament.setFinishDate(LocalDate.parse("2030-06-30"));
        Assert.assertFalse(tournament.isRunning());
    }


    /**
     * test if the tournament is full
     */
    @Test
    public void testIsFull() {

        Assert.assertFalse(tournament.isFull());
        String[] names = {"Nikos", "Kostas", "Takis", "Giorgos", "Christos", "Avraam"};
        String[] surnames = {"Nikou", "Konstas", "Takis", "Zachos", "Ioannou"};
        String[] phones = {"69xxxxxxx", "69dfggdfg", "6993453453", "456456"};
        String[] emails = {"nikos@g.com", "nik@g.com", "nana@yahoo.gr", "a123@y.gr"};


        for (int i = 0; i < 32; i++) {

            Random random = new Random();
            int randomName = random.nextInt(names.length);
            int randomSurname = random.nextInt(surnames.length);
            int randomPhone = random.nextInt(phones.length);
            int randomEmail = random.nextInt(emails.length);


            Player p = new Player(names[randomName], surnames[randomSurname], "Athens", phones[randomPhone], emails[randomEmail], LocalDate.parse("2007-01-01"), (new Credentials(names[randomName], phones[randomPhone])));
            p.setBirthDate(LocalDate.parse("2007-01-01"));
            p.addSportInterested(new Sport("Basketball3v3"));
            Team testTeam = new Team(names[randomName], (new Sport("Basketball3v3")), AgeDivision.K15, p, "green");
            for (int y = 0; y < 5; y++) {
                random = new Random();
                randomName = random.nextInt(names.length);
                randomSurname = random.nextInt(surnames.length);
                randomPhone = random.nextInt(phones.length);
                randomEmail = random.nextInt(emails.length);


                Player playerTest = new Player(names[randomName], surnames[randomSurname],"Athens",  phones[randomPhone], emails[randomEmail], LocalDate.parse("2007-01-01"), (new Credentials(names[randomName], phones[randomPhone])));
                playerTest.setBirthDate(LocalDate.parse("2007-01-01"));
                playerTest.addSportInterested(new Sport("Basketball3v3"));
                testTeam.addPlayer(playerTest);
            }

            tournament.addParticipation(new Participation(tournament, testTeam));
        }
        Assert.assertTrue(tournament.isFull());


    }


    /**
     * Tester for change tournaments title
     */
    @Test
    public void testChangeTournamentTitle() {

        String newTitle = "EPSA_1";
        tournament.setTitle(newTitle);
        Assert.assertEquals(newTitle, tournament.getTitle());

    }

    /**
     * Tester for change tournaments location
     */
    @Test
    public void testChangeTournamentLocation() {

        String newLocation = "TRIKALA";
        tournament.setLocation(newLocation);
        Assert.assertEquals(newLocation, tournament.getLocation());

    }

    /**
     * Tester for change tournaments description
     */
    @Test
    public void testChangeTournamentDescription() {

        String newDescription = "TRIKALA BEST TOURNOUA!";
        tournament.setDescription(newDescription);
        Assert.assertEquals(newDescription, tournament.getDescription());

    }


    /**
     * Tester for change tournaments sport
     */
    @Test
    public void testChangeTournamentSport() {

        Sport newSport = new Sport("Volleyball6v6");
        tournament.setSportType(newSport);
        Assert.assertEquals(newSport, tournament.getSportType());


    }

    /**
     * Tester for change tournaments AgeDivision
     */
    @Test
    public void testChangeTournamentAgeDivision() {

        tournament.setAgeDivision(AgeDivision.K12);
        Assert.assertEquals(AgeDivision.K12, tournament.getAgeDivision());


    }

    /**
     * Tester for change tournaments start date
     */
    @Test
    public void testChangeStartDate() {

        LocalDate newStartDate = LocalDate.parse("2021-08-05");
        tournament.setStartDate(newStartDate);
        Assert.assertEquals(newStartDate, tournament.getStartDate());


    }

    /**
     * Tester for change tournaments finish date
     */
    @Test
    public void testChangeFinishDate() {

        LocalDate newFinishDate = LocalDate.parse("2021-08-10");
        tournament.setFinishDate(newFinishDate);
        Assert.assertEquals(newFinishDate, tournament.getFinishDate());

    }


}