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

public class OrganizerTest {

    private Organizer organizer;
    private Organizer organizer2;
    private Organizer organizer3;
    private Organizer organizer4;
    private LocalDate birthDate;
    private LocalDate birthDate2;
    private Credentials cred;
    private Credentials cred2;
    private Tournament tournament;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {

        ArrayList<Tournament> tournaments = new ArrayList<>();
        ArrayList<Tournament> tournaments2 = new ArrayList<>();
        birthDate = LocalDate.parse("1969-06-05");
        birthDate2 = LocalDate.parse("1978-10-23");
        cred = new Credentials("nikos1", "1234567");
        cred2 = new Credentials("lemon", "tikatika");

        organizer = new Organizer("Nikos", "Nikopoulos", "69xxxxxxxx", "nikosnik@gmail.com", birthDate, cred, "ESKA");
        organizer2 = new Organizer("Nikos", "Nikopoulos", "69xxxxxxxx", "nikosnik@gmail.com", birthDate, cred, "ESKA");
        organizer3 = new Organizer("Kostas", "Kostas", "69xxxxxxxx", "kostakis@gmail.com", birthDate2, cred2, "ESKANA");
        organizer4 = new Organizer();

        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        for (int i = 0; i < 64; i++) {
            dates.add(LocalDate.now());
        }
        tournament = new Tournament("TOURNOUA1", LocalDate.parse("2021-08-01"), LocalDate.parse("2021-08-25"), "ATHENS", (new Sport("Basketball3v3")),
                32, AgeDivision.K15, dates);

    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals() {

        Assert.assertEquals(organizer2, organizer);
        Assert.assertEquals(organizer, organizer);

    }

    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals() {

        Assert.assertNotEquals(organizer2, organizer3);
        Assert.assertNotEquals(organizer3, organizer);
        Assert.assertNotEquals(organizer4, organizer);

    }

    /**
     * test the toString method
     */
    @Test
    public void testPrinting() {

        Assert.assertEquals(organizer2.toString(), organizer.toString());

    }

    /**
     * test the title getter
     */
    @Test
    public void getNameTest() {
        Assert.assertEquals("ESKA", organizer.getTitle());
    }

    /**
     * test the title setter
     */
    @Test
    public void testSetterGetterTitle() {

        String newTitle = "EPSA";
        organizer3.setTitle(newTitle);
        Assert.assertEquals(newTitle, organizer3.getTitle());

    }

    /**
     * Tester for adding tournaments
     */
    @Test
    public void testAddTournament() {

        Tournament tournamentTest = null;
        organizer.addTournament(tournamentTest);

        organizer.addTournament(tournament);
        Assert.assertEquals(1, organizer.getTournaments().size());
        Assert.assertEquals(tournament, organizer.getTournaments().get(0));
        Assert.assertTrue(organizer.getTournaments().contains(tournament));

    }

    /**
     * Tester for deleting tournaments
     */
    @Test
    public void testDeleteTournament() {

        Tournament tournamentTest = null;
        organizer.deleteTournament(tournamentTest);

        organizer.deleteTournament(tournament);


        organizer.addTournament(tournament);
        organizer.deleteTournament(tournament);


        Assert.assertEquals(0, organizer.getTournaments().size());
        Assert.assertFalse(organizer.getTournaments().contains(tournament));

    }


}
