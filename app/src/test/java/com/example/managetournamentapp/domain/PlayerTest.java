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

public class PlayerTest {
    private Player player1;
    private Player player2;
    private Player player;
    private Player player3;

    private Credentials credentials;
    private Credentials credentials2;
    private Credentials credentials3;
    private Credentials credentials4;
    private Credentials credentials5;

    private LocalDate date;
    private LocalDate date2;

    private Team team,team2;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        credentials = new Credentials("Nondic", "123456");
        credentials2 = new Credentials("Gianneiro", "123456");
        credentials3 = new Credentials("Giannis", "GioGio");
        credentials4 = new Credentials("Nondas", "123456");
        credentials5 = new Credentials();

        date = LocalDate.parse("1994-05-03");
        date2 = LocalDate.parse("2000-01-04");

        player1 = new Player("Nondas", "Ioannou", "Athens", "69xxxxxxx", "nondas@gmail.com", date, credentials);
        player1.addSportInterested(new Sport("Volleyball3v3"));
        player2 = new Player("Giorgos", "Zachariadis", "Athens", "69xxxxxxx", "zacchos@gmail.com", date2, credentials2);
        player = new Player();

        player2.addSportInterested(new Sport("Volleyball3v3"));

        player3 = new Player("John", "Ioannou","Athens",  "69xxxxxxx", "john@gmail.com", date, credentials3);
        player3.addSportInterested(new Sport("Volleyball3v3"));
        player3.getTeamsJoined().add(team2);

        team = new Team("Boston", new Sport("Volleyball3v3"), AgeDivision.K100 ,player1, "white" );
        team2 = new Team("Blues", new Sport("Volleyball3v3"), AgeDivision.K100 ,player2, "white" );
        team2.getPlayers().add(player3);

    }

    /**
     * test the basic methods
     */
    @Test
    public void basicTests(){


        team = null;

        player1.removeJoinedTeam(team);
        Assert.assertEquals(1,player1.getTeamsJoined().size());

        player1.addJoinedTeam(team);
        Assert.assertEquals(1,player1.getTeamsJoined().size());

        //player can't remove a team
        //that he hasn't joined
        player1.removeJoinedTeam(team2);
        Assert.assertEquals(1,player1.getTeamsJoined().size());


    }

    /**
     * test adding sports in the list
     */
    @Test
    public void addSportInterestedTest(){
        Sport sport3 = null;
        player2.addSportInterested(sport3);

        //can't add a null sport
        //already had 1 sport interest in the arraylist
        Assert.assertEquals(1,player2.getSportsInterested().size());

        //can't add a sport that
        //is already added
        player2.addSportInterested(new Sport("Volleyball3v3"));
        Assert.assertEquals(1,player2.getSportsInterested().size());

        //adding a new sport
        //that doesn't exist in the arraylist
        player2.addSportInterested(new Sport("Volleyball5v5"));
        Assert.assertEquals(2,player2.getSportsInterested().size());
    }

    /**
     * test removing sports from the list
     */
    @Test
    public void removeSportInterestedTest(){
        Sport sport4 = null;
        player2.removeSportInterested(sport4);

        //can't remove a null sport interest
        Assert.assertEquals(1, player2.getSportsInterested().size());

        //succesfully removing sport
        player2.removeSportInterested(player2.getSportsInterested().get(0));

        Assert.assertEquals(0,player2.getSportsInterested().size());
    }

    /**
     * test deleting teams
     */
    @Test
    public void deleteTeamTest(){

        Team team4 = null;
        //can't delete a null team
        player2.deleteTeam(team4);
        Assert.assertEquals(1,player2.getTeamsJoined().size());

        //player can't delete a team that he isn't a captain
        player2.deleteTeam(team);
        Assert.assertEquals(1,player2.getTeamsJoined().size());

        //captain deletes Team
        player2.removeJoinedTeam(team2);
        Assert.assertEquals(0,player2.getTeamsJoined().size());


    }

    /**
     * test adding more teams in the captain list
     */
    @Test
    public void addCaptainInTeamsTest(){
        team = null;
        //can't be a captain in a null team
        player2.addCaptainInTeams(team);
        Assert.assertEquals(1,player2.getCaptainInTeams().size());

        //can't be a captain because he isn't a member of the specific team
        player1.addCaptainInTeams(team2);
        Assert.assertEquals(1,player1.getCaptainInTeams().size());

        //captain in another team successfull
        team2.getPlayers().add(player2);
        player2.addCaptainInTeams(team2);
        Assert.assertEquals(2,player2.getCaptainInTeams().size());
    }

    /**
     * test removing teams from the captain list
     */
    @Test
    public void removeCaptainInTeamsTest(){
        team = null;

        //can't remove a null team
        player2.removeCaptainInTeams(team);
        Assert.assertEquals(1,player2.getCaptainInTeams().size());

        //can't remove  captain because he isn't a member of the specific team
        player1.removeCaptainInTeams(team2);
        Assert.assertEquals(1,player1.getCaptainInTeams().size());

        //captain in another team successfull
        team2.getPlayers().add(player2);
        player2.addCaptainInTeams(team2);
        Assert.assertEquals(2,player2.getCaptainInTeams().size());

        player2.removeCaptainInTeams(team2);
        Assert.assertEquals(1,player2.getCaptainInTeams().size());

    }

    /**
     * test adding invitations
     */
    @Test
    public void addInviteTest(){
        //can't add a null invite
        Invitation invitation = null;
        player2.addInvite(invitation);

        Assert.assertEquals(0,player2.getInvitesReceived().size());

        Invitation invitation2 = new Invitation(team);

        player2.addInvite(invitation2);
        Assert.assertEquals(1,player2.getInvitesReceived().size());
    }

    /**
     * test removing invitations
     */
    @Test
    public void removeInviteTest(){
        Invitation invitation2 = new Invitation(team);
        Invitation invitation3 = new Invitation(team2);

        player2.addInvite(invitation2);
        Assert.assertEquals(1,player2.getInvitesReceived().size());

        //can't remove an invitation
        //that doesn't exist in the invitation
        //arraylist
        player2.removeInvite(invitation3);
        Assert.assertEquals(1,player2.getInvitesReceived().size());

        invitation3 = null;

        //can't remove a null invitation
        player2.removeInvite(invitation3);
        Assert.assertEquals(1,player2.getInvitesReceived().size());


        player2.removeInvite(invitation2);
        Assert.assertEquals(0,player2.getInvitesReceived().size());


    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals(){
        player1.setLocation("Athens");
        player = player1;
        Assert.assertEquals(player,player1);
    }

    /**
     * test the toString method
     */
    @Test
    public void toStringTest(){
        player2.setLocation("Athens");

        Assert.assertEquals("User{name='Giorgos', surname='Zachariadis', phoneNumber='69xxxxxxx', email='zacchos@gmail.com', birthDate=2000-01-04, " +
                "credentials=Credentials{username='Gianneiro', password='123456'}}Location: Athens Age group: K100",player2.toString());
    }
}