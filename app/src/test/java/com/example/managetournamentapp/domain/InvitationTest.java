package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitationTest {
    Invitation invitation;
    Player player;
    Team team;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        Credentials credentials = new Credentials("sakis7","123");
        player = new Player( "sakis", "rouvas" ,"Athens",  "69000000" , "aa@aa.aa", LocalDate.parse("2000-01-01") , credentials);
        team = new Team("Celtic", (new Sport("Basketball3v3")), AgeDivision.K15, player, "green");
        invitation = new Invitation(team);
    }

    /**
     * test the basic methods
     */
    @Test
    public void basicTests() {
        Team team2 = new Team("Celtic2", (new Sport("Basketball3v3")), AgeDivision.K15, player, "green");
        invitation = new Invitation(team);
        Assert.assertFalse(invitation.getAccepted());
        Assert.assertTrue(invitation.getPending());
        invitation.setTeam(team2);
        Assert.assertEquals(invitation.getTeam(),team2);
        Assert.assertTrue(invitation.getDate().equals(LocalDate.now()));
    }

    /**
     * test the equality between to instances
     */
    @Test
    public void equalTest() {
        Assert.assertEquals(invitation, new Invitation(team));
    }
}
