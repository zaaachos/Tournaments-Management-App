package com.example.managetournamentapp.view.Player.PlayerPage;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerPagePresenterTest {

    private PlayerPagePresenter presenter;
    private PlayerPageView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {

        new MemoryInitializer().prepareData();
        view = new PlayerPageViewStub();
        presenter = new PlayerPagePresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());

        presenter.setView(view);
    }

    /**
     * Test if presenter finds the user properly
     */
    @Test
    public void findPlayerInfo() {
        presenter.findAccess("tommy0");
        presenter.findPlayerInfo("tommy0");
        Assert.assertEquals("tomtom", presenter.getPlayerName());
    }

    /**
     * Test user's actions on different cases
     */
    @Test
    public void testActions() {

        presenter.findPlayerInfo("tommy0");
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        Assert.assertNotEquals(null, new MemoryLoggedInUser().getUser());

        presenter.onLogOut();
        Assert.assertTrue(((PlayerPageViewStub) view).onLogout);

        presenter.onNoLogOut();
        Assert.assertTrue(((PlayerPageViewStub) view).onNoLogout);

        presenter.onYesLogOut();

        Assert.assertEquals(null, new MemoryLoggedInUser().getUser());

        presenter.onPlayerAccount();
        Assert.assertTrue(((PlayerPageViewStub) view).onInfo);

        presenter.onPlayerTeams();
        Assert.assertTrue(((PlayerPageViewStub) view).onTeams);

        presenter.onPlayerInvites();
        Assert.assertTrue(((PlayerPageViewStub) view).onInvites);

    }
}