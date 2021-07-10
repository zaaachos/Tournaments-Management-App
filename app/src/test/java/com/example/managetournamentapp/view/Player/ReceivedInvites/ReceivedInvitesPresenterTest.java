package com.example.managetournamentapp.view.Player.ReceivedInvites;


import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */


public class ReceivedInvitesPresenterTest {

    private ReceivedInvitesPresenter presenter;
    private ReceivedInvitesView view;
    private Player testPlayer;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new ReceivedInvitesViewStub();
        presenter = new ReceivedInvitesPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());

        presenter.setView(view);
    }

    /**
     * Test if presenter finds the player's invites properly from DAO
     */
    @Test
    public void findInvites() {
        presenter.findInvites("gioza");
        Assert.assertEquals("gioza", presenter.getPlayerName());

    }

    /**
     * Test user's actions
     */
    @Test
    public void testActions() {
        presenter.onTeamPageClick();
        Assert.assertTrue(((ReceivedInvitesViewStub) view).onTeam);

        presenter.onHomePage();
        Assert.assertTrue(((ReceivedInvitesViewStub) view).onHome);

    }

    /**
     * Test getter for Invitation List
     */
    @Test
    public void getInvitations() {
        presenter.findInvites("gioza");
        Assert.assertEquals(1, presenter.getInvites().size());
        Assert.assertEquals("Celtic10", presenter.getInvites().get(0).getTeam().getName());

    }

    /**
     * Test the declination of an invite
     */
    @Test
    public void declineInvite() {
        testPlayer = new PlayerDAOMemory().find("gioza");
        presenter.findInvites("gioza");
        presenter.declineInvitation(testPlayer.getInvitesReceived().get(0));
        presenter.findInvites("gioza");
        Assert.assertEquals(testPlayer.getInvitesReceived().size(), presenter.getInvites().size());

    }

    /**
     * Test the acceptance of an invite
     */
    @Test
    public void acceptInvite() {
        testPlayer = new PlayerDAOMemory().find("gioza");
        presenter.findInvites("gioza");
        presenter.acceptInvitation(testPlayer.getInvitesReceived().get(0));
        presenter.findInvites("gioza");
        Assert.assertEquals(testPlayer.getInvitesReceived().size(), presenter.getInvites().size());

    }
}